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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DonationItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "제목을 입력하세요.")
	@Column(length = 50)
	private String title;
	
	@NotBlank(message = "제목을 입력하세요.")
	@Column(length = 5000)
	private String content;
	
	@NotBlank(message = "제목을 입력하세요.")
	@Column(length = 20)
	private String name;

	@NotNull(message = "옵션을 선택해주세요.")
	private String noticeOption;

	@Column(name = "is_pinned")
	private Boolean isPinned; // 공지글인지 판별 여부

	//수정 삭제 할 경우 회원 번호 값
	@ManyToOne(fetch = FetchType.EAGER) // Many=Board , one=User
	@JoinColumn(name = "userid")
	private Member member; // 작성자

	@CreationTimestamp // 자동으로 현재시간
	private Timestamp createDate;

	@UpdateTimestamp // 자동으로 수정된 시간
	private Timestamp updateDate;

}
