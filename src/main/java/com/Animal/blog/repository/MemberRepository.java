package com.Animal.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Animal.blog.Model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	 Optional<Member>findByUsername(String username);

}
