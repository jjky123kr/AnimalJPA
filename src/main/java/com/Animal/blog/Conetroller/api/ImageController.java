package com.Animal.blog.Conetroller.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.Service.ImageService;
import com.Animal.blog.repository.UploadFileRepository;

@Controller
public class ImageController {

private static final String Paths = null;

@Autowired
private ImageService imageService;

private UploadFileRepository uploadFileRepository;

@Autowired
ResourceLoader resourceLoader;

// summernote 이미지 
 @PostMapping("/image")
 public ResponseEntity<?>imageUpload(@RequestParam("file")MultipartFile file){
	 try {
		 UploadFile uploadFile = imageService.save(file);
		 return ResponseEntity.ok().body("/image/"+uploadFile.getId());
	 }catch(Exception e) {
		 e.printStackTrace();
		 return ResponseEntity.badRequest().build();
	 }
  }
 // summernote 이미지 불러오기
 @GetMapping("/image/{fileid}")
 public ResponseEntity<?>saveFile(@PathVariable Long fileid){
	 try {
		 UploadFile uploadFile = imageService.load(fileid);
		 org.springframework.core.io.Resource resource = resourceLoader.getResource("file:" + uploadFile.getFilePath());
		 return ResponseEntity.ok().body(resource);
	 }catch(Exception e) {
		 e.printStackTrace();
		 return ResponseEntity.badRequest().build();
	 }

}


}