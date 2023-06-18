package com.dh.catalog.client;

import com.dh.catalog.model.serie.Chapter;
import com.dh.catalog.model.serie.Serie;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name="api-serie")//,url = "http://localhost:8080")
public interface SerieServiceClient {

	@GetMapping("/api/v1/series/{genre}")
	List<SerieServiceClient.SerieDto> getSerieByGenre(@PathVariable (value = "genre") String genre);


	@Getter
	@Setter
	class SerieDto{
		private Long id;

		private String name;

		private String genre;

		private String urlStream;

		}

}
