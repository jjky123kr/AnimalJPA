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
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//ORM->Java(다른언어)object ->테이블로 매핑해주는 기술
@Entity // User 클래스가 읽어서 Mysql에 테이블이 생성이 된다. 
 public class Board {
 
  @Id
  
  @GeneratedValue(strategy=GenerationType.IDENTITY) 
  private int id;
  
  @Column(nullable=false,length=100) 
  private String title;
 
  @Column(nullable=false,length= 5000) 
  @Lob private String content; //썸머노트 라이브러리 <html>태그가 섞어서 디지인
  
  private int count; //조회수
  
  // DB 오브젝트를 저장을 할수 없고지만, FK ,자바를 오브젝트를 저장된다. //자바가 DB 방식으로 따라가서, FK키로 저장을 한다.
  // ORM은 @JoinColumn(name="userid")
  
  @ManyToOne(fetch=FetchType.EAGER)// Many=Board , one=User
  @JoinColumn(name="userid") 
  private Member member; // 작성자
  
  
  @CreationTimestamp // 자동으로 현재시간
  private Timestamp createDate;

}

