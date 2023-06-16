package pt.com.leogds.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import pt.com.leogds.domain.user.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String createTokenJWT(User user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("leogds")
		        .withSubject(user.getUsername())
		        .withExpiresAt(getInstant5MinutesLater())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Error Creating Token JWT");
		}
	}
	
	public String getSubject(String tokenJWT) {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        .withIssuer("leogds")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
	}

	private Instant getInstant5MinutesLater() {
		return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("+00:00"));
	}
}
