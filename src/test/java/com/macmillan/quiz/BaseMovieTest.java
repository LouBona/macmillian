package com.macmillan.quiz;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macmillan.quiz.Movie;

/**
 * Methods here wrap the REST invocation plumbing.
 *
 */
public class BaseMovieTest {

	private ObjectMapper objectMapper = new ObjectMapper();

	
	protected MockHttpServletResponse addMovie(MockMvc mockMvc, Movie testMovie) throws Exception {
		String jsonTestMovie = objectMapper.writeValueAsString(testMovie);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/movie/create")
				.accept(MediaType.APPLICATION_JSON).content(jsonTestMovie).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		return response;
	}

	
	protected MockHttpServletResponse updateMovie(MockMvc mockMvc, Movie testMovie) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTestMovie = objectMapper.writeValueAsString(testMovie);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/movie/update")
				.accept(MediaType.APPLICATION_JSON).content(jsonTestMovie).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		return response;
	}

	
	protected MockHttpServletResponse deleteMovie(MockMvc mockMvc, Long movieId) throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/movie/delete/" + movieId);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		return response;
	}

	
	protected List<Movie> getMovies(MockMvc mockMvc) throws Exception {
		RequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/api/movie/list")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();

		MockHttpServletResponse response2 = result2.getResponse();
		assertEquals(HttpStatus.OK.value(), response2.getStatus());

		String resp = response2.getContentAsString();

		List<Movie> movies = objectMapper.readValue(resp, new TypeReference<List<Movie>>() {});

		return movies;
	}

}
