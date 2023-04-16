package urbandeport.tienda.util.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import urbandeport.tienda.models.entity.Usuario;
import urbandeport.tienda.models.services.UsuarioService;
import urbandeport.tienda.util.event.RegistrationCompleteEvent;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private JavaMailSender mailSender;
    private Usuario theUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        theUser = event.getUser();
        //2. Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        //3. Save the verification token for the user
        userService.saveUserVerificationToken(theUser, verificationToken);
        //4 Build the verification url to be sent to the user
        String url = "http://localhost:4200/auth/validar-email/"+verificationToken;
        //5. Send the email.
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration :  {}", url);

    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Verifica tu cuenta";
        String senderName = "Registro de usuario en Portal Service";
        String mailContent = "<p> Saludos, "+ theUser.getNombre()+ ", </p>"+
                "<p>Gracias por registrarte con nosotros,"+"" +
                "Por favor, haz click en el siguiente enlace para terminar tu registro.</p>"+
                "<a href=\"" +url+ "\">Verifica tu correo para activar tu cuenta.</a>"+
                "<p> Gracias! <br> Registro de usuario en Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("fermaster911@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
