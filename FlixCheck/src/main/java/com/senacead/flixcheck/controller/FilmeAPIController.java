package com.senacead.flixcheck.controller;

import com.senacead.flixcheck.model.FilmeEntity;
import com.senacead.flixcheck.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeAPIController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/listar")
    public ResponseEntity<List<FilmeEntity>> listar() {
        List<FilmeEntity> listagem = filmeService.listarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@RequestBody FilmeEntity filme) {
        var novoFilme = filmeService.criar(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<FilmeEntity> pesquisar(@PathVariable Integer id) {
        FilmeEntity filmeEncontrado = filmeService.buscarPorId(id);
        return new ResponseEntity<>(filmeEncontrado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        filmeService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @PutMapping("/atualizar/{id}")
   public ResponseEntity<FilmeEntity> atualizandoFilme(@PathVariable Integer id, @RequestBody FilmeEntity filme){
       var filmeEditado = filmeService.atualizarFilme(id, filme);
       return new ResponseEntity<>(filmeEditado, HttpStatus.OK);
   }   
}
