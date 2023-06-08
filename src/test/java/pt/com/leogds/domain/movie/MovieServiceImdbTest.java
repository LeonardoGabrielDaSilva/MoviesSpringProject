package pt.com.leogds.domain.movie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pt.com.leogds.domain.movie.dto.ReturnMovieData;
import pt.com.leogds.domain.user.User;
import pt.com.leogds.domain.user.UserRepository;

@SpringBootTest
class MovieServiceImdbTest {

	@Autowired
	private MovieServiceImdb movieService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Test
	void testRecommendSimilarMovie() {
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
		
		ReturnMovieData similarMovie = movieService.recommendSimilarMovie("teste1");
		movie2 = movieRepository.findById(movie2.getId()).orElse(null);
		assertThat(similarMovie.equals(new ReturnMovieData(movie2)));
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
