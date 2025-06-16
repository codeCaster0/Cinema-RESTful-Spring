package com.exemplo.cinema.service;

import com.exemplo.cinema.data.cinemaEntity;
import com.exemplo.cinema.data.cinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cinemaService {

    @Autowired
    private cinemaRepository CinemaRepository;

    public cinemaEntity CriarFilme(cinemaEntity film) {
        film.setId(null);
        return CinemaRepository.save(film);
    }

    public cinemaEntity atualizarFilme(Integer filmId, cinemaEntity filmeRequest) {
        cinemaEntity film = getFilmId(filmId);
        film.setTitulo(filmeRequest.getTitulo());
        film.setSinopse(filmeRequest.getSinopse());
        film.setGenero(filmeRequest.getGenero());
        film.setAnoLancamento(filmeRequest.getAnoLancamento());
        return CinemaRepository.save(film);
    }

    public cinemaEntity getFilmId(Integer cinemaId) {
        return CinemaRepository.findById(cinemaId).orElse(null);
    }

    public List<cinemaEntity> listarTodosFilmes() {
        return CinemaRepository.findAll();
    }

    public void deletarFilme(Integer filmId) {
        cinemaEntity film = getFilmId(filmId);
        CinemaRepository.delete(film);
    }
}
