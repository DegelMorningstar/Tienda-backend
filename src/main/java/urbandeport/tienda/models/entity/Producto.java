package urbandeport.tienda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //lo genera el servicio
    private String slug;
    //se asigna la ruta
    private String portada;
    @Column(nullable = false)
    @NotEmpty(message = "No puede estar vacio.")
    @Size(min = 4,message = "El tamaño debe ser mayor a 4 caracteres")
    private String titulo;
    @Column(nullable = false)
    @NotEmpty(message = "No puede estar vacio.")
    @Size(min = 4,message = "El tamaño debe ser mayor a 4 caracteres")
    private String descripcion;
    //lo genera el servicio
    private String sku;
    @Column(nullable = false)
    @NotNull(message = "No puede estar vacio.")
    private Double precio;
    @Column(nullable = false)
    @NotNull(message = "No puede estar vacio.")
    @Min(value = 0,message = "No puede ser menor a 0")
    private Long stock;
    private Long nventas;
    @Column(nullable = false)
    @NotNull(message = "No puede estar vacio.")
    @Min(value = 0,message = "Debe ser mayor a 0")
    private Double peso;
    private Boolean estado;
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name="update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "descuento_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Descuento descuento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Categoria categoria;

    @PrePersist
    private void prePersists (){
        this.createAt = new Date();
        this.updateAt = new Date();
    }

    private static final long serialVersionUID = 1L;
}
