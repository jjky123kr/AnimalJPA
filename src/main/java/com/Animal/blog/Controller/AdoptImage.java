package com.Animal.blog.Controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.Animal.blog.Model.Adopt;
import com.Animal.blog.repository.AdoptRepository;



@Controller
public class AdoptImage {

    @PostMapping("/adopt/image")
    public void upload(MultipartFile[] profileImage) {
    	
        System.out.println("ajax upload"); // 확인 코드

        String uploadFolder = "C:\\upload"; // 파일 경로 지정

        // 폴더 생성 날짜별로 정리한다.
        File uploadPath = new File(uploadFolder, getFolder());
        System.out.println("uploadPath:" + uploadPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multiparFile : profileImage) {
            System.out.println("확인");
            System.out.println("profileImage File name:" + multiparFile.getOriginalFilename());
            System.out.println("profileImage File size:" + multiparFile.getSize());

            String profileImageName = multiparFile.getOriginalFilename(); // 업로드 할 파일 명 찾기
            profileImageName = StringUtils.cleanPath(profileImageName); // "C:\fakepath\" 제거

            System.out.println("only file name:" + profileImageName);

            // 중복 검사 확인
            UUID uuid = UUID.randomUUID();
            profileImageName = uuid.toString() + "_" + profileImageName;

            // File saveFile = new File(uploadFolder,multiparFile.getOriginalFilename());

            File saveFile = new File(uploadFolder, profileImageName);
            //

            try {
                multiparFile.transferTo(saveFile);
                
                
                
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } // end catch

        } // end for

    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("_", File.separator);
    }

}
