// com/dh/movie/config/RabbitMQConfig.java
package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import lombok.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
public class MovieEventConsumer {


    private final RabbitTemplate rabbitTemplate;

    public MovieEventConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
/*
    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE_CREADA)
    public void listen(MovieEventConsumer.DocumentRequest DocumentRequest){
        System.out.print("NOMBRE DE LA MOVIE "+ DocumentRequest);
        //procesamiento
    }
*/
    public void publishCrearMovieEvent(MovieEventConsumer.DocumentRequest entity){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_MOVIE_CREADA,entity);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static
    class DocumentRequest {
        @Size(min = 1, max = 10)
        private String name;
        @Size(min = 1, max = 20)
        private String genre;
        @Size(min = 1, max = 20)
        private String urlStream;
    }
}