package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {
	
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	private MovieApiController controller;
	
	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}
	
	@Test
	public void test_getAll_return_movies_returned_by_the_repo() {
		// Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		
		when(movieRepo.findAll()).thenReturn(movies);
		
		//Act
		List<Movie> actual = controller.getAll();
		
		//Assert
		assertThat(movies.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll();
	}
	
	@Test
	public void test_getOne_returns_movie_returned_from_repo() throws StuffNotFoundException {
		// Arrange
		Movie bradPitt = new Movie();
		when(movieRepo.findOne(69L)).thenReturn(bradPitt);
		
		// Act
		Movie actual = controller.getOne(69L);
		
		// Assert
		assertThat(actual).isSameAs(bradPitt);
		verify(movieRepo).findOne(69L);
	}
	
	@Test
	public void test_getOne_throws_stuffNotFound_when_no_movie_returned_from_repo() {
		try {
			controller.getOne(7);
			fail("The controller did not throw the stuffNotFoundException");
		} catch(StuffNotFoundException snfe) {}
	}
	
	@Test
	public void test_delete_returns_movie_deleted_when_found() {
		// Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(3L)).thenReturn(movie);
		
		// Act
		Movie actual = controller.delete(3L);
		
		//Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).delete(3L);
		verify(movieRepo).findOne(3L);
	}
	
	@Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccessException() throws StuffNotFoundException {
		// Arrange
		when(movieRepo.findOne(8L)).thenThrow(new EmptyResultDataAccessException(0));
		
		// Act
		Movie actual = controller.delete(8L);
		
		// Assert
		assertThat(actual).isNull();
		verify(movieRepo).findOne(8L);
	}
	
	@Test
	public void test_that_a_new_movie_is_created() {
		// Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		// Act
		Movie actual = controller.create(movie);
		
		// Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void test_that_a_new_movie_is_update() {
		// Arrange
		Movie movie = new Movie();
		movie.setId(69L);
		when(movieRepo.save(movie)).thenReturn(movie);
		
		// Act
		Movie actual = controller.update(movie, 69L);
		
		// Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void test_associate_an_actor_to_a_movie() {
		// Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		movie.setId(69L);
		actor.setId(20L);
		when(movieRepo.findOne(69L)).thenReturn(movie);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		// Act
		Movie actual = controller.associateAnActor(69L, actor);
		
		// Assert
		assertThat(movie).isSameAs(actual);
		assertThat(actor).isSameAs(actor);
		verify(movieRepo).findOne(69L);
		verify(actorRepo).findOne(20L);
	}

}
