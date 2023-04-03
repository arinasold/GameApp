package backendLastProject.GamesApp;


import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backendLastProject.GamesApp.domain.Game;
import backendLastProject.GamesApp.domain.GameRepository;
import backendLastProject.GamesApp.domain.Genre;
import backendLastProject.GamesApp.domain.GenreRepository;
import backendLastProject.GamesApp.domain.Publisher;
import backendLastProject.GamesApp.domain.PublisherRepository;

@SpringBootApplication
public class GamesAppApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GamesAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GamesAppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(GameRepository gameRepo, GenreRepository genreRepo, PublisherRepository publisherRepo) {
		
		return (args) -> {
			
			// demo data of genres
			
			Genre genre1 = new Genre("Action");
			Genre genre2 = new Genre("Adventure");
			Genre genre3 = new Genre("Strategy");
			
			genreRepo.save(genre1);
			genreRepo.save(genre2);
			genreRepo.save(genre3);
			
			log.info("fetch all games");
			for (Genre genre : genreRepo.findAll()) {
				log.info(genre.toString());
				}
			
			// demo data of publishers
			
			Publisher publisher1 = new Publisher("Valve"); // Dota2, Counter-Strike: Global Offensive
			Publisher publisher2 = new Publisher("Berserk Games"); // Tabletop Simulator
			Publisher publisher3 = new Publisher("Annapurna Interactive"); // Stray, Solar Ash
			Publisher publisher4 = new Publisher("2K Games"); // Civilization VI
			
			
			publisherRepo.save(publisher1);
			publisherRepo.save(publisher2);
			publisherRepo.save(publisher3);
			publisherRepo.save(publisher4);
			
			log.info("fetch all games");
			for (Publisher publisher : publisherRepo.findAll()) {
				log.info(publisher.toString());
				}
			
			// demo data for games

			Game game1 = new Game("Dota 2", "A multiplayer online battle arena video game", Arrays.asList(genre1, genre3), publisher1);
			Game game2 = new Game("Counter-Strike: Global Offensive", "A multiplayer first-person shooter video game", Arrays.asList(genre1), publisher1);
			Game game3 = new Game("Tabletop Simulator", "A simulation video game that allows players to play tabletop games", Arrays.asList(genre2), publisher2);
			Game game4 = new Game("Stray", "An upcoming action-adventure video game", Arrays.asList(genre1, genre2), publisher3);
			Game game5 = new Game("Solar Ash", "An upcoming action-platformer video game", Arrays.asList(genre1, genre2), publisher3);
			Game game6 = new Game("Civilization VI", "A turn-based strategy video game", Arrays.asList(genre3), publisher4);

			gameRepo.save(game1);
			gameRepo.save(game2);
			gameRepo.save(game3);
			gameRepo.save(game4);
			gameRepo.save(game5);
			gameRepo.save(game6);
			
			// test in console
			
			log.info("fetch all games");
			for (Game game : gameRepo.findAll()) {
				log.info(game.toString());
				}
		};
		
	};

}
