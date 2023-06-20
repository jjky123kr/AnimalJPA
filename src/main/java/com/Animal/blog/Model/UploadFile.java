package com.Animal.blog.Model;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity
public class UploadFile {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column  
	private String fileName;                //예류.jpg
	
	@Column
	private String saveFileName;            //uuid예류.jpg
	
	@Column
	private String filePath;                // D:/image/uuid예류.jpg
	
	@Column
	private String type;                   // image/jpeg
	
	@Column
	private boolean contentType;             // true 와 false 이미지 확인 코드
	
	private long size;                      // 4476873 (byte)
	
	private LocalDateTime registerDate;     // 2020-02-07 12:27:37.857
	
	@ManyToOne
    @JoinColumn(name = "adopt_id")
    private Adopt adopt;
		
	/* 
	 * @OneToOne
	 * 
	 * @JoinColumn(name="adoptId") private Adopt adopt;
	 */
	//List list
	@OneToMany(mappedBy="uploadFileId")
	//private List<Like>likes = new ArrayList<>();
	
     @Transient
     private int likeCount;
     
    @CreationTimestamp
 	private Timestamp createDate;
    
 	@CreationTimestamp
 	private Timestamp updateDate;


}



