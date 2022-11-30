package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.model.Professor;
import br.com.jgsolutions.gems.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> buscarTodos() {
        return professorRepository.findAll();
    }

    public Professor inserir(Professor professor) {
        professor.setDataCriacao(new Date());
        Professor professorNovo = professorRepository.saveAndFlush(professor);
        return professorNovo;
    }

    public Professor alterar(Professor professor) {
        professor.setDataCriacao(new Date());
        return professorRepository.saveAndFlush(professor);
    }

    public void excuir(Long id) {
        Professor professor = professorRepository.findById(id).get();
        professorRepository.delete(professor);
    }
}
