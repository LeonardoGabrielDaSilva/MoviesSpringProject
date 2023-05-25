package pt.com.leogds.domain.movie.dto;

import java.util.List;

public record DiscoverMoviesData(Long page, List<MovieTmdbData> results) {

}
