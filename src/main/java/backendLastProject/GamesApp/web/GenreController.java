package backendLastProject.GamesApp.web;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import backendLastProject.GamesApp.domain.Game;
import backendLastProject.GamesApp.domain.Genre;
import backendLastProject.GamesApp.domain.GenreRepository;



@Controller
public class GenreController {
	
	@Autowired
	GenreRepository genreRepo;
	
	@GetMapping("/genrelist")
	public String showGenres(Model model) {
		
		List<Genre> genres = (List<Genre>)genreRepo.findAll();
		model.addAttribute("genres", genres);
		return "genrelist";
	}
	
	@GetMapping("/addnewgenre")
	public String addGenre(Model model){
	 model.addAttribute("genre", new Genre());
	 return "addgenre";
	}
	
	@PostMapping("/savegenre")
	public String saveGenre(Genre savedgenre){
		genreRepo.save(savedgenre);
	 return "redirect:genrelist";
	}
	
	@RequestMapping(value = "/editgenre/{id}", method = RequestMethod.GET)
	public String editGenre(@PathVariable("id") Long genreId, Model model){
	    Optional<Genre> genreOptional = genreRepo.findById(genreId);
	    if (genreOptional.isPresent()) {
	        Genre genre = genreOptional.get();
	        model.addAttribute("genre", genre);
	        return "editgenre";
	    } else {
	        // handle the case where the requested genre is not found
	        return "error";
	    }
	}
	
	@RequestMapping(value = "/deletegenre/{id}", method = RequestMethod.GET)
	public String deleteGenre(@PathVariable("id") Long genreId, RedirectAttributes redirectAttributes) {
	    Optional<Genre> genreOptional = genreRepo.findById(genreId);
	    if (genreOptional.isPresent()) {
	        Genre genre = genreOptional.get();
	        List<Game> games = genre.getGames();
	        for (Game game : games) {
	            game.getGenres().remove(genre);
	        }
	        genreRepo.deleteById(genreId);
	        redirectAttributes.addFlashAttribute("message", "Genre has been deleted successfully.");
	    } else {
	    	redirectAttributes.addFlashAttribute("message", "Genre not found.");
	   
	    }
	    return "redirect:/genrelist";
	}

}
