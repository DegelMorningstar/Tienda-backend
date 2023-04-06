package urbandeport.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import urbandeport.tienda.models.entity.Usuario;
import urbandeport.tienda.models.services.IUsuarioService;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/clientes")
    public List<Usuario> indexUsers() {
        return usuarioService.findAll();
    }
}
