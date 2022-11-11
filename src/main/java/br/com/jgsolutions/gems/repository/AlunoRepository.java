package br.com.jgsolutions.gems.repository;

import br.com.jgsolutions.gems.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
