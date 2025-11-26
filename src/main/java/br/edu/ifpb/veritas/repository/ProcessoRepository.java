package br.edu.ifpb.veritas.repository;

import br.edu.ifpb.veritas.models.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> { }
