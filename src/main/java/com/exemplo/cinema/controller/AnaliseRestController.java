package com.exemplo.cinema.controller;

import com.exemplo.cinema.data.AnaliseEntity;
import com.exemplo.cinema.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/filmes/{filmeId}/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseService analiseService;

    @PostMapping
    public ResponseEntity<AnaliseEntity> addAnalise(
            @PathVariable Integer filmeId,
            @RequestBody AnaliseEntity analise) {
        AnaliseEntity novaAnalise = analiseService.adicionarAnalise(filmeId, analise);
        return ResponseEntity
                .created(URI.create("/api/filmes/" + filmeId + "/analises/" + novaAnalise.getId()))
                .body(novaAnalise);
    }

    @GetMapping
    public ResponseEntity<List<AnaliseEntity>> getAnalisesPorFilme(
            @PathVariable Integer filmeId) {
        List<AnaliseEntity> analises = analiseService.listarAnalisesPorFilme(filmeId);
        return ResponseEntity.ok(analises);
    }

    @PutMapping("/{analiseId}")
    public ResponseEntity<AnaliseEntity> atualizarAnalise(
            @PathVariable Integer filmeId,
            @PathVariable Integer analiseId,
            @RequestBody AnaliseEntity analiseAtualizada) {

        AnaliseEntity analiseExistente = analiseService.buscarAnalisePorId(analiseId);

        if (!analiseExistente.getFilme().getId().equals(filmeId)) {
            return ResponseEntity.badRequest().build();
        }

        analiseExistente.setAnalise(analiseAtualizada.getAnalise());
        analiseExistente.setNota(analiseAtualizada.getNota());

        AnaliseEntity analiseAtualizadaSalva = analiseService.atualizarAnalise(analiseExistente);
        return ResponseEntity.ok(analiseAtualizadaSalva);
    }

    @DeleteMapping("/{analiseId}")
    public ResponseEntity<Void> deleteAnalise(
            @PathVariable Integer filmeId,
            @PathVariable Integer analiseId) {
        analiseService.deletarAnalise(analiseId);
        return ResponseEntity.noContent().build();
    }
}
