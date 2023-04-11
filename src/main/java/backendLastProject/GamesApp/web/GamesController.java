package backendLastProject.GamesApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backendLastProject.GamesApp.domain.Game;
import backendLastProject.GamesApp.domain.GameRepository;
import backendLastProject.GamesApp.domain.GenreRepository;
import backendLastProject.GamesApp.domain.Publisher;
import backendLastProject.GamesApp.domain.PublisherRepository;


@Controller
public class GamesController {
	
	@Autowired
	GameRepository gameRepo;
	@Autowired
	GenreRepository genreRepo;
	@Autowired
	PublisherRepository publisherRepo;
	
	@RequestMapping(value="/gamelist", method = RequestMethod.GET)
	public String showGames(Model model) {
		
		List<Game> games = (List<Game>)gameRepo.findAll();
		model.addAttribute("games", games);
		return "gamelist";
	}
	
	@RequestMapping(value = "/add")
	public String addGame(Model model){
	   model.addAttribute("game", new Game());
	   model.addAttribute("genres", genreRepo.findAll());
	   return "addgame";
	}


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveGame(Game game){
	    Publisher publisher = game.getPublisher();
	    publisherRepo.save(publisher); // save the publisher entity first
	    gameRepo.save(game); // save the game entity
	    return "redirect:gamelist";
	}

}
