package pt.com.leogds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import pt.com.leogds.domain.role.RoleEnum;
import pt.com.leogds.domain.user.UserService;
import pt.com.leogds.domain.user.dto.AuthenticationData;
import pt.com.leogds.domain.user.dto.FavoriteData;
import pt.com.leogds.domain.user.dto.ReturnUserData;
import pt.com.leogds.domain.user.dto.UpdateRolesData;
import pt.com.leogds.domain.user.dto.UsernameOnly;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<ReturnUserData> createUser(@RequestBody @Valid AuthenticationData user) {
		ReturnUserData userData = userService.createUser(user);
		return ResponseEntity.ok().body(userData);
	}

	@Secured(RoleEnum.Constants.ADMIN)
	@PostMapping("/update")
	public ResponseEntity<ReturnUserData> updateUserRole(@RequestBody @Valid UpdateRolesData userData) {
		ReturnUserData user = userService.updateUserRole(userData);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("addFavorite")
	public ResponseEntity<ReturnUserData> addFavoriteMovie(@RequestBody @Valid FavoriteData favoriteData) {
		ReturnUserData user = userService.addFavorite(favoriteData);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("removeFavorite")
	public ResponseEntity<ReturnUserData> removeFavoriteMovie(@RequestBody @Valid FavoriteData favoriteData) {
		ReturnUserData user = userService.removeFavorite(favoriteData);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("listFavorites")
	public ResponseEntity<ReturnUserData> listFavoriteMovies(@RequestBody @Valid UsernameOnly usernameOnly) {
		ReturnUserData user = userService.listUser(usernameOnly.username());
		return ResponseEntity.ok().body(user);
	}
	
}
