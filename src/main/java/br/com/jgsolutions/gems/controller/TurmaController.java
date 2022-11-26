package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.model.Turma;
import br.com.jgsolutions.gems.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gems/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Turma> buscarTodos() {
        return turmaService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Turma inserir(@RequestBody Turma turma) {
        return turmaService.inserir(turma);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Turma alterar(@RequestBody Turma turma) {
        return turmaService.alterar(turma);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        turmaService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
