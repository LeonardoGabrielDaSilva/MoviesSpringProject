package pt.com.leogds.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(@NotBlank String username, @NotBlank String password) {

}
