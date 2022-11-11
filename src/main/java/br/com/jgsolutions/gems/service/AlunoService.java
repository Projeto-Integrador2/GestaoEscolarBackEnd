package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.model.Aluno;
import br.com.jgsolutions.gems.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> buscarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno inserir(Aluno aluno) {
        aluno.setDataCriacao(new Date());
        Aluno alunoNovo = alunoRepository.saveAndFlush(aluno);
        return alunoNovo;
    }

    public Aluno alterar(Aluno aluno) {
        aluno.setDataCriacao(new Date());
        return alunoRepository.saveAndFlush(aluno);
    }

    public void excuir(Long id) {
        Aluno aluno = alunoRepository.findById(id).get();
        alunoRepository.delete(aluno);
    }
}
