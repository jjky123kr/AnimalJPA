package com.Animal.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.UploadFile;

public interface AdoptRepository extends JpaRepository<Adopt,Integer> {

	

	Page<Adopt>findByTitleContaining(String keyword, Pageable pageable);

}
