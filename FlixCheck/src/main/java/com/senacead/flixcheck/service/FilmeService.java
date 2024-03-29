package com.senacead.flixcheck.service;

import com.senacead.flixcheck.model.FilmeEntity;
import com.senacead.flixcheck.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // CRUD
    public FilmeEntity criar(FilmeEntity filme) {
        filme.setId(null);
        filmeRepository.save(filme);
        return filme;
    }
    
    public List<FilmeEntity> listarTodos() {
        return filmeRepository.findAll();
    }

    public FilmeEntity buscarPorId(Integer id) {
        return filmeRepository.findById(id).orElseThrow();
    }

    public void excluir(Integer id) {
        filmeRepository.deleteById(id);
    }

    public FilmeEntity atualizarFilme(Integer id, FilmeEntity filmeEnviado) {
        FilmeEntity filmeEncontrado = buscarPorId(id);
        filmeEncontrado.setNome(filmeEnviado.getNome());
        filmeEncontrado.setAutor(filmeEnviado.getAutor());
        filmeEncontrado.setGenero(filmeEnviado.getGenero());
        filmeEncontrado.setData(filmeEnviado.getData());
        filmeEncontrado.setSinopse(filmeEnviado.getSinopse());
        filmeEncontrado.setAssistido(filmeEnviado.isAssistido());
        filmeRepository.save(filmeEncontrado);
        return filmeEncontrado;
    }
    
}
