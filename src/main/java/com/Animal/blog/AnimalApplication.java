package com.Animal.blog;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnimalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalApplication.class, args);
	}

	@Bean(name = "uploadPath")
	public String uploadPath() {
		return "C:\\upload";
	}

}

