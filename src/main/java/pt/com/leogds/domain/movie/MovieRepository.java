package pt.com.leogds.domain.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepositoryImplementation<Movie, Long> {

	List<Movie> findTop10ByOrderByStarDesc();

	@Query("SELECT um FROM User u " +
			"JOIN u.favoriteMovies um " + 
				"WHERE u.id = :userId AND um.id NOT IN :movieIds " +
			"ORDER BY RANDOM() LIMIT 1")
	Optional<Movie> findRandomMovieByUserIdAndNotInMovieIds(@Param("userId") Long userId,
			@Param("movieIds") List<Long> movieId);

	@Query("SELECT m FROM Movie m " +
				"WHERE m.id NOT IN :movieIds " + 
			"ORDER BY RANDOM() LIMIT 1")
	Optional<Movie> findRandomMovieNotInMovieIds(@Param("movieIds") List<Long> userId);

	@Query(value = "SELECT m FROM Movie m ORDER BY RANDOM() LIMIT 1")
    Optional<Movie> findRandomMovie();

	
}
