package com.Animal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import com.Animal.blog.Model.Board;

public interface BoardRepository extends JpaRepository<Board,Integer >{

}
