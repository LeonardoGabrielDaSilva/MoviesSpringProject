package pt.com.leogds.domain.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.leogds.domain.movie.dto.DiscoverMoviesData;

@Service
public class MovieServiceTmdb extends MovieServiceTemplate {

	@Autowired
	ClientTmdb movieClient;

	@Override
	public List<Movie> populateSchema() {
		DiscoverMoviesData discoverMovies = movieClient.discoverMovies();
		return movieRepository.saveAll(discoverMovies.results().stream()
				.map(m -> new Movie(m.title(), m.release_date().getYear()))
				.collect(Collectors.toList()));
	}

}
