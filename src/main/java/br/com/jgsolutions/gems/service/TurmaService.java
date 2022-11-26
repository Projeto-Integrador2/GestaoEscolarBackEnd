package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.model.Turma;
import br.com.jgsolutions.gems.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> buscarTodos() {
        return turmaRepository.findAll();
    }

    public Turma inserir(Turma turma) {
        turma.setDataCriacao(new Date());
        Turma turmaNovo = turmaRepository.saveAndFlush(turma);
        return turmaNovo;
    }

    public Turma alterar(Turma turma) {
        turma.setDataCriacao(new Date());
        return turmaRepository.saveAndFlush(turma);
    }

    public void excuir(Long id) {
        Turma turma = turmaRepository.findById(id).get();
        turmaRepository.delete(turma);
    }
}
