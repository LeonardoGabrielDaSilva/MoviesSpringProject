package pt.com.leogds.domain.role;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tbrole")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = -8237787407221610915L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private RoleEnum name;

	public Role(RoleEnum roleName) {
		this.name = roleName;
	}
	
	@Override
	public String getAuthority() {
		return name.toString();
	}
	

}
