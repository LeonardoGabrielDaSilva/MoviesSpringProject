package pt.com.leogds.domain.movie.dto;

import java.util.List;

public record ImdbListData(List<MovieImdbData> items, String errorMessage) {

}
