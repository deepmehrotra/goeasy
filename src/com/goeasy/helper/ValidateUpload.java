package com.goeasy.helper;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile; 

public class ValidateUpload {
   public static void validateOfficeData(MultipartFile file){
	   System.out.println("Inside validating");
        if(!file.getContentType().equals("application/vnd.ms-excel"))
            throw new MultipartException("Only excel files accepted!");
    }
} 