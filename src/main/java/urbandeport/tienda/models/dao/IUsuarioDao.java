package urbandeport.tienda.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import urbandeport.tienda.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
}
