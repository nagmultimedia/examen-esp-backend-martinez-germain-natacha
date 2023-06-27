package com.dh.movie;

import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
@EnableRabbit

public class ApiMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMovieApplication.class, args);
    }


    @Bean
    public CommandLineRunner loadData(MovieRepository repository) {
        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }

            repository.save(new Movie(null, "La llamada", "Terror", "www.netflix.com"));
            repository.save(new Movie(null, "IT", "Terror", "www.netflix.com"));
            repository.save(new Movie(null, "Scary movie", "Comedia", "www.netflix.com"));
            repository.save(new Movie(null, "Mentiras Verdaderas", "Accion", "www.netflix.com"));
        };
    }

}
