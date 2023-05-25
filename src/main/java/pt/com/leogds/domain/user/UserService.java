package pt.com.leogds.domain.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import pt.com.leogds.domain.movie.Movie;
import pt.com.leogds.domain.movie.MovieRepository;
import pt.com.leogds.domain.role.Role;
import pt.com.leogds.domain.role.RoleEnum;
import pt.com.leogds.domain.role.RoleRepository;
import pt.com.leogds.domain.user.dto.AuthenticationData;
import pt.com.leogds.domain.user.dto.FavoriteData;
import pt.com.leogds.domain.user.dto.ReturnUserData;
import pt.com.leogds.domain.user.dto.UpdateRolesData;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MovieRepository movieRepository;

	public ReturnUserData createUser(AuthenticationData userData) {
		User user = new User(userData.username(), passwordEncoder.encode(userData.password()), null);
		userRepository.save(user);
		return new ReturnUserData(user);
	}

	public ReturnUserData updateUserRole(UpdateRolesData userData) {
		
		if(!userRepository.existsByUsername(userData.username())) {
			throw new NoResultException("User not found!");
		}
		
		User user = userRepository.findByUsername(userData.username());
		Set<Role> roles = new HashSet<>();
		for (RoleEnum roleEnum : userData.roles()) {
			Role role = roleRepository.findByName(roleEnum);
			roles.add(role);
		}
		user.setRoles(roles);
		userRepository.save(user);
		return new ReturnUserData(user);
	}

	//How to encapsulate to devs use user.addMovie() and always use addFavorite from userRepository??
	public ReturnUserData addFavorite(FavoriteData data) {
		
		if (!movieRepository.existsById(data.movieId())) {
			throw new NoResultException("Movie not found!");
		}

		User user = userRepository.findByUsername(data.username());
		Movie movie = movieRepository.findById(data.movieId()).get();
		if (user.addMovie(movie)) {
			userRepository.save(user);
			movie.addStar();
			movieRepository.save(movie);
		}
		return new ReturnUserData(user);
	}

	public ReturnUserData removeFavorite(FavoriteData data) {

		if (!movieRepository.existsById(data.movieId())) {
			throw new NoResultException("Movie not found!");
		}

		User user = userRepository.findByUsername(data.username());
		Movie movie = movieRepository.findById(data.movieId()).get();
		if (user.removeMovie(movie)) {
			userRepository.save(user);
			movie.removeStar();
			movieRepository.save(movie);
		}
		return new ReturnUserData(user);
	}
	
	public ReturnUserData listUser(String username) {
		User user = userRepository.findByUsername(username);
		return new ReturnUserData(user);
	}

}
