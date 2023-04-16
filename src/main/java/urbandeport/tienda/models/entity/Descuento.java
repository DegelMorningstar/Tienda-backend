package urbandeport.tienda.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "descuentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Descuento implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "No puede estar vacio.")
    @Size(min = 4,message = "El tamaño debe ser mayor a 4 caracteres")
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    @NotNull(message = "No puede estar vacio.")
    @Min(value = 0,message = "Debe ser mayor a 0")
    private Float porcentaje;

    private Boolean active;

    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name="update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @PrePersist
    private void prePersists (){
        this.createAt = new Date();
        this.updateAt = new Date();
    }

}
