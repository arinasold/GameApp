package backendLastProject.GamesApp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import backendLastProject.GamesApp.web.GamesController;
import backendLastProject.GamesApp.web.GenreController;
import backendLastProject.GamesApp.web.UserController;

@ExtendWith(SpringExtension.class)   
@SpringBootTest
class GamesAppApplicationTests {
	
	@Autowired
	GamesController gamesController;
	
	@Autowired
	GenreController genreController;
	
	@Autowired
	UserController userController;

	@Test
	void contextLoadsForGames() {
		assertThat(gamesController).isNotNull();
	}
	
	@Test
	void contextLoadsForGenres() {
		assertThat(genreController).isNotNull();
	}
	
	@Test
	void contextLoadsForUsers() {
		assertThat(userController).isNotNull();
	}

}
