package br.com.pessoa.lembrete.Service;


import br.com.pessoa.lembrete.Entity.Lembrete;
import br.com.pessoa.lembrete.Entity.Pessoa;
import br.com.pessoa.lembrete.Repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRep;

    @Transactional(rollbackFor = Exception.class)
    public void createLembrete(final Lembrete lembrete){
        Assert.isTrue(lembrete.getLembrete() != null, "Lembrete não pode ser nulo");
        Assert.isTrue(lembrete.getLembrete().length() < 500, "O tamanho máxima do lembrete é até 500 caracteres");


        lembreteRep.save(lembrete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaLembrete(final Lembrete lembrete){
        Assert.isTrue(lembrete.getLembrete() != null, "Lembrete não pode ser nulo");
        Assert.isTrue(lembrete.getLembrete().length() < 500, "O tamanho máxima do lembrete é até 500 caracteres");


        lembreteRep.save(lembrete);

    }


}
