package br.edu.ifpb.veritas.service;

import br.edu.ifpb.veritas.models.Voto;
import br.edu.ifpb.veritas.repository.VotoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService {
    private final VotoRepository repo;
    public VotoService(VotoRepository repo) { this.repo = repo; }
    public List<Voto> findAll() { return repo.findAll(); }
    public Optional<Voto> findById(Long id) { return repo.findById(id); }
    public Voto save(Voto v) { return repo.save(v); }
    public void delete(Long id) { repo.deleteById(id); }
}
