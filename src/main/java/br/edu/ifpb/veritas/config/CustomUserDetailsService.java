package br.edu.ifpb.veritas.config;

import br.edu.ifpb.veritas.models.User;
import br.edu.ifpb.veritas.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // Se for usuário anônimo → NÃO tentar buscar no banco
    if (username == null || username.isBlank() || username.equals("anonymousUser")) {
        throw new UsernameNotFoundException("Usuário anônimo");
    }

    User u = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    var authorities = u.getRoles().stream()
            .map(r -> {
                String roleName = r.getName();
                if (!roleName.startsWith("ROLE_")) {
                    roleName = "ROLE_" + roleName;
                }
                return new SimpleGrantedAuthority(roleName);
            })
            .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(
            u.getUsername(),
            u.getPassword(),
            authorities
    );
}


}
