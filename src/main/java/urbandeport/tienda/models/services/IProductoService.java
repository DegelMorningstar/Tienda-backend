package urbandeport.tienda.models.services;

import urbandeport.tienda.models.entity.Categoria;
import urbandeport.tienda.models.entity.Descuento;
import urbandeport.tienda.models.entity.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAllProductos();

    public Producto registrarProducto(Producto producto);

    public List<Categoria> findAllCategorias();

    public Categoria registrarCategoria(Categoria categoria);

    public List<Descuento> findAllDescuentos();

    public Descuento registrarDescuento(Descuento descuento);

}
