package pt.com.leogds.domain.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.leogds.domain.movie.dto.ReturnMovieData;
import pt.com.leogds.domain.movie.dto.TmdbPageData;

@Service
public class MovieServiceTmdb extends MovieServiceTemplate {

	@Autowired
	ClientTmdb movieClient;

	@Override
	public List<ReturnMovieData> populateSchema() {
		TmdbPageData discoverMovies = movieClient.discoverMovies();
		List<Movie> tmdbMovies = movieRepository.saveAll(discoverMovies.results().stream()
				.map(m -> new Movie(m.title(), m.release_date().getYear()))
				.collect(Collectors.toList()));
		
		return tmdbMovies.stream().map(m -> new ReturnMovieData(m)).collect(Collectors.toList());
	}

}
