package com.exemplo.cinema.controller;

import com.exemplo.cinema.data.cinemaEntity;
import com.exemplo.cinema.service.cinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private cinemaService cinemaService;

    @GetMapping("/listar")
    public String getAllFilmes(Model model) {
        List<cinemaEntity> filmes = cinemaService.listarTodosFilmes();
        model.addAttribute("filmes", filmes);
        return "filmes/lista";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesFilme(@PathVariable Integer id, Model model) {
        cinemaEntity filme = cinemaService.getFilmId(id);
        model.addAttribute("filme", filme);
        return "filmes/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Integer id, Model model) {
        cinemaEntity filme = cinemaService.getFilmId(id);
        model.addAttribute("filme", filme);
        return "filmes/form";
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new cinemaEntity());
        return "filmes/form";
    }

    @PostMapping("/adicionar")
    public String addFilme(@ModelAttribute cinemaEntity filme) {
        cinemaService.CriarFilme(filme);
        return "redirect:/filmes/listar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarFilme(@PathVariable Integer id, @ModelAttribute cinemaEntity filme) {
        cinemaService.atualizarFilme(id, filme);
        return "redirect:/filmes/listar";
    }

    // Deletar um filme
    @PostMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Integer id) {
        cinemaService.deletarFilme(id);
        return "redirect:/filmes/listar";
    }
}
