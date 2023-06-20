package com.Animal.blog.Model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message = "제목을 입력하세요.")
	@Column(nullable=false,length=20)
	private String title; // 제목
	
	@NotNull(message = "옵션을 선택해주세요.")
	@Column(nullable=false,length=10) 
	private String animalType;  // 동물 타입(강아지, 고양이)	
	
	@NotBlank(message = "종류를 입력하세요.")
	@Column(nullable=false,length=20)
	private String kind; //동물 종류(강아지:말티즈,비숑,포메라니안,등)
	
	@NotBlank(message = "이름을 입력하세요.")
	@Column(nullable=false,length=20)
	private String name;  // 동물 이름

	@NotBlank(message = "성별을 입력하세요.")
	@Column(nullable=false,length=10)
	private String gender;  // 동물 성별
	
	@NotBlank(message = "건강상태를 입력하세요.")
	@Column(nullable=false,length=20)
	private String neutered; // 중성화/ 건강상태
	
	@NotBlank(message = "나이를 입력하세요.")
	@Column(nullable=false,length=10)
	private String age;   // 동물 나이
	
	@NotBlank(message = "몸무게를 입력하세요.")
	@Column(nullable=false,length=10) // 동물 몸무게
    private String kg;	
	
	
	@Lob
	private String content; // 이미지
	
	@NotBlank(message = "성격을 입력하세요.")
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
