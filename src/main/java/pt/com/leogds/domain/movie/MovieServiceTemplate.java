package pt.com.leogds.domain.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import pt.com.leogds.domain.user.User;
import pt.com.leogds.domain.user.UserRepository;

public abstract class MovieServiceTemplate {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected MovieRepository movieRepository;

	abstract public List<Movie> populateSchema();

	public List<Movie> listAll(){
		return movieRepository.findAll();
	}

	@Cacheable("rank")
	public List<Movie> listTop10StarMovies() {
		try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return movieRepository.findTop10ByOrderByStarDesc();
	}

	public Movie recommendSimilarMovie(String username) {
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
			randomMovie = movieRepository.findRandomMovie().get();
		}

		return randomMovie;
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
