package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/movies")
@Api(description="Use this to get and create movies, and add actors to movies.")
public class MovieApiController {
	
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	
	public MovieApiController(MovieRepository movieRepo, ActorRepository actorRepo) {
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
		
//		movieRepo.save(new Movie("Talladega Nights: The Ballad of Ricky Bobby", new Date(Date.parse("08/04/2006")), 42500000l, "Columbia Pictures"));
	}
	
	@ApiOperation(value = "Find all movies.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}
	
	@ApiOperation(value = "Get a movie by movie ID.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws StuffNotFoundException {
		Movie movie = movieRepo.findOne(id);
		if (movie == null) {
			throw new StuffNotFoundException();
		}
		return movie;
	}
	
	@ApiOperation(value = "Delete a movie.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@DeleteMapping("{id}")
	public Movie delete(@PathVariable long id) {
		try {
			Movie movie = movieRepo.findOne(id);
			movieRepo.delete(id);
			return movie;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	@ApiOperation(value = "Create a new movie.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@PostMapping("")
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	@ApiOperation(value = "Update an existing movie.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}
	
	@ApiOperation(value = "Associate an actor to a movie.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@PostMapping("{movieId}/actors")
	public Movie associateAnActor(@PathVariable long movieId, @RequestBody Actor actor) {
		Movie movie = movieRepo.findOne(movieId);
		actor = actorRepo.findOne(actor.getId());
		
		movie.addActor(actor);
		movieRepo.save(movie);
		return movie;
	}

}
