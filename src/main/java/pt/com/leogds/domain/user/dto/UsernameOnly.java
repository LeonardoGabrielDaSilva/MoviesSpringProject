package pt.com.leogds.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UsernameOnly(@NotBlank String username) {

}
