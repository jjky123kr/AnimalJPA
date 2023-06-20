package com.Animal.blog.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class AdoptSurvey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adoptId")  
	private Adopt adopt;
	  
    @Column(length=20)
	private String name; // 신청인 서명
    
    @Column(length=20)
	private String sex ;      // 성별
	
    @Column(length=20)
	private String age;       // 나이
	
    @Column(length=20)
	private String tel;
	
    @Column(length=20)
	private String email;
	
    @Column(length=20)
	private String atime;
	
    @Column(length=20)
	private String address; // 주소
	
    @Column(length=20)
	private String wedding;  // 결혼 여부
    
    @Column(length=20)
    private String job;
    
	// 질문지
    
    @Column(length=20)
	private String q1;
	
    @Column(length=20)
	private String q2;
	
    @Column(length=20)
	private String q3;
	
    @Column(length=20)
	private String q4;
	
    @Column(length=20)
	private String q5;
	
    @Column(length=20)
	private String q6;
	
    @Column(length=20)
	private String q7;
	
    @Column(length=20)
	private String q8;
	
    @Column(length=20)
	private String q9;
	
    @Column(length=20)
	private String q10;
	
    @Column(length=20)
	private String q11;
	
    @Column(length=20)
	private String q12;
    
    @Enumerated(EnumType.STRING)
	private AdoptionStatus adoptionStatus;
    
   
 	
}
