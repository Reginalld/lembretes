package br.com.pessoa.lembrete;

import br.com.pessoa.lembrete.Controller.LembreteController;
import br.com.pessoa.lembrete.Controller.PessoaController;
import br.com.pessoa.lembrete.Entity.Lembrete;
import br.com.pessoa.lembrete.Entity.Pessoa;
import br.com.pessoa.lembrete.Repository.LembreteRepository;
import br.com.pessoa.lembrete.Repository.PessoaRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SistemaLembreteApplicationTest {

    @MockBean
    PessoaRepository pessoaRep;

    @MockBean
    LembreteRepository lembreteRep;

    @Autowired
    private final LembreteController controllerLembrete = new LembreteController();

    @Autowired
    private final PessoaController controller = new PessoaController();

    private List<Pessoa>pessoaList;

    private List<Lembrete>lembreteList;
    @BeforeEach
    void injectData() {
        Optional<Pessoa> pessoa = Optional.of(new Pessoa(1L, "Willian"));

        pessoaList = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa(1L,"Willian");
        Pessoa pessoa2 = new Pessoa(2L,"Douglas");

        pessoaList.add(pessoa1);
        pessoaList.add(pessoa2);

        Long id = 1L;
        Mockito.when(pessoaRep.findById(id)).thenReturn(pessoa);
        Mockito.when(pessoaRep.findAll()).thenReturn(pessoaList);
        Mockito.when(pessoaRep.findByNome("Willian")).thenReturn(pessoaList);

        // Injete a instância mock no controlador
        ReflectionTestUtils.setField(controller, "pessoaRep", pessoaRep);
    }

    @BeforeEach
    void injectData2(){
        Optional<Lembrete> lembrete = Optional.of(new Lembrete(1L, "Salve"));

        lembreteList = new ArrayList<>();
        Lembrete lembrete1 = new Lembrete(1L,"Salve");
        Lembrete lembrete2 = new Lembrete(2L,"Season");

        lembreteList.add(lembrete1);
        lembreteList.add(lembrete2);

        Long id = 1L;
        Mockito.when(lembreteRep.findById(id)).thenReturn(lembrete);
        Mockito.when(lembreteRep.findAll()).thenReturn(lembreteList);

        // Injete a instância mock no controlador
        ReflectionTestUtils.setField(controllerLembrete, "lembreteRep", lembreteRep);
    }

    @Test
    void testPessoaGet() {
        var pessoaController = controller.findById(1L);
        Long id = pessoaController.getBody().getId();
        System.out.println(id);
        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void testeLembreteGet() {
        var lembreteController = controllerLembrete.findByIdPath(1L);
        Long id = lembreteController.getBody().getId();
        System.out.println(id);
        Assert.assertEquals(1L, id, 0);
    }

    @Test
    void testPessoaList() {
        ResponseEntity<List<Pessoa>> pessoaController = controller.List();
        List<Pessoa> pessoaListController = pessoaController.getBody();

        Assert.assertNotNull(pessoaListController);
        Assert.assertEquals(pessoaList.size(), pessoaListController.size());

        for (int i = 0; i < pessoaList.size(); i++) {
            Assert.assertEquals(pessoaList.get(i), pessoaListController.get(i));
        }

    }

    @Test
    void testLembreteList() {
        ResponseEntity<List<Lembrete>> lembreteController = controllerLembrete.List();
        List<Lembrete> lembreteListController = lembreteController.getBody();

        Assert.assertNotNull(lembreteListController);
        Assert.assertEquals(lembreteList.size(), lembreteListController.size());

        for (int i = 0; i < lembreteList.size(); i++) {
            Assert.assertEquals(lembreteList.get(i), lembreteListController.get(i));
        }

    }

    @Test
    void testPessoaNome(){
        ResponseEntity<List<Pessoa>> pessoaController = controller.Nome("Willian");
        List<Pessoa> pessoaListController = pessoaController.getBody();

        Assert.assertNotNull(pessoaListController);
        Assert.assertEquals(pessoaList.size(), pessoaListController.size());
        Assert.assertEquals(pessoaList.get(1), pessoaListController.get(1));
    }

}
