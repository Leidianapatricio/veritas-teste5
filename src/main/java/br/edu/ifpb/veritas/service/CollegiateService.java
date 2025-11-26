package br.edu.ifpb.veritas.service;

import br.edu.ifpb.veritas.models.Collegiate;
import br.edu.ifpb.veritas.repository.CollegiateRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CollegiateService {
    private final CollegiateRepository repo;
    public CollegiateService(CollegiateRepository repo) { this.repo = repo; }
    public List<Collegiate> findAll() { return repo.findAll(); }
    public Optional<Collegiate> findById(Long id) { return repo.findById(id); }
    public Collegiate save(Collegiate c) { return repo.save(c); }
    public void delete(Long id) { repo.deleteById(id); }
}
