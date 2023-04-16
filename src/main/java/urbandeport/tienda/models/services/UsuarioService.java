package urbandeport.tienda.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urbandeport.tienda.models.dao.IUsuarioDao;
import urbandeport.tienda.models.dao.IVerificationTokenDao;
import urbandeport.tienda.models.entity.Role;
import urbandeport.tienda.models.entity.Usuario;
import urbandeport.tienda.models.entity.VerificationToken;
import urbandeport.tienda.util.exception.InvalidVerificationTokenException;
import urbandeport.tienda.util.exception.NeedVerificationException;
import urbandeport.tienda.util.exception.UserAlreadyExistsException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IVerificationTokenDao verificationTokenDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByEmail(email);
        if(usuario == null){
            logger.error("Error, el usuario no existe en la BD");
            throw new UsernameNotFoundException("El usuario no existe");
        }
        if(!usuario.getVerificado()){
            logger.error("Error, el usuario necesita verificar su correo");
            throw new NeedVerificationException("Necesitas validar tu correo");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
        return new User(usuario.getEmail(),usuario.getPassword(),usuario.getVerificado(),true,true,true,authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getAllUsers() {
        return usuarioDao.findAll();
    }

    @Override
    public Page<Usuario> getAllUsers(Pageable pageable) {
        //TODO
        return null;
    }

    @Override
    @Transactional
    public Usuario registroUsuario(Usuario usuarioReq) {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = usuarioDao.findByEmail(usuarioReq.getEmail());
        if(usuario != null){
            throw new UserAlreadyExistsException(
                    "El usuario con el email "+usuarioReq.getEmail()+" ya existe."
            );
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioReq.getNombre());
        nuevoUsuario.setApellidos(usuarioReq.getApellidos());
        nuevoUsuario.setEmail(usuarioReq.getEmail());
        nuevoUsuario.setPassword(bCryptPasswordEncoder.encode(usuarioReq.getPassword()));
        nuevoUsuario.setVerificado(false);

        Role role = new Role(1L,"ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        nuevoUsuario.setRoles(roles);

        logger.info("Persistiendo el usuario "+nuevoUsuario.toString());
        return usuarioDao.save(nuevoUsuario);
    }

    @Override
    @Transactional
    public void saveUserVerificationToken(Usuario usuarioReq, String token) {
        VerificationToken verificationToken = new VerificationToken(token,usuarioReq);
        logger.info("Persistiendo el token de verificacion "+token+" para el usuario "+usuarioReq.getNombre());
        verificationTokenDao.save(verificationToken);
    }

    @Override
    @Transactional
    public String validateToken(String theToken) {
        VerificationToken token = verificationTokenDao.findByToken(theToken);
        if(token == null){
            return "Token invalido";
        }
        Usuario usuario = token.getUsuario();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            verificationTokenDao.delete(token);
            return "Token expirado";
        }
        usuario.setVerificado(true);
        usuarioDao.save(usuario);
        return "Validado";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenDao.findByToken(oldToken);
        if(verificationToken == null){
            throw new InvalidVerificationTokenException("El token no es valido");
        }
        VerificationToken tokenExpirationTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(tokenExpirationTime.getTokenExpirationTime());
        return verificationTokenDao.save(verificationToken);
    }

}
