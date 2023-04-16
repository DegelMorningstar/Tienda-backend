package urbandeport.tienda.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urbandeport.tienda.models.dao.ICategoriaDao;
import urbandeport.tienda.models.dao.IDescuentoDao;
import urbandeport.tienda.models.dao.IProductoDao;
import urbandeport.tienda.models.entity.Categoria;
import urbandeport.tienda.models.entity.Descuento;
import urbandeport.tienda.models.entity.Producto;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoDao productoDao;

    @Autowired
    private ICategoriaDao categoriaDao;

    @Autowired
    private IDescuentoDao descuentoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAllProductos() { return productoDao.findAll(); }

    @Override
    @Transactional
    public Producto registrarProducto(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAllCategorias() {
        return categoriaDao.findAll();
    }

    @Override
    @Transactional
    public Categoria registrarCategoria(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    public List<Descuento> findAllDescuentos() {
        return descuentoDao.findAll();
    }

    @Override
    public Descuento registrarDescuento(Descuento descuento) {
        return descuentoDao.save(descuento);
    }


}
