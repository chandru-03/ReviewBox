package com.reviewBox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reviewBox.entity.MyReviewBoxList;

@Repository
public interface MyReviewBoxRepository extends JpaRepository<MyReviewBoxList, Integer>
{

}
