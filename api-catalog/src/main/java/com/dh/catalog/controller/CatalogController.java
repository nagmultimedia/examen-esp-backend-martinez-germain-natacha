package com.dh.catalog.controller;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/{genre}")
	ResponseEntity<List<Object>> getGenre(@PathVariable String genre) {
		List<Object> genreItems = new ArrayList<>();
		genreItems.addAll(catalogService.getMovieByGenre(genre));
		genreItems.addAll(catalogService.getSerieByGenre(genre));
		return ResponseEntity.ok(genreItems);
	}


}