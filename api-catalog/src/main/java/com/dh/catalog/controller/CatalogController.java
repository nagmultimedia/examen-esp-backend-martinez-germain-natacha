package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;


import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private CatalogService catalogService;
	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;

	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
	}

	@GetMapping("/movie/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getPGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
	}

	@GetMapping("/serie/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDto>> getSGenre(@PathVariable String genre) {
		return ResponseEntity.ok(serieServiceClient.getSerieByGenre(genre));
	}

}
