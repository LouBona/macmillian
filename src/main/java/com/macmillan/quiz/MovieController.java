package com.macmillan.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	/**
	 * Since there is no business logic to speak of there's no reason to create a service/biz logic layer
	 * between the Controller and the Repository. Simple error checking and validation can be done here.
	 */
	
	
	@Autowired
	MovieRepository repo;
	
	@PostMapping("/create")	    
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		movie = repo.save(movie);
		return ResponseEntity.ok().body(movie);
	}
	

	@PutMapping("/update")	    
	public ResponseEntity updateMovie(@RequestBody Movie movie) {
		if (movie.getId() == null) {
			return ResponseEntity.badRequest().body("No id present");
		}
		repo.save(movie);
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteMovie(@PathVariable long id) {
		try {
			repo.deleteById(id);
		}
		catch (Exception ex) {
			return ResponseEntity.badRequest().body("No movie with id " + id + " present");
		}
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/list")	    
	public ResponseEntity<List<Movie>> listMovies() {
		List<Movie> movies = repo.findAll();		
		return ResponseEntity.ok().body(movies);
	}
	
}
