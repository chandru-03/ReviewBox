package com.reviewBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewBox.entity.ReviewBox;
import com.reviewBox.repository.ReviewBoxRepository;

@Service
public class ReviewBoxService 
{
  @Autowired
  private ReviewBoxRepository bRepo;
  public void save(ReviewBox b)
  {
	  bRepo.save(b);
  }
  public List<ReviewBox> getAllMovie()
  {
		return bRepo.findAll();
  }
  public ReviewBox getMovieById(int id) 
  {
		return bRepo.findById(id).get();
  }
  public void deleteById(int id) {
		bRepo.deleteById(id);
	}
  
  public List<ReviewBox> findByName(String name){
	  return bRepo.findByName(name);
  }
  

}
