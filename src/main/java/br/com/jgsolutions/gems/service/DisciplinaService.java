package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.model.Disciplina;
import br.com.jgsolutions.gems.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> buscarTodos() {
        return disciplinaRepository.findAll();
    }

    public Disciplina inserir(Disciplina disciplina) {
        disciplina.setDataCriacao(new Date());
        Disciplina disciplinaNovo = disciplinaRepository.saveAndFlush(disciplina);
        return disciplinaNovo;
    }

    public Disciplina alterar(Disciplina disciplina) {
        disciplina.setDataCriacao(new Date());
        return disciplinaRepository.saveAndFlush(disciplina);
    }

    public void excuir(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id).get();
        disciplinaRepository.delete(disciplina);
    }
}
