package br.com.jgsolutions.gems.repository;

import br.com.jgsolutions.gems.model.Pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

    @Query(value = "select a from Pessoa a where a.nomePessoa like %?1%")
    Page<Pessoa> findByNome(String nomePessoa, Pageable page);

    Page<Pessoa> findAll(Pageable page);

}