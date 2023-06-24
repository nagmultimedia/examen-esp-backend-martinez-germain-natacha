package com.dh.apiserie.controller.mapper;

import com.dh.apiserie.controller.SerieRequest.SerieRequest;
import com.dh.apiserie.controller.response.SerieResponse;
import com.dh.apiserie.model.Serie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SerieMapper {

    Serie toSerie(SerieRequest request);

    SerieResponse toserieResponse(Serie entity);

}
