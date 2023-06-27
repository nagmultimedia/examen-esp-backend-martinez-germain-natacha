package com.dh.movie.event;
import com.dh.movie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class crearMovieEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public crearMovieEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishcrearMovie(MovieData message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_MOVIE_CREADA,message);
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


