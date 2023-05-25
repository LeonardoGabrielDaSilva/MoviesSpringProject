package pt.com.leogds.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FavoriteData(@NotBlank String username, @NotNull Long movieId) {

}
