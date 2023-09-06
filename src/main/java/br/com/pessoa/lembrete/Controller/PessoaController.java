package br.com.pessoa.lembrete.Controller;

import br.com.pessoa.lembrete.Entity.Pessoa;
import br.com.pessoa.lembrete.Repository.PessoaRepository;
import br.com.pessoa.lembrete.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRep;
    @Autowired
    private PessoaService pessoaServ;

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable("id") final Long id) {
        final Pessoa pessoas = this.pessoaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pessoas);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Pessoa>> List(){
        return ResponseEntity.ok(this.pessoaRep.findAll());

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pessoa>> Nome(@PathVariable("nome") String nome){
        List<Pessoa> pessoa = pessoaRep.findByNome(nome);
        return ResponseEntity.ok(pessoa);

    }


    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Pessoa pessoa){
        try {
            this.pessoaServ.createPessoa(pessoa);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Pessoa pessoa){
        try {
            final Pessoa pessoa1 = this.pessoaRep.findById(id).orElse(null);

            if (pessoa1 == null || pessoa1.getId() != (pessoa.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            pessoaServ.atualizaPessoa(pessoa);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        try {

            this.pessoaRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



}
