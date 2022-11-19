package br.com.jgsolutions.gems.repository;

import br.com.jgsolutions.gems.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long> {
}
