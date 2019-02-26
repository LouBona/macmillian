package com.macmillan.quiz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Time of day functionality has nothing to do with Movie management
 * so it lives here. If I were to fully implement this project each 
 * Controller would be in its own separately deployed micro-service.
 *  
 * @author lbona
 *
 */
@RestController
@RequestMapping("/api")
public class UtilController {

	public static final String dateFormat = "hh:mm:ss a  MM/dd/yyyy";
	    
	@GetMapping("/timeOfDay")	    
	public ResponseEntity<String> greeting() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String text = date.format(formatter);
		return ResponseEntity.ok().body(text);
	}
	
}
