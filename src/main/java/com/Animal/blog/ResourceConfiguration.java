package com.Animal.blog;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ResourceConfiguration implements WebMvcConfigurer {
	 @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
	        registry.addResourceHandler("/image/**")
	        .addResourceLocations("file:///C:\\upload");      
	        
	       
		
	    }
}
