package pt.com.leogds.domain.movie.dto;

public record MovieImdbData(String id, Integer rank, String title, String fullTitle, Integer year, String image,
		String crew, Double imDbRating) {

}
