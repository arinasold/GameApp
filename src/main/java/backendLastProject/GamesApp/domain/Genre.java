package backendLastProject.GamesApp.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genre_id;
	private String genre_name;
	@ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("genres")
	private List<Game>games;
	
	// constructors
	
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Genre(String genre_name) {
		super();
		this.genre_name = genre_name;
	}

	public Genre(Long genre_id, String genre_name, List<Game> games) {
		super();
		this.genre_id = genre_id;
		this.genre_name = genre_name;
		this.games = games;
	}
	
	// getters and setters 
	
	public Long getGenre_id() {
		return genre_id;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	// To String
	
	@Override
	public String toString() {
		return "Genre [genre_id=" + genre_id + ", genre_name=" + genre_name + ", games=" + games + "]";
	}
	

}
