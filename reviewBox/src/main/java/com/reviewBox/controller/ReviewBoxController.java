package com.reviewBox.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.reviewBox.entity.ReviewBox;
import com.reviewBox.entity.Review;
import com.reviewBox.service.ReviewBoxService;
import com.reviewBox.service.ReviewService;

@Controller
public class ReviewBoxController {
	
	@Autowired
	private ReviewBoxService service;
	
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/addFilms")
	public String bookRegister() 
	{
		return "addFilms";
	}
	
	@GetMapping("/films")
	public ModelAndView getMovies() {
		List<ReviewBox>list=service.getAllMovie();
		System.out.println("Fetched Movies: " + list.size());
		if(list.isEmpty()){
			System.out.println("No movies found in the database!");
		}
		return new ModelAndView("films","films",list);
	}
	
	@Autowired
	private ReviewService reviewService;

	@PostMapping("/saveReview/{movieId}")
	public String saveReview(@PathVariable("movieId") int movieId, 
	                         @RequestParam("title") String title,
	                         @RequestParam("reviewerName") String reviewerName,
	                         @RequestParam("review") String comment,
	                         @RequestParam("rating") int rating) {
	    ReviewBox movie = service.getMovieById(movieId);
	    if (movie != null) {
	        Review review = new Review();
	        review.setReviewBox(movie);
	        review.setTitle(title);
	        review.setReviewerName(reviewerName);
	        review.setComment(comment);
	        review.setRating(rating);
	        review.setCreatedAt(new Date());
	        
	        reviewService.saveReview(review);
	    }
	    return "redirect:/viewMovie/" + movieId;
	}

	
	@GetMapping("/filmdetails")
	public String filmDetails() {
		
		return "filmdetails";
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute ReviewBox m, Model model, @RequestParam("imageFile") MultipartFile file) {
		try {
			 System.out.println("File received: " + file.getOriginalFilename());
		List<ReviewBox> movie = service.findByName(m.getName());
		if(!movie.isEmpty()) {
			model.addAttribute("errorMessage","This Movie Already Exists. So please kindly search for a Review");
			return "addFilms";
		}
		
		if(!file.isEmpty()) {
			String folder = "src/main/resources/static/images/";
			String fileName = file.getOriginalFilename();
			Path path = Paths.get(folder, fileName);
			Files.write(path, file.getBytes());
			
			m.setImage(fileName);
			System.out.println("Image successfully saved at: " + fileName);
		} else {
			m.setImage("default-movie.png");
			System.out.println("No file uploaded, using default image.");
		}
		
		service.save(m);
		} catch (Exception e){
			 e.printStackTrace();
			 model.addAttribute("errorMessage","Sorry! Failed to Upload Your Image.");
			 return "addFilms";
		}
		return "redirect:/films";
	}

	
	@GetMapping("/myReviews")
	public String myReviews(Model model) {
	    List<Review> reviews = reviewService.getAllReviews();

	    // Debugging: Log each review and its movie name
	    for (Review r : reviews) {
	        if (r.getReviewBox() == null) {
	            System.out.println("Error: Review ID " + r.getId() + " has null ReviewBox");
	        } else {
	            System.out.println("Review: " + r.getReviewBox().getName());
	        }
	    }

	    model.addAttribute("reviews", reviews);
	    return "myReviews";
	}

	
	
	@RequestMapping("/viewMovie/{id}")
	public String editBook(@PathVariable("id") String id, Model model) {
	    try {
	        int movieId = Integer.parseInt(id);
	        ReviewBox movie = service.getMovieById(movieId);
	        
	        double averageOfRating = reviewService.getAverageRating(movieId);
					        
	        model.addAttribute("reviewl", movie);
	        model.addAttribute("averageOfRating", String.format("%.1f", averageOfRating));
	        
	        return "filmdetails";
	    } catch (NumberFormatException e) {
	        System.err.println("Invalid movie ID: " + id);
	        return "redirect:/films";
	    }
	}
	
	@GetMapping("/deleteReview/{id}")
	public String deleteReview(@PathVariable("id") int id) {
	    reviewService.deleteById(id);
	    return "redirect:/myReviews";
	}
	
	
	@GetMapping("/editReview/{id}")
	public String editReview(@PathVariable("id") int id, Model model) {
	    Review review = reviewService.getReviewById(id);
	    if (review == null) {
	        System.out.println("Error: Review ID " + id + " not found!");
	        return "redirect:/myReviews";
	    }

	    if (review.getReviewBox() == null) {
	        System.out.println("Warning: Review ID " + id + " has no associated ReviewBox!");
	        review.setReviewBox(new ReviewBox()); // Prevent null reference issues
	    }
	    
	    
	    List<Review> reviews = reviewService.getAllReviews();
	    for (Review r : reviews) {
	        if (r.getReviewBox() == null) {
	            System.out.println("Error: Review ID " + r.getId() + " has null ReviewBox");
	        } else {
	            System.out.println("Review: " + r.getReviewBox().getName());
	        }
	    }


	    model.addAttribute("review", review);
	    return "editReview";
	}
	
	@PostMapping("/updateReview")
	public String updateReview(@ModelAttribute Review updatedReview) {
	    System.out.println("Received Review ID: " + updatedReview.getId());
	    System.out.println("Received Title: " + updatedReview.getTitle());
	    System.out.println("Received Comment: " + updatedReview.getComment());
	    System.out.println("Received Rating: " + updatedReview.getRating());

	    reviewService.updateReview(updatedReview);
	    return "redirect:/myReviews";
	}

}
