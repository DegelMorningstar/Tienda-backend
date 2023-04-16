package urbandeport.tienda.util.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import urbandeport.tienda.models.entity.Usuario;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Usuario user;
    private String applicationUrl;

    public RegistrationCompleteEvent(Usuario user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
