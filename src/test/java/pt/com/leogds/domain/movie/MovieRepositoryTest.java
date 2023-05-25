package pt.com.leogds.domain.movie;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pt.com.leogds.domain.user.User;
import pt.com.leogds.domain.user.UserRepository;

@DataJpaTest
public
class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	public UserRepository userRepository;

	@Test
	@DisplayName("Find a random movie recommended by a similar user")
	void test1FindRandomMovieByUserIdAndNotInMovieIds() {
		Movie movie1 = new Movie("teste1", 2005);
		Movie movie2 = new Movie("teste2", 2006);
		Movie movie3 = new Movie("teste3", 2007);
		User user1 = userRepository.findById(1l).get();
		User user2 = userRepository.findById(2l).get();
		user1.addMovie(movie1);
		user2.addMovie(movie1);
		user2.addMovie(movie2);
		saveMovies(movie1, movie2, movie3);
		saveUsers(user1, user2);

		List<Long> similarUsers = userRepository.findSimilarUserIds(user1.getId()).orElse(null);
		Optional<Movie> randomMovie = movieRepository.findRandomMovieByUserIdAndNotInMovieIds(similarUsers.get(0),
				user1.getFavoriteMovies().stream().map(m -> m.getId()).collect(Collectors.toList()));
		assertThat(randomMovie.get().equals(movie2));
	}

	public void saveUsers(User... users) {
		for (User user : users) {
			userRepository.save(user);
		}
	}

	public void saveMovies(Movie... movies) {
		for (Movie movie : movies) {
			movieRepository.save(movie);
		}
	}

}
