package com.Animal.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AdoptReview;

public interface AdoptReviewRepository extends JpaRepository<AdoptReview, Integer> {
	Page<Adopt>findByTitleContaining(String keyword, Pageable pageable);

	Page<AdoptReview> findByTitle(String keyword, Pageable pageable);
}
