package chat.model;

import java.time.LocalDate;

public class Movie {
	private String title;
	private String genre;
	private String ratingMPAA;
	private String review;
	private int length;
	private LocalDate releaseDate;
	private double starScore;
	
	public Movie(String title, String genre, String ratingMPAA, String review, int length, LocalDate releaseDate, double starScore) {
		this.title = title;
		this.genre = genre;
		this.ratingMPAA = ratingMPAA;
		this.review = review;
		this.length = length;
		this.releaseDate = releaseDate;
		this.starScore = starScore;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getRatingMPAA() {
		return ratingMPAA;
	}
	
	public String getReview() {
		return review;
	}
	
	public int getLength() {
		return length;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public double getStarScore() {
		return starScore;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setRatingMPAA(String ratingMPAA) {
		this.ratingMPAA = ratingMPAA;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setStarScore(double starScore) {
		this.starScore = starScore;
	}
	
	public String toString() {
		return "Not yet implemented";
	}
}
