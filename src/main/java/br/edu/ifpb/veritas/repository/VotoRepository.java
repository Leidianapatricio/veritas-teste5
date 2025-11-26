package br.edu.ifpb.veritas.repository;

import br.edu.ifpb.veritas.models.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> { }
