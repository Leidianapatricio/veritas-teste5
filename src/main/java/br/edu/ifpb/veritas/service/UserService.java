package br.edu.ifpb.veritas.service;

import br.edu.ifpb.veritas.models.User;
import br.edu.ifpb.veritas.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<User> findAll() { return repo.findAll(); }
    public Optional<User> findById(Long id) { return repo.findById(id); }
    public List<User> findAllById(List<Long> ids) { return repo.findAllById(ids); }
    public User save(User u) {
        if (u.getPassword() != null && !u.getPassword().startsWith("$2a$")) {
            u.setPassword(encoder.encode(u.getPassword()));
        }
        return repo.save(u);
    }
    public void delete(Long id) { repo.deleteById(id); }
}
