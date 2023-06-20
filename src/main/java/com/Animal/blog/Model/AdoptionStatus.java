package com.Animal.blog.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AdoptionStatus {


	PENDING("입양중"),
	COMPLETED("입양완료"); // Adopted 상태 추가

	    private final String korName;

	    AdoptionStatus(String korName) {
	        this.korName = korName;
	    }

	    public String getKorName() {
	        return korName;
	    }
}

