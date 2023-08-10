package br.com.pessoa.lembrete.Repository;

import br.com.pessoa.lembrete.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Pessoa findByNome(String nome);
}
