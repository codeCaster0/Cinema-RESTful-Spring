package com.exemplo.cinema.controller;

import com.exemplo.cinema.data.cinemaEntity;
import com.exemplo.cinema.service.cinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
    private cinemaService cinemaService;

    @GetMapping
    public List<cinemaEntity> getAllFilmes() {
        return cinemaService.listarTodosFilmes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<cinemaEntity> getFilmeById(@PathVariable Integer id) {
        cinemaEntity filme = cinemaService.getFilmId(id);
        return filme != null ? ResponseEntity.ok(filme) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<cinemaEntity> createFilme(@RequestBody cinemaEntity filme) {
        cinemaEntity novoFilme = cinemaService.CriarFilme(filme);
        return ResponseEntity
                .created(URI.create("/api/filmes/" + novoFilme.getId()))
                .body(novoFilme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<cinemaEntity> updateFilme(
            @PathVariable Integer id,
            @RequestBody cinemaEntity filme) {
        return ResponseEntity.ok(cinemaService.atualizarFilme(id, filme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Integer id) {
        cinemaService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
}
