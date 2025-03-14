package com.reviewBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewBox.entity.MyReviewBoxList;
import com.reviewBox.repository.MyReviewBoxRepository;

@Service
public class MyReviewBoxListService 
{
	@Autowired
	private MyReviewBoxRepository myReview;
	
	public void saveMyBooks(MyReviewBoxList reviewl) {
		myReview.save(reviewl);
	}
	
	public List<MyReviewBoxList> getAllMyBooks(){
		return myReview.findAll();
	}
	
	public void deleteById(int id) {
		myReview.deleteById(id);
	}

}
