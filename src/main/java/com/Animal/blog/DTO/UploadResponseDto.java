package com.Animal.blog.DTO;

import java.time.LocalDateTime;

public class UploadResponseDto {
	
	 private String fileId;
	 private String fileName;
	 private LocalDateTime uploadDate;

	  

//	    // 생성자
//	    public UploadResponseDto(String fileId, String fileName, LocalDateTime uploadDate) {
//	        this.fileId = fileId;
//	        this.fileName = fileName;
//	        this.uploadDate = uploadDate;
//	    }

		public String getFileId() {
	        return fileId;
	    }

	    public void setFileId(String fileId) {
	        this.fileId = fileId;
	    }

	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }

	    public LocalDateTime getUploadDate() {
	        return uploadDate;
	    }

	    public void setUploadDate(LocalDateTime uploadDate) {
	        this.uploadDate = uploadDate;
	    }
	}