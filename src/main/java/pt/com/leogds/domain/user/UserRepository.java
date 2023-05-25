package pt.com.leogds.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	@Query("SELECT DISTINCT u.id FROM User u " + "JOIN u.favoriteMovies um " + "WHERE um.id IN "
			+ "(SELECT um2.id FROM User u2 " + "JOIN u2.favoriteMovies um2 "
			+ "WHERE u2.id = :userId) AND u.id != :userId")
	Optional<List<Long>> findSimilarUserIds(@Param("userId") Long userId);
	
	
}
