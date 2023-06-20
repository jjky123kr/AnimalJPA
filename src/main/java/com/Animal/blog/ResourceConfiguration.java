package com.Animal.blog;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ResourceConfiguration implements WebMvcConfigurer {

	  private String connectPath = "/image/**";
	    private String resourcePath = "file:///C:\\upload";

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler(connectPath)
	                .addResourceLocations(resourcePath);
	    }
	}
