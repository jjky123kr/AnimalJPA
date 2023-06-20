package com.Animal.blog.Model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OrderBy;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Support {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "제목을 입력하세요.")
	@Column(length=50)
	private String title;
	
	@NotBlank(message = "이름을 입력하세요.")
	@Column(length=50)
	private String name;
	
	@NotBlank(message = "타입을 선택하세요.")
	@Column(length=50)
	private String type;
	
	
	@Lob
	private String profileImage;
	
	private int count; //조회수
	
	@NotBlank(message = "내용을 선택하세요.")
	@Column(length=9000)
	private String content;
	
	@ManyToOne(fetch=FetchType.EAGER)// Many=Board , one=User 
	@JoinColumn(name="userid")
	private Member member; // 작성자 
	
	@OneToMany(mappedBy = "support", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다 (난 FK가 아니예요) DB에 컬럼을 만들지 마세요.
	@JsonIgnoreProperties({"support", "member"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp // 자동으로 현재시간 
	private Timestamp createDate;
	
	
}
