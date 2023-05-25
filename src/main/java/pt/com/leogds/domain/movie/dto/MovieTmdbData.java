package pt.com.leogds.domain.movie.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieTmdbData(boolean adult, String backdrop_path, List<Integer> genre_ids, Long id,
		String original_language, String original_title, String overview, Double popularity, String poster_path,
		LocalDate release_date, String title, boolean video, Float vote_avarage, Long vote_count) {

}
