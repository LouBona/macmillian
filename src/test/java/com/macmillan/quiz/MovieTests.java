package com.macmillan.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class, secure = false)
public class MovieTests extends BaseMovieTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieRepository repo;

	
	@Test
	public void testCreateMovie() throws Exception {
		Movie testMovie = Movie.builder().withName("Test Movie").withYearReleased("1999").withRating("G").build();
		createMovie(testMovie);
	}
	
	@Test
	public void testCreateEmptyMovie() throws Exception {
		// no fields are required, verify that works
		Movie testMovie = new Movie();
		createMovie(testMovie);
	}
	
	private void createMovie(Movie testMovie) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Long newId = 99l;
		
		// expected return
		Movie newMovie = Movie.builder().withId(newId).withName(testMovie.getName()).withYearReleased(testMovie.getYearReleased())
				.withRating(testMovie.getRating()).withGenre(testMovie.getGenre()).build();
		
		Mockito.when(repo.save(Mockito.any(Movie.class))).thenReturn(newMovie);

		MockHttpServletResponse response = addMovie(mockMvc, testMovie);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		// check returned movie
		String resp = response.getContentAsString();
		Movie returnedMovie = objectMapper.readValue(resp, Movie.class);		
		assertTrue(newId.equals(returnedMovie.getId()));
		assertTrue(newMovie.nonIdFieldsEquals(returnedMovie));
	}
	

	@Test
	public void testDeleteMovieSimple() throws Exception {
		MockHttpServletResponse response = deleteMovie(mockMvc, 1l); 
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	// simulate bad id
	@Test
	public void testDeleteMovieFail() throws Exception {
		Mockito.doThrow(new RuntimeException()).when(repo).deleteById(1l);
		MockHttpServletResponse response = deleteMovie(mockMvc, 1l);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());		
		String msg = response.getContentAsString();
		assertEquals("No movie with id 1 present", msg);
	}


	@Test
	public void testUpdateMovieOk() throws Exception {		
		// only id field matters
		Movie testMovie = Movie.builder().withId(5l).build();
		MockHttpServletResponse response = updateMovie(mockMvc, testMovie);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testUpdateMovieNoId() throws Exception {
		Movie testMovie = new Movie();				
		MockHttpServletResponse response = updateMovie(mockMvc, testMovie);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	
}