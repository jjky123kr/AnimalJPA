package com.Animal.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Animal.blog.Model.DonationItems;


public interface ItemsRepository extends JpaRepository<DonationItems, Integer> {

	 List<DonationItems> findByIsPinnedOrderByCreateDateDesc(boolean isPinned);
	 Page<DonationItems> findByIsPinnedOrderByCreateDateDesc(boolean isPinned, Pageable pageable);
	}

