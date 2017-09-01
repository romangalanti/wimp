package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class AwardModelTests {

	private Award award;
	
	@Before
	public void setUp() {
		award = new Award();
	}
	
	@Test
	public void test_full_argument_constructor() {
		// Act
		Award award = new Award("Will", "Dist", 1969);

		// Assert
		assertThat(award.getTitle()).isEqualTo("Will");
		assertThat(award.getOrganization()).isEqualTo("Dist");
		assertThat(award.getYear()).isEqualTo(1969);
	}

	@Test
	public void test_Id_getter_and_setter_() {
		// Arrange
		award.setId(1L);
		
		// Act
		Long actual = award.getId();
		
		// Assert
		assertThat(actual).isEqualTo(1L);
	}
	
	@Test
	public void test_title_getter_and_setter() {
		// Arrange
		award.setTitle("Randy");
		
		// Act
		String actual = award.getTitle();
		
		// Assert
		assertThat(actual).isEqualTo("Randy");
	}
	
	@Test
	public void test_organization_getter_and_setter() {
		// Arrange
		award.setOrganization("Dist");
		
		// Act
		String actual = award.getOrganization();
		
		// Assert
		assertThat(actual).isEqualTo("Dist");
	}
	
	@Test
	public void test_year_getter_and_setter() {
		// Arrange
		award.setYear(1969);
		
		// Act
		int actual = award.getYear();
		
		// Assert
		assertThat(actual).isEqualTo(1969);
	}
	
	@Test
	public void test_actor_getter_and_setter() {
		// Arrange
		Actor actor = new Actor();
		award.setActor(actor);
		
		// Act
		Actor actual = award.getActor();
		
		// Assert
		assertThat(actual).isSameAs(actor);
	}

}
