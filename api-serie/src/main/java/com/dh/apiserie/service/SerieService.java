package com.dh.apiserie.service;

import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> findByGenre(String genre) {
        return serieRepository.findByGenre(genre);
    }

    @Transactional
    public Serie save(Serie serie) {
        serieRepository.save(serie);
        return serieRepository.save(serie);
    }

    public Serie getById(String id) throws Exception {
        return serieRepository.findById(id).orElseThrow(() -> new Exception("No existe una serie con ese id"));
    }
}
