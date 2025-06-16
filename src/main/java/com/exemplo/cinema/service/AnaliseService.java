package com.exemplo.cinema.service;

import com.exemplo.cinema.data.AnaliseEntity;
import com.exemplo.cinema.data.AnaliseRepository;
import com.exemplo.cinema.data.cinemaEntity;
import com.exemplo.cinema.data.cinemaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private cinemaRepository cinemaRepository;

    public AnaliseEntity deletarAnalise(Integer analiseId) {
        AnaliseEntity analise = analiseRepository.findById(analiseId)
                .orElseThrow(() -> new RuntimeException("Análise não encontrada"));
        Integer filmeId = analise.getFilme().getId();

        analiseRepository.delete(analise);
        return analise;
    }

    public List<AnaliseEntity> listarAnalisesPorFilme(Integer filmeId) {
        return analiseRepository.findByFilmeId(filmeId);
    }

    public AnaliseEntity buscarAnalisePorId(Integer id) {
        return analiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Análise não encontrada"));
    }

    public AnaliseEntity atualizarAnalise(AnaliseEntity analise) {
        return analiseRepository.save(analise);
    }

    public AnaliseEntity adicionarAnalise(Integer filmeId, AnaliseEntity analise) {
        cinemaEntity filme = cinemaRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        analise.setFilme(filme);
        analise.setDataCriacao(LocalDateTime.now());

        return analiseRepository.save(analise);
    }
}
