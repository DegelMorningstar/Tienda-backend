package urbandeport.tienda.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import urbandeport.tienda.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto,Long> {
}
