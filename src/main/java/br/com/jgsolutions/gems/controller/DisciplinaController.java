package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.model.Disciplina;
import br.com.jgsolutions.gems.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/gems/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Disciplina> buscarTodos() {
        return disciplinaService.buscarTodos();}

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Disciplina inserir(@RequestBody Disciplina disciplina) {
        return disciplinaService.inserir(disciplina);}

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Disciplina alterar(@RequestBody Disciplina disciplina) {
        return disciplinaService.alterar(disciplina);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        disciplinaService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
