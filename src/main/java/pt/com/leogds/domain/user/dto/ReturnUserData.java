package pt.com.leogds.domain.user.dto;

import java.util.Set;

import pt.com.leogds.domain.movie.Movie;
import pt.com.leogds.domain.role.Role;
import pt.com.leogds.domain.user.User;

public record ReturnUserData(String username, Set<Movie> favoriteMovies, Set<Role> roles) {

	public ReturnUserData(User user) {
		this(user.getUsername(), user.getFavoriteMovies(), user.getRoles());
	}
	
}
