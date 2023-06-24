// com/dh/movie/config/RabbitMQConfig.java
package com.dh.apiserie.event;

import com.dh.apiserie.config.RabbitMQConfig;
import com.dh.apiserie.model.Season;
import lombok.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SerieEventConsumer {


    private final RabbitTemplate rabbitTemplate;

    public SerieEventConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE_CREADA)
    public void listen(SerieEventConsumer.DocumentRequest DocumentRequest){
        System.out.print("NOMBRE DE LA SERIE "+ DocumentRequest);
        //procesamiento
    }

    public void publishCrearSerieEvent(SerieEventConsumer.DocumentRequest entity){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_SERIE_CREADA,entity);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static
    class DocumentRequest {
        private String name;
        private String genre;
        private String urlStream;
        private List<Season> seasons = new ArrayList<>();
    }
}