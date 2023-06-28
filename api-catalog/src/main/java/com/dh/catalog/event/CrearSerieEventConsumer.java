package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.serie.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CrearSerieEventConsumer {


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE_CREADA)
    public void listen(SerieData message){
        System.out.print("NOMBRE DE SERIE "+ message.name);
        //procesamiento
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SerieData {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
        private List<Season> season;

    }
}
