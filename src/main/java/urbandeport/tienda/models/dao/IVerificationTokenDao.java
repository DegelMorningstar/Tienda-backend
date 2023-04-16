package urbandeport.tienda.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import urbandeport.tienda.models.entity.VerificationToken;

public interface IVerificationTokenDao extends JpaRepository<VerificationToken,Long> {
    public VerificationToken findByToken(String token);
}
