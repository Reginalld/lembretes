package br.com.pessoa.lembrete.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lembretes", schema= "public")
public class Lembrete {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private long id;

    @Getter @Setter
    @Column(name = "lembrete",nullable = false,length = 500)
    private String lembrete;

    @Setter
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

}
