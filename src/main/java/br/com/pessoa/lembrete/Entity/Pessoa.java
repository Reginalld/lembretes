package br.com.pessoa.lembrete.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "pessoas", schema = "public")
public class Pessoa {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Getter
    @Setter
    @Column(name = "nome", nullable = false, unique = true, length = 100)
    private String nome;

    @Getter
    @Setter
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Lembrete> lembretes;

    public Pessoa() {
    }

    public Pessoa(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
