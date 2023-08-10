package br.com.pessoa.lembrete.Repository;

import br.com.pessoa.lembrete.Entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {
    //List<Lembrete> findByNome(String nome);
}
