package com.Animal.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Animal.blog.Model.UploadFile;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

   
}
  