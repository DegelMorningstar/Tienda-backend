package urbandeport.tienda.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import urbandeport.tienda.models.entity.Usuario;
import urbandeport.tienda.models.entity.VerificationToken;

import java.util.List;

public interface IUsuarioService {
    public Usuario findByEmail(String email);

    public List<Usuario> getAllUsers();

    public Page<Usuario> getAllUsers(Pageable pageable);

    public Usuario registroUsuario(Usuario usuario);

    public void saveUserVerificationToken(Usuario usuario,String token);

    public String validateToken(String theToken);

    public VerificationToken generateNewVerificationToken(String oldToken);


}
