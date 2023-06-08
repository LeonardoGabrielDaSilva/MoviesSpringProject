package pt.com.leogds.domain.movie.dto;

import java.io.Serializable;

import pt.com.leogds.domain.movie.Movie;

public record ReturnMovieData(String title, Integer year, Integer star) implements Serializable {

	public ReturnMovieData(Movie movie) {
		this(movie.getTitle(), movie.getYear(), movie.getStar());
	}

}