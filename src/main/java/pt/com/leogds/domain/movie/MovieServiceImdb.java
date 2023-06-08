package pt.com.leogds.domain.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.leogds.domain.movie.dto.ImdbListData;
import pt.com.leogds.domain.movie.dto.ReturnMovieData;

@Service
public class MovieServiceImdb extends MovieServiceTemplate {

	@Autowired
	ClientImdb movieClient;

	public List<ReturnMovieData> populateSchema() {
		ImdbListData findTop250Movies = movieClient.findTop250Movies();
		List<Movie> top250Movies = movieRepository.saveAll(findTop250Movies.items().stream()
				.map(m -> new Movie(m.title(), m.year()))
				.collect(Collectors.toList()));
		
		return top250Movies.stream().map(m -> new ReturnMovieData(m)).collect(Collectors.toList());
	}

}
