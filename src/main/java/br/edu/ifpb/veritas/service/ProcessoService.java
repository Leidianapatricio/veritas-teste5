package br.edu.ifpb.veritas.service;

import br.edu.ifpb.veritas.models.Processo;
import br.edu.ifpb.veritas.repository.ProcessoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessoService {
    private final ProcessoRepository repo;
    public ProcessoService(ProcessoRepository repo) { this.repo = repo; }
    public List<Processo> findAll() { return repo.findAll(); }
    public Optional<Processo> findById(Long id) { return repo.findById(id); }
    public Processo save(Processo a) { return repo.save(a); }
    public void delete(Long id) { repo.deleteById(id); }
}
