package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.model.Aluno;
import br.com.jgsolutions.gems.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/gems/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Aluno> buscarTodos() {
        return alunoService.buscarTodos();}

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Aluno inserir(@RequestBody Aluno aluno) {
        return alunoService.inserir(aluno);}

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Aluno alterar(@RequestBody Aluno aluno) {
        return alunoService.alterar(aluno);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        alunoService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
