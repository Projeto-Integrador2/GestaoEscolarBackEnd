package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.model.Professor;
import br.com.jgsolutions.gems.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gems/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Professor> buscarTodos() {
        return professorService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Professor inserir(@RequestBody Professor professor) {
        return professorService.inserir(professor);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Professor alterar(@RequestBody Professor professor) {
        return professorService.alterar(professor);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        professorService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
