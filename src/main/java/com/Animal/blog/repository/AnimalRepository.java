package com.Animal.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Animal.blog.Model.Animal;
import com.Animal.blog.Model.UploadFile;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {


}
