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
import com.libertymutual.goforcode.wimp.models.Award;
//import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/actors")
@Api(description="Use this to get and create actors, add movies to actors, and add awards to actors.")
public class ActorApiController {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	
	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo) {
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
		
//		actorRepo.save(new Actor("Will", "Ferrell", 1995l, new Date(Date.parse("07/16/1967"))));
	}
	
	@ApiOperation(value = "Find all actors.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}
	
	@ApiOperation(value = "Get an actor by actor ID.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			throw new StuffNotFoundException();
		}
//		ActorWithMovies newActor = new ActorWithMovies();
//		newActor.setId(actor.getId());
//		newActor.setActiveSinceYear(actor.getActiveSinceYear());
//		newActor.setBirthDate(actor.getBirthDate());
//		newActor.setMovies(actor.getMovies());
//		newActor.setFirstName(actor.getFirstName());
//		newActor.setLastName(actor.getLastName());
//		return newActor;
		return actor;
	}
	
	@ApiOperation(value = "Delete an actor.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			actorRepo.delete(id);
			return actor;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	@ApiOperation(value = "Create a new actor.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	@ApiOperation(value = "Update an existing actor.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
	
	@ApiOperation(value = "Associate an award to an actor.", notes = "HERE'S SOME SUPER AWESOME NOTES!")
	@PostMapping("{actorId}/awards")
	public Actor associateAnAward(@PathVariable long actorId, @RequestBody Award award) {
		Award newAward = new Award(award.getTitle(), award.getOrganization(), award.getYear());
		Actor actor = actorRepo.findOne(actorId);
		newAward.setActor(actor);
		awardRepo.save(newAward);
		return actor;
	}

}
