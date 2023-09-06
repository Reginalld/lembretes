package br.com.pessoa.lembrete.Controller;

import br.com.pessoa.lembrete.Entity.Lembrete;
import br.com.pessoa.lembrete.Entity.Pessoa;
import br.com.pessoa.lembrete.Repository.LembreteRepository;
import br.com.pessoa.lembrete.Repository.PessoaRepository;
import br.com.pessoa.lembrete.Service.LembreteService;
import br.com.pessoa.lembrete.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lembrete")
public class LembreteController {

    @Autowired
    private LembreteRepository lembreteRep;
    @Autowired
    private LembreteService lembreteServ;

    @GetMapping("/{id}")
    public ResponseEntity<Lembrete> findByIdPath(@PathVariable("id") final Long id) {
        final Lembrete lembrete = this.lembreteRep.findById(id).orElse(null);
        return ResponseEntity.ok(lembrete);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Lembrete>> List(){
        return ResponseEntity.ok(this.lembreteRep.findAll());

    }


   /* @GetMapping("/nome/{nome}")
    public ResponseEntity<?> Nome(@RequestParam("nome") String nome){

        List<Lembrete> lembrete = lembreteRep.findByNome(nome);
        return ResponseEntity.ok(lembrete);

    }*/




    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Lembrete lembrete){
        try {
            this.lembreteServ.createLembrete(lembrete);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Lembrete lembrete){
        try {
            final Lembrete lembrete1 = this.lembreteRep.findById(id).orElse(null);

            if (lembrete1 == null || lembrete1.getId() != (lembrete.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            lembreteServ.atualizaLembrete(lembrete);
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

            this.lembreteRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



}
