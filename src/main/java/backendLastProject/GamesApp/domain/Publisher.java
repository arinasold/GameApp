package backendLastProject.GamesApp.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publisher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long publisher_id;
	private String publisher_name;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "publisher")
	@JsonIgnoreProperties("publisher")
	private List<Game> games;
	
	//constructors
	
	public Publisher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Publisher(String publisher_name) {
		super();
		this.publisher_name = publisher_name;

	}

	public Publisher(Long publisher_id, String publisher_name, List<Game> games) {
		super();
		this.publisher_id = publisher_id;
		this.publisher_name = publisher_name;
		this.games = games;
	}
	
	// getters and setters 
	
	public Long getPublisher_id() {
		return publisher_id;
	}
	public String getPublisher_name() {
		return publisher_name;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	// To String
	
	@Override
	public String toString() {
		return "Publisher [publisher_id=" + publisher_id + ", publisher_name=" + publisher_name + ", games=" + games
				+ "]";
	}
	

}
