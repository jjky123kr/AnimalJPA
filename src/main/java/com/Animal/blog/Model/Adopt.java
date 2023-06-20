package com.Animal.blog.Model;

import java.beans.Transient;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

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
public class Adopt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스
	
	@NotBlank(message = "제목을 입력하세요.")
	@Column(nullable = false, length = 20)
	private String title; // 제목

	@NotBlank(message = "이름을 입력하세요.")
	@Column(nullable = false, length = 20)
	private String name; // 이름

	@NotBlank(message = "성별을 입력하세요.")
	@Column(nullable = false, length = 20)
	private String gender; // 성별

	@NotNull(message = "옵션을 선택해주세요.")
	@Column(nullable = false, length = 20)
	private String kind; // 종류

	@NotBlank(message = "타입을 입력하세요.")
	@Column(nullable = false, length = 20)
	private String type; // 강아지 or 고양이 종류 기타
	
	@NotBlank(message = "나이를 입력하세요.")
	@Column(nullable = false, length = 20)	
	private String age; // 나이
	
	@NotBlank(message = "무게를 입력하세요.")
	@Column(nullable = false, length = 20)
	private String kg; // 무게

	@NotBlank(message = "건강 상태를 입력하세요.")
	@Column(nullable = false, length = 20)
	private String health; // 건강or중성화

	@NotBlank(message = "성격을 입력하세요.")
	@Column(nullable = false, length = 20)
	private String personality; // 성격


	@Lob
	private String profileImage; // 대표 이미지 설정
	
	@NotBlank(message = "내용을 입력하세요")
	@Lob
	private String content;   
	 
	@ColumnDefault("0")
	private int count; // 조회수

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private Member member;// 작성자
	
	@Enumerated(EnumType.STRING)
	private AdoptionStatus adoptionStatus;

	@CreationTimestamp // 자동으로 현재 시간이 세팅
	private Timestamp createDate;

	@CreationTimestamp // 자동으로 현재 시간이 세팅
	private Timestamp updateDate;

	@OneToMany(mappedBy = "adopt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadFile> uploadfiles = new ArrayList<>();
	

	// 파일 삭제코드
	public void removeUploadFile(UploadFile uploadfile) {
	    uploadfiles.remove(uploadfile); // uploads 컬렉션에서 제거
	    uploadfile.setAdopt(null); // 연관관계 설정 제거
	    deleteFileFrom(uploadfile.getFilePath());
	}

	private void deleteFileFrom(String filePath) {
	    File file = new File(filePath);
	    if (file.exists()) {
	        file.delete();
	    }
	}
	
}
