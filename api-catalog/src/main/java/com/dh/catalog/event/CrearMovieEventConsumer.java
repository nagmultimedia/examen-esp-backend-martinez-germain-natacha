package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CrearMovieEventConsumer {


    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE_CREADA)
    public void listen(MovieData message){
        System.out.print("NOMBRE DE MOVIE "+ message.name);
        //procesamiento
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MovieData {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;

    }
}
