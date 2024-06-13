package com.mth.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comments implements Serializable {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "movie_id")
	    private Movie movie;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "rate", nullable = false)
	    private double rate;
	    
		public Comments() {
			super();
		}

		public Long getId() {
			return id;
		}
		
		public Comments(String description, double rate, String username, String avatar) {
	        this.description = description;
	        this.rate = rate;
	        this.user = new User();  // Khởi tạo đối tượng User
	        this.user.setUsername(username);
	        this.user.setAvatar(avatar);
	    }
		
		public void setId(Long id) {
			this.id = id;
		}

		public Movie getMovie() {
			return movie;
		}

		public void setMovie(Movie movie) {
			this.movie = movie;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}
}
