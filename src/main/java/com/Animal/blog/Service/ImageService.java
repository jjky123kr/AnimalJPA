package com.Animal.blog.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.repository.AdoptRepository;
import com.Animal.blog.repository.AnimalRepository;
import com.Animal.blog.repository.UploadFileRepository;
import com.fasterxml.jackson.databind.ser.std.FileSerializer;

@Service
public class ImageService {
	@Autowired
	private UploadFileRepository uploadFileRepository;
	
	private AdoptRepository adoptRepository;

	private final Path rootLocation;

	public ImageService(String uploadPath) {
		this.rootLocation = Paths.get(uploadPath);
		
		System.out.println(rootLocation.toString());
	}

	// 이미지 DB 저장
	public UploadFile save(MultipartFile file) throws Exception {
		try {
			if (file.isEmpty()) {
				throw new Exception("Filed to save empth file" + file.getOriginalFilename());
			}
            
			
			String saveFileName = fileSave(rootLocation.toString(), file);
			UploadFile saveFile = new UploadFile();
			saveFile.setFileName(file.getOriginalFilename());
			saveFile.setSaveFileName(saveFileName);
			saveFile.setType(file.getContentType());
			saveFile.setSize(file.getResource().contentLength());
			saveFile.setRegisterDate(LocalDateTime.now());
			saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') + '/' + saveFileName);
			uploadFileRepository.save(saveFile);
		
			return saveFile;

		} catch (IOException e) {
			throw new Exception("File to save file" + file.getOriginalFilename(), e);
		}

	}

	public UploadFile load(Long fileId) {
		return uploadFileRepository.findById(fileId).get();
	}

	public String fileSave(String rootLocation, MultipartFile file) throws IOException {
		File uploadDir = new File(rootLocation);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// saveFileName 생성
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + file.getOriginalFilename();
		File saveFile = new File(rootLocation, saveFileName);
		FileCopyUtils.copy(file.getBytes(), saveFile);

		return saveFileName;
	}
}
