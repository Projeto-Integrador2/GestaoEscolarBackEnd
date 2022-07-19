package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.exception.BadResourceException;
import br.com.jgsolutions.gems.exception.ResourceAlreadyExistsException;
import br.com.jgsolutions.gems.exception.ResourceNotFoundException;
import br.com.jgsolutions.gems.model.Permissao;
import br.com.jgsolutions.gems.repository.PermissaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    private boolean existsById(Long id){
        return permissaoRepository.existsById(id);
    }

    public Permissao findById(Long id) throws ResourceNotFoundException {
        Permissao permissao = permissaoRepository.findById(id).orElse(null);
        if(permissao == null) {
            throw new ResourceNotFoundException("Permissão não encontrada com a id: " + id);
        }
        else return permissao;
    }

    public Page<Permissao> findAll(Pageable pageable) {
        return permissaoRepository.findAll(pageable);
    }

    public Page<Permissao> findAllByNome(String nomePermissao, Pageable page) {
        Page<Permissao> permissoes = permissaoRepository.findByNome(nomePermissao, page);
        return permissoes;
    }

    public Permissao save(Permissao permissao) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(permissao.getNomePermissao())) {
            if (permissao.getId() != null && existsById(permissao.getId())) {
                throw new ResourceAlreadyExistsException("Permissão com a id: " + permissao.getId() + " já existe");
            }
            return permissaoRepository.save(permissao);
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar a permissão");
            exc.addErrorMessage("Permissão está vazia ou é nula");
            throw exc;
        }
    }

    public void update(Permissao permissao) throws BadResourceException, ResourceNotFoundException{
        if (!StringUtils.isEmpty(permissao.getNomePermissao())){
            if (!existsById(permissao.getId())){
                throw  new ResourceNotFoundException("Permissão não encontrada com a id: " + permissao.getId());
            }
            permissaoRepository.save(permissao);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar a permissão");
            exc.addErrorMessage("Permissão está nula ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Permissão não encontrada com a id: " + id);
        }
        else{
        	permissaoRepository.deleteById(id);
        }
    }

    public Long count() {
        return permissaoRepository.count();
    }

}
