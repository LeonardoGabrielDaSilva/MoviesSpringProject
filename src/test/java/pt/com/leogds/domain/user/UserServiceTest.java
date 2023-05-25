package pt.com.leogds.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import pt.com.leogds.domain.movie.Movie;
import pt.com.leogds.domain.movie.MovieRepository;
import pt.com.leogds.domain.user.dto.FavoriteData;
import pt.com.leogds.domain.user.dto.ReturnUserData;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserService userService;

	@Test
	@DirtiesContext
	@DisplayName("Add movies in the favorite list and verify if the movie is getting stars")
	void test1AddFavorite() {
		Movie movie1 = new Movie("teste1", 2005);
		Movie movie2 = new Movie("teste2", 2006);
		Movie movie3 = new Movie("teste3", 2007);
		User user1 = userRepository.findById(1l).get();
		saveMovies(movie1, movie2, movie3);
		user1.addMovie(movie1);
		user1.addMovie(movie2);
		saveUsers(user1);

		userService.addFavorite(new FavoriteData(user1.getUsername(), movie1.getId()));
		userService.addFavorite(new FavoriteData(user1.getUsername(), movie2.getId()));
		
		ReturnUserData userData = userService.listUser(user1.getUsername());
		List<Movie> movies = new ArrayList<>();
		movies.add(movie1);
		movies.add(movie2);
		assertThat(userData.favoriteMovies().containsAll(movies));
		
		movie1 = movieRepository.findById(movie1.getId()).orElse(null);
		movie2 = movieRepository.findById(movie2.getId()).orElse(null);
		movie3 = movieRepository.findById(movie3.getId()).orElse(null);
		assertThat(movie1.getStar().equals(1));
		assertThat(movie2.getStar().equals(1));
		assertThat(movie3.getStar().equals(0));
	}

	@Test
	@DirtiesContext
	@DisplayName("Remove movies in the favorite list and verify if the movie is losing stars")
	void test1RemoveFavorite() {
		Movie movie1 = new Movie("teste1", 2005);
		Movie movie2 = new Movie("teste2", 2006);
		Movie movie3 = new Movie("teste3", 2007);
		User user1 = userRepository.findById(1l).get();
		saveMovies(movie1, movie2, movie3);
		user1.addMovie(movie1);
		user1.addMovie(movie2);
		saveUsers(user1);

		userService.addFavorite(new FavoriteData(user1.getUsername(), movie1.getId()));
		userService.addFavorite(new FavoriteData(user1.getUsername(), movie2.getId()));
		
		ReturnUserData userData = userService.listUser(user1.getUsername());
		List<Movie> movies = new ArrayList<>();
		movies.add(movie1);
		movies.add(movie2);
		assertThat(userData.favoriteMovies().containsAll(movies));
		
		movie1 = movieRepository.findById(movie1.getId()).orElse(null);
		movie2 = movieRepository.findById(movie2.getId()).orElse(null);
		movie3 = movieRepository.findById(movie3.getId()).orElse(null);
		assertThat(movie1.getStar().equals(1));
		assertThat(movie2.getStar().equals(1));
		assertThat(movie3.getStar().equals(0));
		
		user1.removeMovie(movie1.getId());
		user1.removeMovie(movie2.getId());
		userRepository.save(user1);
		user1 = userRepository.findById(user1.getId()).orElse(null);
		
		assertThat(userData.favoriteMovies().isEmpty());
		
		movie1 = movieRepository.findById(movie1.getId()).orElse(null);
		movie2 = movieRepository.findById(movie2.getId()).orElse(null);
		movie3 = movieRepository.findById(movie3.getId()).orElse(null);
		assertThat(movie1.getStar().equals(1));
		assertThat(movie2.getStar().equals(1));
		assertThat(movie3.getStar().equals(0));
		
		
		
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
