package com.reviewBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reviewBox.service.MyReviewBoxListService;

@Controller
public class MyReviewBoxController 
{
	@Autowired
	private MyReviewBoxListService service;
	
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/myReviews";
	}

}
