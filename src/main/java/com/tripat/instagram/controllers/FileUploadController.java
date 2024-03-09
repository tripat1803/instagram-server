package com.tripat.instagram.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
    private final String FILE_PATH = "C:/Users/tripa/OneDrive/Desktop/Uploads/";

    @PostMapping("")
    public String uploadFile(@RequestParam("image") MultipartFile file) throws IOException{
        file.transferTo(new File(FILE_PATH+file.getOriginalFilename()));
        return "";
    }
}