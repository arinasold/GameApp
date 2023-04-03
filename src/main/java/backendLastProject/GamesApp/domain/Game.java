package backendLastProject.GamesApp.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long game_id;
	private String game_name;
	private String description;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "game_genre",
		joinColumns = @JoinColumn(name = "game_id"),
		inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@JsonIgnoreProperties("games")
	private List<Genre> genres;
	@ManyToOne
	@JoinColumn(name = "publisher_id")
	@JsonIgnoreProperties("games")
	private Publisher publisher;
	
	// constructors
	
	public Game() {
		super();

	}
	
	public Game(String game_name, String description, Publisher publisher) {
		super();
		this.game_name = game_name;
		this.description = description;
		this.publisher = publisher;
	}
	
	public Game(String game_name, String description, List<Genre> genres, Publisher publisher) {
		super();
		this.game_name = game_name;
		this.description = description;
		this.genres = genres;
		this.publisher = publisher;
	}
	
	public Game(Long game_id, String game_name, String description, List<Genre> genres, Publisher publisher) {
		super();
		this.game_id = game_id;
		this.game_name = game_name;
		this.description = description;
		this.genres = genres;
		this.publisher = publisher;
	}
	
	// getters and setters
	
	public Long getGame_id() {
		return game_id;
	}
	public String getGame_name() {
		return game_name;
	}
	public String getDescription() {
		return description;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setGame_id(Long game_id) {
		this.game_id = game_id;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	// To String which avoids cyclic references
	
	@Override
	public String toString() {
	    return "Game{" +
	            "game_id=" + game_id +
	            ", game_name='" + game_name + '\'' +
	            ", description='" + description + '\'' +
	            ", genres=" + genres.stream().map(genre -> genre.getGenre_name().replace("_", " ")).collect(Collectors.toList()) +
	            ", publisher=" + (publisher == null ? "null" : publisher.getPublisher_name()) +
	            '}';
	}
	
	
}
