package com.dh.apiserie.controller.response;

import com.dh.apiserie.model.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SerieResponse {

    private String id;
    private String name;
    private String genre;
    private String urlStream;
    private List<Season> seasons = new ArrayList<>();

}
