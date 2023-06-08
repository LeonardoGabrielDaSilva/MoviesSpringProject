package pt.com.leogds.domain.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import pt.com.leogds.domain.movie.dto.ReturnMovieData;
import pt.com.leogds.domain.user.User;
import pt.com.leogds.domain.user.UserRepository;

public abstract class MovieServiceTemplate {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected MovieRepository movieRepository;

	abstract public List<ReturnMovieData> populateSchema();

	public List<ReturnMovieData> listAll(){
		 List<Movie> allMovies = movieRepository.findAll();
		 return allMovies.stream().map(m -> new ReturnMovieData(m)).collect(Collectors.toList());
	}

	@Cacheable("top10Movies")
	public List<ReturnMovieData> listTop10StarMovies() {
		List<Movie> top10Movies = movieRepository.findTop10ByOrderByStarDesc();
		return top10Movies.stream().map(m -> new ReturnMovieData(m)).collect(Collectors.toList());
	}

	public ReturnMovieData recommendSimilarMovie(String username) {
		User user = userRepository.findByUsername(username);

		// Get the user's favorites
		List<Long> userFavorites = user.getFavoriteMovies().stream().map(Movie::getId).collect(Collectors.toList());

		// Get similar users from favorite movies
		List<Long> similarUsersIds = userRepository.findSimilarUserIds(user.getId())
				.orElse(new ArrayList<Long>());

		// Get a random user from the similars and then look after a random movie that
		// isn't present in userFavorites
		Movie randomMovie = null;
		while (randomMovie == null && !similarUsersIds.isEmpty()) {
			randomMovie = getRandomMovieFromUserList(userFavorites, similarUsersIds);
		}

		if (randomMovie == null) {
			randomMovie = movieRepository.findRandomMovieNotInMovieIds(userFavorites).get();
		}

		return new ReturnMovieData(randomMovie);
	}

	private Movie getRandomMovieFromUserList(List<Long> userFavorites, List<Long> similarUsersIds) {
		Movie randomMovie;
		Long similarUserId = similarUsersIds.get(new Random().nextInt(similarUsersIds.size()));
		similarUsersIds.remove(similarUserId);
		randomMovie = movieRepository.findRandomMovieByUserIdAndNotInMovieIds(similarUserId, userFavorites)
				.orElse(null);
		return randomMovie;
	}

}
