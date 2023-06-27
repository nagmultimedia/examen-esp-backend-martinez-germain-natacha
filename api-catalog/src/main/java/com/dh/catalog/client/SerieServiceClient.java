package com.dh.catalog.client;

import com.dh.catalog.model.serie.Season;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@FeignClient(name="api-serie")
public interface SerieServiceClient {

	@GetMapping("/api/v1/series/{genre}")
	List<SerieDto> getSeriesByGenre(@PathVariable (value = "genre") String genre);

	@PostMapping("/api/v1/series/save")
	SerieDto saveSerie(@RequestBody SerieDto serieDto);

	@Getter
	@Setter
	class SerieDto{
		private String id;
		private String name;
		private String genre;
		private List<Season> seasons = new ArrayList<>();
	}
}
