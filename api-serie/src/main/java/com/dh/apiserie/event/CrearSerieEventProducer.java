package com.dh.apiserie.event;

import com.dh.apiserie.config.RabbitMQConfig;
import com.dh.apiserie.controller.SerieController;
import com.dh.apiserie.model.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrearSerieEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public CrearSerieEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCrearSerie(SerieController.SerieData message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_SERIE_CREADA,message);
    }

}
