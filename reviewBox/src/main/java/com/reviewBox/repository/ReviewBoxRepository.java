package com.reviewBox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reviewBox.entity.ReviewBox;


@Repository
public interface ReviewBoxRepository  extends JpaRepository<ReviewBox, Integer>
{

	List<ReviewBox> findByName(String name);
}
