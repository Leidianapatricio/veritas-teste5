package br.edu.ifpb.veritas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "processos")
public class Processo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String assunto;

    @Column(length = 2000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;

    @ManyToOne
    @JoinColumn(name = "collegiate_id")
    private Collegiate colegiado;

    private LocalDateTime criadoEm = LocalDateTime.now();

    private String status;

    public Processo() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getTitulo() { return assunto; } public void setTitulo(String titulo) { this.assunto = titulo; }
    public String getDescricao() { return descricao; } public void setDescricao(String descricao) { this.descricao = descricao; }
    public User getAutor() { return autor; } public void setAutor(User autor) { this.autor = autor; }
    public Collegiate getColegiado() { return colegiado; } public void setColegiado(Collegiate colegiado) { this.colegiado = colegiado; }
    public LocalDateTime getCriadoEm() { return criadoEm; } public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}
