package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    @Autowired
    @Lazy
    private CatalogService self;


    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }
/*  este metodo en el controller esta llamando a los circuits breakers..:
    @GetMapping("/{genre}")
    ResponseEntity<List<Object>> getGenre(@PathVariable String genre) {
        List<Object> genreItems = new ArrayList<>();
        genreItems.addAll(catalogService.getMovieByGenre(genre));
        genreItems.addAll(catalogService.getSerieByGenre(genre));
        return ResponseEntity.ok(genreItems);
    }
*/

    @Retry(name = "retrySearchGenre")
    @CircuitBreaker(name = "searchGenre", fallbackMethod = "findMovieFallBack")
    public List<MovieServiceClient.MovieDto> getMovieByGenre(String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    public List<MovieServiceClient.MovieDto> findMovieFallBack(String genre, Throwable throwable) throws Exception {
        throw new Exception("No hay peliculas con ese genero");
    }

    @Retry(name = "retrySearchGenre")
    @CircuitBreaker(name = "searchGenre", fallbackMethod = "findSeriesFallBack")
    public List<SerieServiceClient.SerieDto> getSerieByGenre(String genre) {
        return serieServiceClient.getSeriesByGenre(genre);
    }

    public List<SerieServiceClient.SerieDto> findSeriesFallBack(String genre, Throwable throwable)  throws Exception {
        throw new Exception("No hay series con ese genero");
    }

}
