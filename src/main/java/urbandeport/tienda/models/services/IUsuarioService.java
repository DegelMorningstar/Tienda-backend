package urbandeport.tienda.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import urbandeport.tienda.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public Usuario findByEmail(String email);

    public List<Usuario> findAll();

    public Page<Usuario> findAll(Pageable pageable);

    public Usuario findById(Long id);

    public Usuario save(Usuario user);

    public void delete(Long id);


}
