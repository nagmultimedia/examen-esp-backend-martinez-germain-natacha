package com.dh.catalog.client;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="api-movie")//,url = "http://localhost:8080")
public interface MovieServiceClient {

	@GetMapping("/api/v1/movies/{genre}")
	List<Movie> getMovieByGenre(@PathVariable (value = "genre") String genre);

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	class Movie {
		private Long id;
		private String name;
		private String genre;
		private String urlStream;
	}

}
