package com.Animal.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResponseDto<T> {

	
	int status;
	 T data;
	 private String message;
	 
}
