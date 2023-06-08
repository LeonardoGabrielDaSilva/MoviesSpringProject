package pt.com.leogds.domain.movie.dto;

import java.util.List;

public record TmdbPageData(Long page, List<MovieTmdbData> results) {

}
