package backendLastProject.GamesApp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import backendLastProject.GamesApp.domain.Game;
import backendLastProject.GamesApp.domain.GameRepository;
import backendLastProject.GamesApp.domain.Genre;
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
	
	@RequestMapping(value={"/", "/gamelist", "/index", "/home"})
	public String showGames(Model model) {
		
		List<Game> games = (List<Game>)gameRepo.findAll();
		model.addAttribute("games", games);
		return "gamelist";
	}
	
	@RequestMapping(value = "/add")
	@PreAuthorize("hasAuthority('ADMIN')")
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
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editGame(@PathVariable("id") Long gameId, Model model){
	    Optional<Game> gameOptional = gameRepo.findById(gameId);
	    if (gameOptional.isPresent()) {
	        Game game = gameOptional.get();
	        List<Genre> genres = (List<Genre>) genreRepo.findAll();
	        Publisher publisher = game.getPublisher();
	        model.addAttribute("game", game);
	        model.addAttribute("genres", genres);
	        model.addAttribute("publisher", publisher);
	        return "editgame";
	    } else {
	        // handle the case where the requested game is not found
	        return "error";
	    }
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGame(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
	    gameRepo.deleteById(id);
	    redirectAttributes.addFlashAttribute("message", "Game has been deleted successfully.");
	    return "redirect:/gamelist";
	}


}
