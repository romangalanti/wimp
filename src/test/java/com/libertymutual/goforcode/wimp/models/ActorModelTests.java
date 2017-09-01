package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ActorModelTests {
	
	private Actor actor;
	
	@Before
	public void setUp() {
		actor = new Actor();
	}
	
	@Test
	public void test_full_argument_constructor() {
		// Act
		Actor actor = new Actor("Will", "Ferrell", 1965L, new Date(Date.parse("07/16/1967")));

		// Assert
		assertThat(actor.getFirstName()).isEqualTo("Will");
		assertThat(actor.getLastName()).isEqualTo("Ferrell");
		assertThat(actor.getActiveSinceYear()).isEqualTo(1965L);
		assertThat(actor.getBirthDate()).isEqualTo(new Date(Date.parse("07/16/1967")));
	}
	
	@Test
	public void test_firstName_constructor() {
		// Act
		Actor actor = new Actor("Will");

		// Assert
		assertThat(actor.getFirstName()).isEqualTo("Will");
	}

	@Test
	public void test_Id_getter_and_setter_() {
		// Arrange
		actor.setId(1L);
		
		// Act
		Long actual = actor.getId();
		
		// Assert
		assertThat(actual).isEqualTo(1L);
	}
	
	@Test
	public void test_firstName_getter_and_setter() {
		// Arrange
		actor.setFirstName("Randy");
		
		// Act
		String actual = actor.getFirstName();
		
		// Assert
		assertThat(actual).isEqualTo("Randy");
	}
	
	@Test
	public void test_lastName_getter_and_setter() {
		// Arrange
		actor.setLastName("Carlson");
		
		// Act
		String actual = actor.getLastName();
		
		// Assert
		assertThat(actual).isEqualTo("Carlson");
	}
	
	@Test
	public void test_activeSinceYear_getter_and_setter() {
		// Arrange
		actor.setActiveSinceYear(1969L);
		
		// Act
		Long actual = actor.getActiveSinceYear();
		
		// Assert
		assertThat(actual).isEqualTo(1969L);
	}
	
	@Test
	public void test_birthDate_getter_and_setter() {
		// Arrange
		actor.setBirthDate(new Date(Date.parse("07/16/1967")));
		
		// Act
		Date actual = actor.getBirthDate();
		
		// Assert
		assertThat(actual).isEqualTo(new Date(Date.parse("07/16/1967")));
	}
	
	@Test
	public void test_movies_getter_and_setter() {
		// Arrange
		List<Movie> movies = new ArrayList<Movie>();
		actor.setMovies(movies);
		
		// Act
		List<Movie> actual = actor.getMovies();
		
		// Assert
		assertThat(actual).isSameAs(movies);
	}
	
	@Test
	public void test_awards_getter_and_setter() {
		// Arrange
		List<Award> awards = new ArrayList<Award>();
		actor.setAwards(awards);
		
		// Act
		List<Award> actual = actor.getAwards();
		
		// Assert
		assertThat(actual).isSameAs(awards);
	}

}
