package br.edu.ifpb.veritas.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votos")
public class Voto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assunto_id")
    private Processo assunto;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String escolha;

    private LocalDateTime votadoEm = LocalDateTime.now();

    public Voto() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Processo getAssunto() { return assunto; } public void setAssunto(Processo assunto) { this.assunto = assunto; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
    public String getEscolha() { return escolha; } public void setEscolha(String escolha) { this.escolha = escolha; }
    public LocalDateTime getVotadoEm() { return votadoEm; } public void setVotadoEm(LocalDateTime votadoEm) { this.votadoEm = votadoEm; }
}
