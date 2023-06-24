package com.dh.movie.controller;
import com.dh.movie.event.MovieEventConsumer;
import com.dh.movie.model.Movie;
import com.dh.movie.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {


    private final MovieService movieService;
    private final MovieEventConsumer movieEventConsumer;

    public MovieController(MovieService movieService, MovieEventConsumer movieEventConsumer) {
        this.movieService = movieService;
        this.movieEventConsumer = movieEventConsumer;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> create(@RequestBody Movie movie) {
        movieService.save(movie);
        return ResponseEntity.ok(movie.getId());
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }

    @PatchMapping("/registrada")
    @ResponseStatus(code = HttpStatus.OK)
    public void registro(){
        movieEventConsumer.publishCrearMovieEvent(new MovieEventConsumer.DocumentRequest("Tiburon","Accion", "www.cuevana3.mx" ));
    }

    

}
