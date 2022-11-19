package br.com.jgsolutions.gems.service;

import br.com.jgsolutions.gems.model.Curso;
import br.com.jgsolutions.gems.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

    public Curso inserir(Curso curso) {
        curso.setDataCriacao(new Date());
        Curso cursoNovo = cursoRepository.saveAndFlush(curso);
        return cursoNovo;
    }

    public Curso alterar(Curso curso) {
        curso.setDataCriacao(new Date());
        return cursoRepository.saveAndFlush(curso);
    }

    public void excuir(Long id) {
        Curso curso = cursoRepository.findById(id).get();
        cursoRepository.delete(curso);
    }
}
