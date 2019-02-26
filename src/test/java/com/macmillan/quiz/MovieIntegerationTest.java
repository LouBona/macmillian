package com.macmillan.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MovieIntegerationTest extends BaseMovieTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	    
    @Spy
	@InjectMocks
	private MovieController controller = new MovieController();
    
	@Autowired
	MovieRepository movieRepo;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}


	// Keeping the db alive to test the interaction of the methods
	@Test
	public void test() throws Exception {
		
		// unnecessary, but for clarity show empty list at start
		List<Movie> movies = getMovies(mockMvc);
		assertEquals(0, movies.size());		
		
		Movie testMovie = Movie.builder().withName("Test Movie").withYearReleased("1999").withRating("G").build();		
		MockHttpServletResponse response = addMovie(mockMvc, testMovie);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		// save the same movie again, will get different id (no dup field restrictions)
		response = addMovie(mockMvc, testMovie);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
			
		movies = getMovies(mockMvc);
		assertEquals(2, movies.size());
		// ids not null, not same, other fields match
		assertFalse(movies.get(0).getId().equals(movies.get(1).getId()));
		assertTrue(movies.get(0).nonIdFieldsEquals(movies.get(1)));
		
		Movie movie2 = movies.get(1);
		movie2.setName("Updated Test Movie");
		response = updateMovie(mockMvc, movie2);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		movies = getMovies(mockMvc);
		// still only 2 movies
		assertEquals(2, movies.size());
		Movie updatedMovie2 = movies.get(1);
		// ids same, names not
		assertTrue(movie2.getId().equals(updatedMovie2.getId()));
		assertTrue("Updated Test Movie".equals(updatedMovie2.getName()));
		
		response = deleteMovie(mockMvc, movies.get(0).getId());
		movies = getMovies(mockMvc);
		// now only 1 movie
		assertEquals(1, movies.size());
		Movie remainingMovie = movies.get(0);
		// same movie
		assertTrue(remainingMovie.getId().equals(updatedMovie2.getId()));
		assertTrue("Updated Test Movie".equals(updatedMovie2.getName()));
			
	}
	
	
	
}