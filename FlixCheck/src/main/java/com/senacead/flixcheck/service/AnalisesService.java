package com.senacead.flixcheck.service;

import com.senacead.flixcheck.model.AnalisesEntity;
import com.senacead.flixcheck.repository.AnalisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnalisesService {

    @Autowired
    private AnalisesRepository analisesRepository;

    // CRUD
    public AnalisesEntity criar(AnalisesEntity analise) {
        analise.setId(null);
        analisesRepository.save(analise);
        return analise;
    }

    public List<AnalisesEntity> buscarPorFilme(Integer idFilme) {
        return analisesRepository.findByFilmeId(idFilme);
    }

    public AnalisesEntity buscarPorId(Integer id) {
        Optional<AnalisesEntity> analiseOptional = analisesRepository.findById(id);
        return analiseOptional.orElse(null);
    }

    public void excluir(Integer id) {
        analisesRepository.deleteById(id);
    }

    public AnalisesEntity atualizarAnalise(Integer id, AnalisesEntity analiseEnviada) {
        AnalisesEntity analiseEncontrada = buscarPorId(id);

        if (analiseEncontrada != null) {
            analiseEncontrada.setFilme(analiseEnviada.getFilme());
            analiseEncontrada.setDescricao(analiseEnviada.getDescricao());
            analiseEncontrada.setNota(analiseEnviada.getNota());

            analisesRepository.save(analiseEncontrada);
        }

        return analiseEncontrada;
    }

    public void excluirAnalisesPorFilme(Integer idFilme) {
        List<AnalisesEntity> analisesDoFilme = analisesRepository.findByFilmeId(idFilme);

        for (AnalisesEntity analise : analisesDoFilme) {
            analisesRepository.deleteById(analise.getId());
        }
    }

}
