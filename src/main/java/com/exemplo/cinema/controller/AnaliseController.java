package com.exemplo.cinema.controller;

import com.exemplo.cinema.data.AnaliseEntity;
import com.exemplo.cinema.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analises")
public class AnaliseController {

    @Autowired
    private AnaliseService analiseService;

    @PostMapping("/deletar/{id}")
    public String deletarAnalise(@PathVariable Integer id) {
        AnaliseEntity analise = analiseService.deletarAnalise(id);  // Captura a análise excluída
        return "redirect:/filmes/detalhes/" + analise.getFilme().getId();  // Redireciona para a página de detalhes do filme
    }

    @PostMapping("/adicionar")
    public String adicionarAnalise(
            @RequestParam Integer filmeId,
            @RequestParam String analise,
            @RequestParam int nota) {

        AnaliseEntity novaAnalise = new AnaliseEntity();
        novaAnalise.setAnalise(analise);
        novaAnalise.setNota(nota);

        analiseService.adicionarAnalise(filmeId, novaAnalise);

        return "redirect:/filmes/detalhes/" + filmeId;
    }
}
