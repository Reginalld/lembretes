package br.com.pessoa.lembrete.Repository;

import br.com.pessoa.lembrete.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    List<Pessoa> findByNome(String nome);
}
