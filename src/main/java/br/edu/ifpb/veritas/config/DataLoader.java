package br.edu.ifpb.veritas.config;

import br.edu.ifpb.veritas.models.Role;
import br.edu.ifpb.veritas.models.User;
import br.edu.ifpb.veritas.repository.RoleRepository;
import br.edu.ifpb.veritas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (roleRepo.count() == 0) {
                roleRepo.save(new Role("ADMIN", "Administrador"));
                roleRepo.save(new Role("COORDENADOR", "Coordenador"));
                roleRepo.save(new Role("PROFESSOR", "Professor"));
                roleRepo.save(new Role("ALUNO", "Aluno"));
            }
            if (userRepo.count() == 0) {
                var adminRole = roleRepo.findByName("ADMIN").orElseThrow();
                User admin = new User("admin@veritas.com", encoder.encode("1234"), Set.of(adminRole));
                userRepo.save(admin);
            }
        };
    }
}
