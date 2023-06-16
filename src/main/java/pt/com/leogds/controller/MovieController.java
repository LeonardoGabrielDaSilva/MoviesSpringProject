package pt.com.leogds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import pt.com.leogds.domain.movie.MovieServiceImdb;
import pt.com.leogds.domain.movie.MovieServiceTmdb;
import pt.com.leogds.domain.movie.dto.ReturnMovieData;
import pt.com.leogds.domain.role.RoleEnum;
import pt.com.leogds.domain.user.dto.UsernameOnly;

@RestController
@RequestMapping("/movie")
@SecurityRequirement(name = "bearer-key")
public class MovieController {

	@Autowired
	private MovieServiceImdb primaryMovieService;
	
	@Autowired
	private MovieServiceTmdb secondaryMovieService;
	
	
	@PostMapping("populate")
	@Secured(RoleEnum.Constants.ADMIN)
	@CircuitBreaker(name = "populateSchema", fallbackMethod = "populateFallBack")
	public ResponseEntity<List<ReturnMovieData>> populate() {
		List<ReturnMovieData> movies = primaryMovieService.populateSchema();
		return ResponseEntity.ok(movies);
	}
	

	@SuppressWarnings("unused")
	private ResponseEntity<List<ReturnMovieData>> populateFallBack(Throwable t) {
		List<ReturnMovieData> movies = secondaryMovieService.populateSchema();
		return ResponseEntity.ok(movies);
	}
	
	
	@GetMapping("/listAll")
	public ResponseEntity<List<ReturnMovieData>> listAllMovies() {
		List<ReturnMovieData> allMovies = primaryMovieService.listAll();
		return ResponseEntity.ok(allMovies);
	}
	
	@GetMapping("/listTop10MoviesByStar")
	public ResponseEntity<List<ReturnMovieData>> listTopMovies() {
		List<ReturnMovieData> top10StarMovies = primaryMovieService.listTop10StarMovies();
		return ResponseEntity.ok(top10StarMovies);
	}	
	
	@GetMapping("/randomMovie")
	public ResponseEntity<ReturnMovieData> getRandomMovie(@RequestBody @Valid UsernameOnly usernameOnly){
		ReturnMovieData movie = primaryMovieService.recommendSimilarMovie(usernameOnly.username());
		return ResponseEntity.ok(movie);
	}
	
	
	
}
