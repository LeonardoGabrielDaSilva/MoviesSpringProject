package pt.com.leogds.domain.movie.dto;

import java.util.List;

public record Top250Data(List<MovieImdbData> items, String errorMessage) {

}
