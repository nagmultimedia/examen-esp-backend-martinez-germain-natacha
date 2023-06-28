package com.dh.apiserie.controller;

import com.dh.apiserie.event.CrearSerieEventProducer;
import com.dh.apiserie.model.Season;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.service.SerieService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private SerieService serieService;
    private final CrearSerieEventProducer crearSerieEventProducer;

    public SerieController(SerieService serieService, CrearSerieEventProducer crearSerieEventProducer) {
        this.serieService = serieService;
        this.crearSerieEventProducer = crearSerieEventProducer;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre){
        return ResponseEntity.ok(serieService.getSeriesBygGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
         return ResponseEntity.ok().body(serieService.createSerie(serie));
    }

    @PostMapping("/publishSerie")
    @ResponseStatus(code= HttpStatus.OK)
    public void publishSerie (@RequestBody SerieData serieData){
        crearSerieEventProducer.publishCrearSerie(serieData);
        // el profe lo manda asi: finalizarCursoEventProducer.publishFinalizarCursoEvent(new FinalizarCursoEventProducer.Data("Esp Back I",10, "Felices Pascuas" ));
        // pero no me salio mandarlo igual
    }

    @Getter
    @Setter
    public static class SerieData {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
        private List<Season> season;

    }

}
