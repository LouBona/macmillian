package com.macmillan.quiz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.annotation.Generated;

/**
 * My preference is to see the code, so this class contains generated code. 
 * I find it helpful while debugging. 
 * A less-cluttered approach would be to use a tool like lombok to generate the get/set
 * and builder at runtime.
 *  
 * @author lbona
 *
 */
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String genre;
	private String yearReleased;
	private String rating;

	// Spring needs a no-arg constructor
	public Movie() {}
	

	@Generated("SparkTools")
	private Movie(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.genre = builder.genre;
		this.yearReleased = builder.yearReleased;
		this.rating = builder.rating;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getYearReleased() {
		return yearReleased;
	}
	
	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genre=" + genre + ", yearReleased=" + yearReleased
				+ ", rating=" + rating + "]";
	}


	// utility method to aid testing
	public boolean nonIdFieldsEquals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (yearReleased == null) {
			if (other.yearReleased != null)
				return false;
		} else if (!yearReleased.equals(other.yearReleased))
			return false;
		return true;
	}


	/**
	 * Creates builder to build {@link Movie}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}


	/**
	 * Builder to build {@link Movie}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Long id;
		private String name;
		private String genre;
		private String yearReleased;
		private String rating;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withGenre(String genre) {
			this.genre = genre;
			return this;
		}

		public Builder withYearReleased(String yearReleased) {
			this.yearReleased = yearReleased;
			return this;
		}

		public Builder withRating(String rating) {
			this.rating = rating;
			return this;
		}

		public Movie build() {
			return new Movie(this);
		}
	}
	

}
