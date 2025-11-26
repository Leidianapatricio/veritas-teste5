package br.edu.ifpb.veritas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "collegiate")
public class Collegiate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private User coordenador;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collegiate_members",
        joinColumns = @JoinColumn(name = "collegiate_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;

    public Collegiate() {}
    public Collegiate(String name, String description, User coordenador) {
        this.name = name;
        this.description = description;
        this.coordenador = coordenador;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public User getCoordenador() { return coordenador; }
    public void setCoordenador(User coordenador) { this.coordenador = coordenador; }
    public List<User> getMembers() { return members; }
    public void setMembers(List<User> members) { this.members = members; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof Collegiate)) return false; Collegiate that = (Collegiate) o; return Objects.equals(id, that.id); }
    @Override public int hashCode() { return Objects.hash(id); }
}
