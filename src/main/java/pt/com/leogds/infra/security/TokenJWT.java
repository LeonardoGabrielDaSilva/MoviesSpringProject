package pt.com.leogds.infra.security;

import jakarta.validation.constraints.NotNull;

public record TokenJWT(@NotNull String token) {

}
