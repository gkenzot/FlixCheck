package com.senacead.flixcheck.controller;

import com.senacead.flixcheck.model.AnalisesEntity;
import com.senacead.flixcheck.model.FilmeEntity;
import com.senacead.flixcheck.service.FilmeService;
import com.senacead.flixcheck.service.AnalisesService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FlixCheckController {

    private List<FilmeEntity> listaFilme = new ArrayList<>();
    private List<AnalisesEntity> listaAnalises = new ArrayList<>();

    @Autowired
    FilmeService filmeService;

    @Autowired
    AnalisesService analisesService;

    @RequestMapping("/")
    public String index(@CookieValue(name = "pref-nome", defaultValue = "") String nome,
            @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema,
            @CookieValue(name = "darkMode", defaultValue = "off") String darkMode,
            Model model) {
        model.addAttribute("nome", nome);
        model.addAttribute("css", tema);
        model.addAttribute("darkMode", darkMode.equals("on"));
        return "index";
    }

    @GetMapping("/inicio")
    public String inicio2() {
        return "redirect:/";
    }

    @GetMapping("/cadastro")
    public String cadastramento(Model model) {
        List<String> generos = lerGenerosDoArquivo("src/main/resources/data/generos.txt");
        model.addAttribute("generos", generos);
        model.addAttribute("filme", new FilmeEntity());
        return "cadastro";
    }

    private List<String> lerGenerosDoArquivo(String caminhoArquivo) {
        try {
            return Files.lines(Paths.get(caminhoArquivo))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return List.of();
        }
    }

    @PostMapping("/gravar-filme")
    public String salvando(@ModelAttribute FilmeEntity filme) {
        filme.setAssistido(false);
        filmeService.criar(filme);
        return "redirect:/listagem";
    }

    @GetMapping("/listagem")
    public String listaForm(Model model) {
        model.addAttribute("lista", filmeService.listarTodos());
        return "lista";
    }

    @GetMapping("/exibir-analises")
    public String mostraDetalhesFilme(Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        FilmeEntity filmeEncontrado = new FilmeEntity();
        filmeEncontrado = filmeService.buscarPorId(idFilme);
        List<AnalisesEntity> analisesEncontrado = new ArrayList<>();
        analisesEncontrado = analisesService.buscarPorFilme(idFilme);

        model.addAttribute("filme", filmeEncontrado);
        model.addAttribute("analise", new AnalisesEntity());
        model.addAttribute("analises", analisesEncontrado);
        return "exibir-analises";
    }

    @PostMapping("/gravar-analise")
    public String gravarAnaliseUsuario(@ModelAttribute FilmeEntity filme, @ModelAttribute AnalisesEntity analise) {
        analise.setFilme(filme);
        analisesService.criar(analise);
        return "redirect:/listagem";
    }

    @GetMapping("/editar-analise/{id}")
    public String editarAnalise(Model model, @PathVariable("id") Integer id) {
        AnalisesEntity analiseEncontrada = analisesService.buscarPorId(id);
        model.addAttribute("analise", analiseEncontrada);
        return "editar-analise";
    }

    @PostMapping("/editar-analise-salvar")
    public String editarAnaliseSalvar(@ModelAttribute AnalisesEntity analise) {
        AnalisesEntity analiseExistente = analisesService.buscarPorId(analise.getId());
        analiseExistente.setDescricao(analise.getDescricao());
        analiseExistente.setNota(analise.getNota());
        analisesService.atualizarAnalise(analiseExistente.getId(), analiseExistente);
        return "redirect:/exibir-analises?id=" + analiseExistente.getFilme().getId();

    }

    @GetMapping("/editar-filme/{id}")
    public String editarFilme(Model model, @PathVariable("id") Integer id) {
        List<String> generos = lerGenerosDoArquivo("src/main/resources/data/generos.txt");
        model.addAttribute("generos", generos);
        FilmeEntity filmeEncontrado = filmeService.buscarPorId(id);
        model.addAttribute("filme", filmeEncontrado);
        return "editar-filme";
    }

    @PostMapping("/editar-filme-salvar")
    public String editarFilmeSalvar(@ModelAttribute FilmeEntity filmeAtualizado) {
        FilmeEntity filmeExistente = filmeService.buscarPorId(filmeAtualizado.getId());
        filmeExistente.setNome(filmeAtualizado.getNome());
        filmeExistente.setAutor(filmeAtualizado.getAutor());
        filmeExistente.setGenero(filmeAtualizado.getGenero());
        filmeExistente.setData(filmeAtualizado.getData());
        filmeExistente.setSinopse(filmeAtualizado.getSinopse());
        filmeService.atualizarFilme(filmeExistente.getId(), filmeExistente);
        return "redirect:/listagem";
    }

    @GetMapping("/excluir-filme/{id}")
    public String excluirFilme(@PathVariable("id") Integer id) {
        analisesService.excluirAnalisesPorFilme(id);
        filmeService.excluir(id);
        return "redirect:/listagem";
    }

    @GetMapping("/dark-mode-toggle")
    @ResponseBody
    public String toggleDarkMode(@RequestParam(defaultValue = "false") String currentMode, HttpServletResponse response) {
        String newMode = currentMode.equals("true") ? "false" : "true";
        Cookie darkModeCookie = new Cookie("darkMode", newMode);
        darkModeCookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(darkModeCookie);
        return newMode;
    }

}
