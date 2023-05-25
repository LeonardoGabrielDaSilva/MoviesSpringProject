package pt.com.leogds.domain.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.leogds.domain.movie.dto.Top250Data;

@Service
public class MovieServiceImdb extends MovieServiceTemplate {

	@Autowired
	ClientImdb movieClient;

	public List<Movie> populateSchema() {
		Top250Data findTop250Movies = movieClient.findTop250Movies();
		return movieRepository.saveAll(findTop250Movies.items().stream()
				.map(m -> new Movie(m.title(), m.year()))
				.collect(Collectors.toList()));
	}

}
