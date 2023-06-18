package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.repository.CatalogRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;
    private final CatalogRepository catalogRepository;

    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogRepository catalogRepository) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
        this.catalogRepository = catalogRepository;
    }


    //circuit breaker:
    @Retry(name = "retryGenre")
    @CircuitBreaker(name = "searchGenres", fallbackMethod = "searchGenresMFallBack") //modificar en config
    public MovieServiceClient.MovieDto searchMGenres(String genre) {
        List<MovieServiceClient.MovieDto> resMovies = movieServiceClient.getMovieByGenre(genre);
        return (MovieServiceClient.MovieDto) resMovies;
    }

    @Retry(name = "retryGenre")
    @CircuitBreaker(name = "searchGenres", fallbackMethod = "searchGenresSFallBack")
    private SerieServiceClient.SerieDto searchSGenres(String genre) {
        List<SerieServiceClient.SerieDto> resSeries = serieServiceClient.getSerieByGenre(genre);
        return (SerieServiceClient.SerieDto) resSeries;
    }

    public MovieServiceClient.MovieDto searchGenresMFallBack(String genre, Throwable t) throws Exception {
        throw new Exception("No se encontraron peliculas de ese genero");
    }

    public SerieServiceClient.SerieDto searchGenresSFallBack(String genre, Throwable t) throws Exception {
        throw new Exception("No se encontraron series de ese genero");
    }


}
