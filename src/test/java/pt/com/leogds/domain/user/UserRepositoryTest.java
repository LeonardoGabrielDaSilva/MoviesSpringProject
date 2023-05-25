package pt.com.leogds.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pt.com.leogds.domain.movie.Movie;
import pt.com.leogds.domain.movie.MovieRepository;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Test
	@DisplayName("Find users with similar movies at favorite list with 2 users, 3 movies and 1 movie in both favorite list. "
			+ "Should return user 2")
	void test1FindSimilarUserIds() {
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

		Optional<List<Long>> findSimilarUserIds = userRepository.findSimilarUserIds(user1.getId());
		assertThat(findSimilarUserIds.get().contains(user2.getId()));
	}
	
	@Test
	@DisplayName("Find users with similar movies at favorite list with 3 users, 3 movies and 1 movie in both favorite list. "
			+ "Should return user 2 and 3")
	void test2FindSimilarUserIds() {
		Movie movie1 = new Movie("teste1", 2005);
		Movie movie2 = new Movie("teste2", 2006);
		Movie movie3 = new Movie("teste3", 2007);
		User user1 = userRepository.findById(1l).get();
		User user2 = userRepository.findById(2l).get();
		User user3 = new User("teste3", "teste3");
		user1.addMovie(movie1);
		user2.addMovie(movie1);
		user2.addMovie(movie2);
		user3.addMovie(movie3);
		saveMovies(movie1, movie2, movie3);
		saveUsers(user1, user2, user3);

		Optional<List<Long>> findSimilarUserIds = userRepository.findSimilarUserIds(user1.getId());
		assertThat(findSimilarUserIds.get().contains(user3.getId()));
		assertThat(findSimilarUserIds.get().contains(user2.getId()));
	}

	@Test
	@DisplayName("Find users with similar movies at favorite list with 2, 2 movies none movie in both favorite list. "
			+ "Should return null")
	void test3FindSimilarUserIds() {
		Movie movie1 = new Movie("teste1", 2005);
		Movie movie2 = new Movie("teste2", 2006);
		User user1 = userRepository.findById(1l).get();
		User user2 = userRepository.findById(2l).get();
		user1.addMovie(movie1);
		user2.addMovie(movie2);
		saveMovies(movie1, movie2);
		saveUsers(user1, user2);

		Optional<List<Long>> findSimilarUserIds = userRepository.findSimilarUserIds(user1.getId());
		assertThat(findSimilarUserIds.isEmpty());
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
