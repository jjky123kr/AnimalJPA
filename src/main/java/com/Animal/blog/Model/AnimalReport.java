package com.Animal.blog.Model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class AnimalReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false,length=20)
	private String name;
	
	@Column(nullable=false,length=20)
	private String title;
	
	@Column(length=5000)
	private String content;
	
	 @ManyToOne(fetch=FetchType.EAGER)// Many=Board , one=User
	 @JoinColumn(name="userid") 
	   private Member member; // 작성자
	 
	  @CreationTimestamp // 자동으로 현재시간
	  private Timestamp createDate;
}
