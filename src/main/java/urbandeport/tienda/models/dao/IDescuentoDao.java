package urbandeport.tienda.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import urbandeport.tienda.models.entity.Categoria;
import urbandeport.tienda.models.entity.Descuento;

public interface IDescuentoDao extends JpaRepository<Descuento,Long> {
}
