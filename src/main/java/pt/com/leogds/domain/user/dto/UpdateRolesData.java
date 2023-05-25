package pt.com.leogds.domain.user.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import pt.com.leogds.domain.role.RoleEnum;

public record UpdateRolesData(@NotBlank String username, Set<RoleEnum> roles) {

}
