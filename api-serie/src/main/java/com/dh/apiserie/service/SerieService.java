package com.dh.apiserie.service;

import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    public Serie createSerie(Serie serie) {
        return serieRepository.save(serie);
    }

}
