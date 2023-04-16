package urbandeport.tienda.models.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "No puede estar vacio.")
    @Size(min = 4,max = 12,message = "El tamaño debe estar entre 4 y 12 caracteres")
    private String nombre;
    @NotEmpty(message = "No puede estar vacio.")
    private String apellidos;
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "El correo no puede estar vacio.")
    @Email(message = "No es una dirección de correo valida")
    private String email;
    @Column(nullable = false,length = 60)
    @NotEmpty(message = "No puede estar vacio.")
    private String password;
    private Boolean verificado;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"),
            uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "roles_id"})})
    private List<Role> roles;

    public void addRole(Role role){
        this.roles.add(role);
    }
    public void removeRole(long roleId){
        Role role = this.roles.stream().filter(t -> t.getId() == roleId).findFirst().orElse(null);
        if (role != null) {
            this.roles.remove(role);
        }
    }


    private static final long serialVersionUID = 1L;
}
