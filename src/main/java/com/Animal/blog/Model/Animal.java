package com.Animal.blog.Model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int animalid; // 동물 번호
	
	@Column(nullable=false,length=20)
	private String title; // 제목
	
	@Column(length=20)
	private String type; // 동물 타입(강아지, 고양이)
	
	@Column(nullable=false,length=20)
	private String kind; //동물 종류(강아지:말티즈,비숑,포메라니안,등)
	
	@Column(nullable=false,length=20)
	private String name;  // 동물 이름

	@Column(nullable=false,length=10)
	private String gender;  // 동물 성별
	
	@Column(nullable=false,length=20)
	private String neutered; // 중성화/ 건강상태
	
	@Column(nullable=false,length=10)
	private String age;   // 동물 나이
	
	@Column(nullable=false,length=10) // 동물 몸무게
    private String kg;
	  
	@Column(nullable=false,length=100000000)  
	private String content; // 설명
	
	@Column(nullable=false,length=20) // 동물 성격
	private String personality;
	
	@ColumnDefault("0")  
	private int count; //조회수
			
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="member_id")
	private Member member;// 작성자
	
	@CreationTimestamp // 시간이 자동으로 입력 (현재)
	private Timestamp createDate;
	
	
	  
	     
	
	
	
	  
}
