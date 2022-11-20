package com.example.sportplay.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    @PostMapping("/up")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException{
        System.out.println(file.getOriginalFilename());
        String path="D:\\";
        System.out.println(path);
        saveFile(file,path);
        System.out.println("h0");
        return "success";
    }
    public void saveFile(MultipartFile f,String path)throws IOException{
        File upDir=new File(path);
        if(!upDir.exists()){
            upDir.mkdir();
        }
        File file=new File(path+f.getOriginalFilename());
        f.transferTo(file);
    }
}
