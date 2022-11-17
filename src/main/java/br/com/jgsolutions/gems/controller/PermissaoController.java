package br.com.jgsolutions.gems.controller;

import br.com.jgsolutions.gems.exception.BadResourceException;
import br.com.jgsolutions.gems.exception.ResourceAlreadyExistsException;
import br.com.jgsolutions.gems.exception.ResourceNotFoundException;
import br.com.jgsolutions.gems.model.Permissao;
import br.com.jgsolutions.gems.service.PermissaoService;

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
@RequestMapping("/gems/permissao")
//@Tag(name = "permissao", description = "API para CRUD de permissoes")
public class PermissaoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping(value = "/", consumes =
            MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Permissao>> findAll(
            @RequestBody(required=false) String nomePermissao, Pageable pageable){
        if (StringUtils.isEmpty(nomePermissao)) {
            return ResponseEntity.ok(permissaoService.findAll(pageable));
        }
        else {
            return ResponseEntity.ok(permissaoService.findAllByNome(nomePermissao, pageable));
        }
    }

    @GetMapping(value = "/{id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Permissao> findAlunoById(@PathVariable long id) {
        try {
            Permissao permissao = permissaoService.findById(id);
            return ResponseEntity.ok(permissao);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Permissao> addPermissao(@RequestBody Permissao permissao)
            throws URISyntaxException {
        try {
            Permissao novaPermissao = permissaoService.save(permissao);
            return ResponseEntity.created(new URI("/permissao/" + novaPermissao.getId())).body(permissao);
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Permissao> updatePermissao(@Valid @RequestBody Permissao permissao, @PathVariable long id) {
        try {
            permissao.setId(id);
            permissaoService.update(permissao);
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
    public ResponseEntity<Void> deletePermissaoById(@PathVariable long id){
        try {
        	permissaoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
