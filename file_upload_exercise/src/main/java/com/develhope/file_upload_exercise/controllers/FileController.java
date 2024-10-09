package com.develhope.file_upload_exercise.controllers;

import com.develhope.file_upload_exercise.services.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    // parameters
    @Autowired
    private FileStorageService fileStorageService;

    //metodo upload
    @PostMapping("/upload")
    public ResponseEntity<List<String>> upload(@RequestBody MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file: files){
            String singleUploaddileNames = fileStorageService.upload(file);
            fileNames.add(singleUploaddileNames);
        }

        return ResponseEntity.ok(fileNames);
    }
    // metod download
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@PathVariable String fileName, HttpServletResponse response) throws IOException{
        System.out.println("downloading " + fileName);
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension){
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        return ResponseEntity.ok(fileStorageService.download(fileName));

    }
}
