package pt.com.leogds.domain.movie;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbmovie")
@DynamicInsert
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
    
    @NotNull
    @Column(name = "launch_year")
    private Integer year;
    
    @ColumnDefault("0")
    private Integer star;
	
    public Movie(Long id) {
    	this.id = id;
    }
    
    public Movie(String title, Integer year) {
    	this.title = title;
    	this.year = year;
    	this.star = 0;
    }
    
    public void addStar() {
    	this.star++;
    }
    
    public void removeStar() {
    	this.star--;
    }
	
}
