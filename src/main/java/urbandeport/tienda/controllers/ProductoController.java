package urbandeport.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import urbandeport.tienda.models.entity.Categoria;
import urbandeport.tienda.models.entity.Descuento;
import urbandeport.tienda.models.entity.Producto;
import urbandeport.tienda.models.services.IProductoService;
import urbandeport.tienda.util.ResponseHandler;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public ResponseEntity<Object> indexProductos(){
        List<Producto> productos = null;
        try{
            productos = productoService.findAllProductos();
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        if(productos == null){
            return ResponseHandler.generateResponse(
                    "No hay registros",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                productos
        );
    }

    @PostMapping("/productos")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> crearProducto(
            @Valid @RequestBody Producto producto, BindingResult result
    ){
        Producto nuevoProducto =null;
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map( err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Error al validar el formulario",
                    HttpStatus.BAD_REQUEST,
                    errors
            );
        }
        try {
            nuevoProducto = productoService.registrarProducto(producto);
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                nuevoProducto
        );

    }

    @GetMapping("/categorias")
    public ResponseEntity<Object> indexCategorias(){
        List<Categoria> categorias = null;
        try{
            categorias = productoService.findAllCategorias();
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        if(categorias == null){
            return ResponseHandler.generateResponse(
                    "No hay registros",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                categorias
        );
    }

    @PostMapping("/categorias")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> crearCategoria(
            @Valid @RequestBody Categoria categoria, BindingResult result
    ){
        Categoria nuevaCategoria =null;
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map( err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Error al validar el formulario",
                    HttpStatus.BAD_REQUEST,
                    errors
            );
        }
        try {
            nuevaCategoria = productoService.registrarCategoria(categoria);
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                nuevaCategoria
        );

    }

    @GetMapping("/descuentos")
    public ResponseEntity<Object> indexDescuentos(){
        List<Descuento> descuentos = null;
        try{
            descuentos = productoService.findAllDescuentos();
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        if(descuentos == null){
            return ResponseHandler.generateResponse(
                    "No hay registros",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                descuentos
        );
    }

    @PostMapping("/descuentos")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> crearDescuento(
            @Valid @RequestBody Descuento descuento, BindingResult result
    ){
        Descuento nuevoDescuento =null;
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map( err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Error al validar el formulario",
                    HttpStatus.BAD_REQUEST,
                    errors
            );
        }
        try {
            nuevoDescuento = productoService.registrarDescuento(descuento);
        }catch (DataAccessException e){
            return ResponseHandler.generateResponse(
                    "Error al transaccionar en base de datos",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
        return ResponseHandler.generateResponse(
                "Operación realizada correctamente",
                HttpStatus.OK,
                nuevoDescuento
        );

    }
}
