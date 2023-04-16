package urbandeport.tienda.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import urbandeport.tienda.models.entity.Categoria;

public interface ICategoriaDao extends JpaRepository<Categoria,Long> {
}
