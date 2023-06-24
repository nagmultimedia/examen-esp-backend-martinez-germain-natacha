package com.dh.apiserie.controller;
import com.dh.apiserie.controller.mapper.SerieMapper;
import com.dh.apiserie.controller.response.SerieResponse;
import com.dh.apiserie.event.SerieEventConsumer;
import com.dh.apiserie.model.Season;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;
    private final SerieEventConsumer serieEventConsumer;
    private final SerieMapper serieMapper;

    public SerieController(SerieService serieService, SerieEventConsumer serieEventConsumer, SerieMapper serieMapper) {
        this.serieService = serieService;
        this.serieEventConsumer = serieEventConsumer;
        this.serieMapper = serieMapper;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody Serie serie) {
        System.out.println("Entro en create");
        serieService.save(serie);
        return ResponseEntity.ok(serie.getId());
    }

    @GetMapping("/{id}")
    public SerieResponse getById(@PathVariable String id) throws Exception {
        return serieMapper.toserieResponse(serieService.getById(id));
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre){
        System.out.println("Entro en getSeriesByGenres");
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Serie> createNewSerie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(serieService.save(serie));
    }

    @PatchMapping("/registrada")
    @ResponseStatus(code = HttpStatus.OK)
    public void registro(){
        Season season = new Season();
        List<Season> seasons = new ArrayList<>();
        seasons.add(season);
        serieEventConsumer.publishCrearSerieEvent(new SerieEventConsumer.DocumentRequest("Chucky","Terror", "www.cuevana3.mx", seasons));
    }

}
