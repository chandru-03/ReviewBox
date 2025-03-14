package com.reviewBox.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ReviewBox
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String image;
	private String name;
	private String director;
	private String cast;
	private String year;
	private String genre;
	private String description;
	private double duration;
	private String music;
	private String language;
	
	 @OneToMany(mappedBy = "reviewBox", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Review> reviews = new ArrayList<>();
	
	private String title;
	private String reviewerName;
	private String comment;
	private double rating;


	public ReviewBox(int id,String image, String name, String director, String cast, String year, String genre, String description,double duration, String music, String language, String title,String reviewerName, String comment, double rating) 
	{
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.year = year;
		this.cast = cast;
		this.description = description;
		this.duration = duration;
		this.music = music;
		this.language = language;
		
		this.title = title;
		this.reviewerName = reviewerName;
		this.comment= comment;
		this.rating = rating;

	}
	public ReviewBox() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
	public List<Review> getReviews() {
	    return reviews;
	}

	public void setReviews(List<Review> reviews) {
	    this.reviews = reviews;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

}
