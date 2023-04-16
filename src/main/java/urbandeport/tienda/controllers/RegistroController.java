package urbandeport.tienda.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urbandeport.tienda.models.dao.IVerificationTokenDao;
import urbandeport.tienda.models.entity.Usuario;
import urbandeport.tienda.models.entity.VerificationToken;
import urbandeport.tienda.models.services.UsuarioService;
import urbandeport.tienda.util.ResponseHandler;
import urbandeport.tienda.util.event.RegistrationCompleteEvent;
import urbandeport.tienda.util.event.listener.RegistrationCompleteEventListener;
import urbandeport.tienda.util.exception.InvalidVerificationTokenException;
import urbandeport.tienda.util.exception.UserAlreadyExistsException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/registro")
public class RegistroController {

    private Logger log = LoggerFactory.getLogger(RegistroController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IVerificationTokenDao verificationTokenDao;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RegistrationCompleteEventListener eventListener;

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody Usuario usuarioRequest, final HttpServletRequest request){
        Usuario user = null;
        try {
            user = usuarioService.registroUsuario(usuarioRequest);
        }catch (UserAlreadyExistsException e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null
            );
        }
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return ResponseHandler.generateResponse(
                "Exito! Por favor, revisa tu email para completar el registro.",
                HttpStatus.OK,
                null
        );
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<Object> verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = verificationTokenDao.findByToken(token);
        if (theToken == null){
            return ResponseHandler.generateResponse(
                    "Token invalido.",
                    HttpStatus.BAD_REQUEST,
                    null
            );
        }
        if (theToken.getUsuario().getVerificado()){
            return ResponseHandler.generateResponse(
                    "La cuenta ya esta verificada, por favor inicia sesión.",
                    HttpStatus.OK,
                    null
            );
        }
        String verificationResult = usuarioService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("Validado")){
            return ResponseHandler.generateResponse(
                    "Email verificado con exito. Ahora puedes iniciar sesión",
                    HttpStatus.OK,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                verificationResult,
                HttpStatus.BAD_REQUEST,
                null
        );
    }

    @GetMapping("/resend-verification-token")
    public ResponseEntity<Object> resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {
        try {
            VerificationToken verificationToken =  usuarioService.generateNewVerificationToken(oldToken);
            Usuario theUser = verificationToken.getUsuario();
            resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
        }catch (InvalidVerificationTokenException | MessagingException  | UnsupportedEncodingException e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Se ha enviado un nuevo link, revisa tu email para completar tu registro.",
                HttpStatus.OK,
                null
        );
    }

    private void resendVerificationTokenEmail(Usuario theUser, String applicationUrl, VerificationToken token)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/api/registro/verifyEmail?token="+token.getToken();
        eventListener.sendVerificationEmail(url);
        log.info("Click the link to verify your registration :  {}", url);
    }



    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
