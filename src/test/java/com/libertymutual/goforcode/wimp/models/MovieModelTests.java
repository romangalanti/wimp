package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MovieModelTests {
	
	private Movie movie;
	
	@Before
	public void setUp() {
		movie = new Movie();
	}
	
	@Test
	public void test_full_argument_constructor() {
		// Act
		Movie movie = new Movie("Will", new Date(Date.parse("07/16/1967")), 1965L, "Dist");

		// Assert
		assertThat(movie.getTitle()).isEqualTo("Will");
		assertThat(movie.getReleaseDate()).isEqualTo(new Date(Date.parse("07/16/1967")));
		assertThat(movie.getBudget()).isEqualTo(1965L);
		assertThat(movie.getDistributor()).isEqualTo("Dist");
	}
	
	@Test
	public void test_title_and_distributor_constructor() {
		// Act
		Movie movie = new Movie("Will", "Dist");

		// Assert
		assertThat(movie.getTitle()).isEqualTo("Will");
		assertThat(movie.getDistributor()).isEqualTo("Dist");
	}

	@Test
	public void test_Id_getter_and_setter_() {
		// Arrange
		movie.setId(1L);
		
		// Act
		Long actual = movie.getId();
		
		// Assert
		assertThat(actual).isEqualTo(1L);
	}
	
	@Test
	public void test_title_getter_and_setter() {
		// Arrange
		movie.setTitle("Randy");
		
		// Act
		String actual = movie.getTitle();
		
		// Assert
		assertThat(actual).isEqualTo("Randy");
	}
	
	@Test
	public void test_releaseDate_getter_and_setter() {
		// Arrange
		movie.setReleaseDate(new Date(Date.parse("07/16/1967")));
		
		// Act
		Date actual = movie.getReleaseDate();
		
		// Assert
		assertThat(actual).isEqualTo(new Date(Date.parse("07/16/1967")));
	}
	
	@Test
	public void test_budget_getter_and_setter() {
		// Arrange
		movie.setBudget(1969L);
		
		// Act
		Long actual = movie.getBudget();
		
		// Assert
		assertThat(actual).isEqualTo(1969L);
	}
	
	@Test
	public void test_distributor_getter_and_setter() {
		// Arrange
		movie.setDistributor("Dist");
		
		// Act
		String actual = movie.getDistributor();
		
		// Assert
		assertThat(actual).isEqualTo("Dist");
	}
	
	@Test
	public void test_actor_list_getter_and_setter() {
		// Arrange
		List<Actor> actors = new ArrayList<Actor>();
		movie.setActors(actors);
		
		// Act
		List<Actor> actual = movie.getActors();
		
		// Assert
		assertThat(actual).isSameAs(actors);
	}
	
	@Test
	public void test_addActor_works() {
		// Arrange
		Actor actor = new Actor();
		
		// Act
		movie.addActor(actor);
		
		// Assert
		assertThat(movie.getActors().contains(actor));
	}
	
}
