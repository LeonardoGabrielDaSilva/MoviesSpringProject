package pt.com.leogds.domain.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.leogds.domain.movie.Movie;
import pt.com.leogds.domain.role.Role;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbuser")
public class User implements UserDetails {

	private static final long serialVersionUID = -696193478692600866L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_favorite_movie", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private Set<Movie> favoriteMovies = new HashSet<>();

	public User(String login, String password, HashSet<Role> roles) {
		this.username = login;
		this.password = password;
		this.roles = roles;
	}
	

	public User(String string, String string2) {
		this.username = string;
		this.password = string2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoles();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Set<Movie> getFavoriteMovies() {
		return Collections.unmodifiableSet(favoriteMovies);
	}

	public boolean addMovie(Movie movie) {
		if(favoriteMovies.contains(movie)) {
			return false;
		}
		favoriteMovies.add(movie);
		return true;
	}
	
	public boolean removeMovie(Long movieId) {
		return favoriteMovies.removeIf(m -> m.getId().equals(movieId));
	}
	
	public boolean removeMovie(Movie movie) {
		return favoriteMovies.remove(movie);
	}

}
