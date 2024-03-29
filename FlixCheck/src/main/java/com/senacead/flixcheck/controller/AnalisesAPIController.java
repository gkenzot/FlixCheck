package com.senacead.flixcheck.controller;

import com.senacead.flixcheck.model.AnalisesEntity;
import com.senacead.flixcheck.service.AnalisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analises")
public class AnalisesAPIController {

    @Autowired
    private AnalisesService analisesService;

    @PostMapping("/adicionar/{idFilme}")
    public ResponseEntity<AnalisesEntity> addAnalise(@PathVariable Integer idFilme, @RequestBody AnalisesEntity analise) {
        analise.getFilme().setId(idFilme);

        var novaAnalise = analisesService.criar(analise);
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idFilme}")
    public ResponseEntity<List<AnalisesEntity>> pesquisar(@PathVariable Integer idFilme) {
        List<AnalisesEntity> analisesEncontradas = analisesService.buscarPorFilme(idFilme);
        return new ResponseEntity<>(analisesEncontradas, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        analisesService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public AnalisesEntity atualizarAnalise(@PathVariable Integer id, @RequestBody AnalisesEntity analiseEnviada) {
        return analisesService.atualizarAnalise(id, analiseEnviada);
    }

}
