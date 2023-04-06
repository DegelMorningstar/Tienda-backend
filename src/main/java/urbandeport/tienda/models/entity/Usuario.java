package urbandeport.tienda.models.entity;


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
    @NotNull(message = "No puede estar vacio.")
    private Date createAt;
    @Column(name="update_at")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "No puede estar vacio.")
    private Date updateAt;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JoinTable(name = "user_authorities",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verificado=" + verificado +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    private static final long serialVersionUID = 1L;
}
