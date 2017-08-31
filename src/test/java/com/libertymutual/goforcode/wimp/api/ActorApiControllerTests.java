package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

public class ActorApiControllerTests {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorApiController controller;
	@Captor
	private ArgumentCaptor<Award> captor;
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		MockitoAnnotations.initMocks(this);
		controller = new ActorApiController(actorRepo, awardRepo);
	}

	@Test
	public void test_getAll_return_actors_returned_by_the_repo() {
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		
		when(actorRepo.findAll()).thenReturn(actors);
		
		//Act
		List<Actor> actual = controller.getAll();
		
		//Assert
		assertThat(actors.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();
	}
	
	@Test
	public void test_getOne_returns_actor_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		Actor bradPitt = new Actor();
		when(actorRepo.findOne(69L)).thenReturn(bradPitt);
		
		// Act
		Actor actual = controller.getOne(69L);
		
		// Assert
		assertThat(actual).isSameAs(bradPitt);
		verify(actorRepo).findOne(69L);
	}
	
	@Test
	public void test_getOne_throws_stuffNotFound_when_no_actor_returned_from_repo() {
		try {
			controller.getOne(7);
			fail("The controller did not throw the stuffNotFoundException");
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_returns_actor_deleted_when_found() {
		// Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(3L)).thenReturn(actor);
		
		// Act
		Actor actual = controller.delete(3L);
		
		//Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).delete(3L);
		verify(actorRepo).findOne(3L);
	}
	
	@Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccessException() throws StuffNotFoundException {
		// Arrange
		when(actorRepo.findOne(8L)).thenThrow(new EmptyResultDataAccessException(0));
		
		// Act
		Actor actual = controller.delete(8L);
		
		// Assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(8L);
	}
	
	@Test
	public void test_that_a_new_actor_is_created() {
		// Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		// Act
		Actor actual = controller.create(actor);
		
		// Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_that_a_new_actor_is_update() {
		// Arrange
		Actor actor = new Actor();
		actor.setId(69L);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		// Act
		Actor actual = controller.update(actor, 69L);
		
		// Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_associate_an_award_to_an_actor() {
		// Arrange
		Award awardTemplate = new Award("fjk", "dujisd", 2007);
		Actor actor = new Actor();
		actor.setId(69L);
		when(actorRepo.findOne(69L)).thenReturn(actor);
		when(awardRepo.save(awardTemplate)).thenReturn(awardTemplate);
		
		// Act
		Actor actual = controller.associateAnAward(69L, awardTemplate);
		
		// Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).findOne(69L);
		verify(awardRepo).save(captor.capture());
		Award newAward = captor.getValue();
		assertThat(newAward.getTitle()).isEqualTo(awardTemplate.getTitle());
		assertThat(newAward.getOrganization()).isEqualTo(awardTemplate.getOrganization());
		assertThat(newAward.getYear()).isEqualTo(awardTemplate.getYear());
	}

}
