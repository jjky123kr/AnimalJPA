package com.Animal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Animal.blog.Model.AnimalReport;

public interface AnimalReportRepository extends JpaRepository<AnimalReport, Integer> {

}
