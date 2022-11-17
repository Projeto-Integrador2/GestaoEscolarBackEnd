package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.model.Curso;
import br.com.jgsolutions.gems.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gems/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Curso> buscarTodos() {
        return cursoService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Curso inserir(@RequestBody Curso curso) {
        return cursoService.inserir(curso);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Curso alterar(@RequestBody Curso curso) {
        return cursoService.alterar(curso);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        cursoService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
