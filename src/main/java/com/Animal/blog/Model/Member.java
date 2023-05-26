package com.Animal.blog.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.criteria.Order;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//ORM->Java(다른언어)object ->테이블로 매핑해주는 기술
@Entity // User 클래스가 읽어서 Mysql에 테이블이 생성이 된다. 
@DynamicInsert //insert시에 null인 필드를 제외 해준다.
public class Member {
	
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id; // 오라클: 시퀀스 , Mysql: auto_increment
   
   //@Pattern(regexp= "^[a-z]{1}[a-z0-9]{5,10}+$",message="영문 숫자 조합 6~10자리")
   //@NotBlank(message = "아이디를 입력하세요")
   
   @Column(nullable=false,length=20)
   private String username;
   
  // @Pattern(regexp ="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", 
  //		   message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요." )
   @Column(nullable=false,length=100)
   private String password;
   
  // @NotBlank(message = "이름을 입력하세요.")
  // @Column(nullable=false,length=30)
   private String name;  
   
 //@JsonFormat으로 Date 타입 직렬화 (Jaon으로 날짜를 전송할떄 date 값을 패턴을지정하여 DB에 저장)
 	@JsonFormat(pattern = "yyyy.MM.dd") 
 	private String brithday;
   
   @Column(nullable=false,length=10)
  // @Pattern(regexp="^[\\d]{2,3}-[\\d]{3,4}-[\\d]{4}+$",message="휴대폰 번호 입력하세요")
   private String phon1;
   
   @Column(nullable=false,length=10)
   private String phon2;
   
   @Column(nullable=false,length=10)
   private String phon3;
   
   
  // @Email(message = "이메일 형식에 맞지 않습니다.")
  // @NotBlank(message = "이메일을 입력하세요")
   @Column(nullable=false,length=30)
   private String email;
   
  
  // @NotBlank(message = "우편 번호을 입력하세요")
   @Column(nullable=false,length=30)
   private String post;
   
   
   //@NotBlank(message = "주소를 입력하세요")
   @Column(nullable=false,length=30)
   private String address1;
   
   //@NotBlank(message = "상세주소를 입력하세요")
   @Column(nullable=false,length=30)
   private String address2;
   
   @Enumerated(EnumType.STRING)
   private RoleType role; //Enum을 사용한다. => 도메인 (범위)를 정해준다. //user,admin ,manager
   
   @CreationTimestamp // 시간이 자동으로 입력 (현재 )
   private Timestamp createDate;
   
//   @Column(nullable=false,length=30)
//   private String staut;
   

}     
