package urbandeport.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import urbandeport.tienda.models.entity.Usuario;
import urbandeport.tienda.models.services.IUsuarioService;
import urbandeport.tienda.util.ResponseHandler;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/clientes")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> indexUsuarios() {
        List<Usuario> usuarios = null;
        try {
            usuarios = usuarioService.getAllUsers();
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        if(usuarios == null){
            return ResponseHandler.generateResponse(
                    "No hay registros",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                usuarios
        );
    }
}
