package br.com.jgsolutions.gems.repository;

import br.com.jgsolutions.gems.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
