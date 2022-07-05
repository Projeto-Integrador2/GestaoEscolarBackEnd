package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.exception.BadResourceException;
import br.com.jgsolutions.gems.exception.ResourceAlreadyExistsException;
import br.com.jgsolutions.gems.exception.ResourceNotFoundException;
import br.com.jgsolutions.gems.model.Pessoa;
import br.com.jgsolutions.gems.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    private boolean existsById(Long id){
        return pessoaRepository.existsById(id);
    }

    public Pessoa findById(Long id) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if(pessoa == null) {
            throw new ResourceNotFoundException("Pessoa não encontrada com o id: " + id);
        }
        else return pessoa;
    }

    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Page<Pessoa> findAllByNome(String nome, Pageable page) {
        Page<Pessoa> pessoas = pessoaRepository.findByNome(nome, page);
        return pessoas;
    }

    public Pessoa save(Pessoa pessoa) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(pessoa.getNomePessoa())) {
            if (pessoa.getId() != null && existsById(pessoa.getId())) {
                throw new ResourceAlreadyExistsException("Pessoa com id: " + pessoa.getId() + " já existe");
            }
            return pessoaRepository.save(pessoa);
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar Pessoa");
            exc.addErrorMessage("Pessoa está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Pessoa pessoa) throws BadResourceException, ResourceNotFoundException{
        if (!StringUtils.isEmpty(pessoa.getNomePessoa())){
            if (!existsById(pessoa.getId())){
                throw  new ResourceNotFoundException("pessoa não encontrado com o id: " + pessoa.getId());
            }
            pessoaRepository.save(pessoa);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar elemento Pessoa");
            exc.addErrorMessage("Pessoa está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Pessoa não foi encontrada com o id: " + id);
        }
        else{
            pessoaRepository.deleteById(id);
        }
    }

    public Long count() {
        return pessoaRepository.count();
    }

}
