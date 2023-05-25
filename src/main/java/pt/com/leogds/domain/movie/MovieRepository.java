package pt.com.leogds.domain.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepositoryImplementation<Movie, Long> {

	List<Movie> findTop10ByOrderByStarDesc();

	@Query("SELECT um FROM User u " + "JOIN u.favoriteMovies um " + "WHERE u.id = :userId AND um.id NOT IN :movieIds "
			+ "ORDER BY FUNCTION('RAND') LIMIT 1")
	Optional<Movie> findRandomMovieByUserIdAndNotInMovieIds(@Param("userId") Long userId,
			@Param("movieIds") List<Long> movieId);

	@Query(value = "SELECT * FROM tbmovie ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Optional<Movie> findRandomMovie();

}
