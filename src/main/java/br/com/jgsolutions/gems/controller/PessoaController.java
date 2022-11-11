package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.exception.BadResourceException;
import br.com.jgsolutions.gems.exception.ResourceAlreadyExistsException;
import br.com.jgsolutions.gems.exception.ResourceNotFoundException;
import br.com.jgsolutions.gems.model.Aluno;
import br.com.jgsolutions.gems.model.Pessoa;
import br.com.jgsolutions.gems.service.PessoaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;


@RestController
@RequestMapping("/gems/pessoa")
//@Tag(name = "pessoa", description = "API para CRUD de pessoa")
public class PessoaController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/", consumes =
            MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Pessoa>> findAll(
            @RequestBody(required=false) String nome, Pageable pageable){
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(pessoaService.findAll(pageable));
        }
        else {
            return ResponseEntity.ok(pessoaService.findAllByNome(nome, pageable));
        }
    }

    @GetMapping(value = "/{id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> findAlunoById(@PathVariable long id) {
        try {
        	Pessoa pessoa = pessoaService.findById(id);
            return ResponseEntity.ok(pessoa);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Pessoa> addAluno(@RequestBody Pessoa pessoa)
            throws URISyntaxException {
        try {
            Pessoa novoPessoa = pessoaService.save(pessoa);
            return ResponseEntity.created(new URI("/pessoa/" + novoPessoa.getId())).body(pessoa);
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> updateAluno(@Valid @RequestBody Pessoa pessoa, @PathVariable long id) {
        try {
        	pessoa.setId(id);
            pessoaService.update(pessoa);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteAlunoById(@PathVariable long id){
        try {
            pessoaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
