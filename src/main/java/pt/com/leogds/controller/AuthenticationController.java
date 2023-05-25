	package pt.com.leogds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pt.com.leogds.domain.user.User;
import pt.com.leogds.domain.user.dto.AuthenticationData;
import pt.com.leogds.infra.security.TokenJWT;
import pt.com.leogds.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping
	public ResponseEntity<TokenJWT> doLogin(@RequestBody @Valid AuthenticationData data) {
		var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		Authentication authenticate = authManager.authenticate(authToken);
		String tokenJWT = tokenService.createTokenJWT((User) authenticate.getPrincipal());
		return ResponseEntity.ok(new TokenJWT(tokenJWT));
	}
}
