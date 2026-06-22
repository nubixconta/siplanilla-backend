package sv.edu.ues.fia.siplanilla_backend.modules.seguridad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.entity.Usuario;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRepository;
import sv.edu.ues.fia.siplanilla_backend.modules.seguridad.repository.UsuarioRolRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<GrantedAuthority> authorities = usuarioRolRepository.findByUsuarioWithRol(usuario)
                .stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRol().getRolNombre()))
                .collect(Collectors.toList());

        usuario.setAuthorities(authorities);
        return usuario;
    }
}
