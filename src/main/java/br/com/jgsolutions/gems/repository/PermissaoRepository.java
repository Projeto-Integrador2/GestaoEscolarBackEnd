package br.com.jgsolutions.gems.repository;

import br.com.jgsolutions.gems.model.Permissao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PermissaoRepository extends PagingAndSortingRepository<Permissao, Long>, JpaSpecificationExecutor<Permissao> {

    @Query(value = "select a from Permissao a where a.nomePermissao like %?1%")
    Page<Permissao> findByNome(String nomePermissao, Pageable page);

    Page<Permissao> findAll(Pageable page);

}