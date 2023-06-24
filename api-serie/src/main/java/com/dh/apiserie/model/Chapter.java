package com.dh.apiserie.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chapter {


    private String name;
    private Integer number;
    private String urlStream;


}
