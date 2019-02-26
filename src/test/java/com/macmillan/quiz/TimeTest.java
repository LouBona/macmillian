package com.macmillan.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.macmillan.quiz.UtilController;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UtilController.class, secure = false)
public class TimeTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	/**
	 * Nothing to really test here as it's all built in Java functionality, so just make
	 * sure we get an OK response.
	 */
    @Test
    public void greeting() throws Exception {
    	RequestBuilder requestBuilder2 = MockMvcRequestBuilders
				.get("/api/timeOfDay")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();

		MockHttpServletResponse response2 = result2.getResponse();
		assertEquals(HttpStatus.OK.value(), response2.getStatus());
		
		String resp = response2.getContentAsString();
		assertNotNull(resp);
	}

}
