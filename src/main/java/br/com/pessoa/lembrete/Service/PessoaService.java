package br.com.pessoa.lembrete.Service;

import br.com.pessoa.lembrete.Entity.Pessoa;
import br.com.pessoa.lembrete.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PessoaService {


    @Autowired
    private PessoaRepository pessoaRep;


    @Transactional(rollbackFor = Exception.class)
    public void createPessoa(final Pessoa pessoa){
        Assert.isTrue(pessoa.getNome() != null, "Nome não pode ser nulo");
        Assert.isTrue(pessoa.getNome().length() < 50, "O tamanha máxima do nome é até 50 caracteres");

        pessoaRep.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPessoa(final Pessoa pessoa){
        Assert.isTrue(pessoa.getNome() != null, "Nome não pode ser nulo");
        Assert.isTrue(pessoa.getNome().length() < 50, "O tamanha máxima do nome é até 50 caracteres");

        pessoaRep.save(pessoa);
    }

}