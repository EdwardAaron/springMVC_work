package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * created by dfk
 * 4/4/2022
 */
@Controller
public class Hello {
    private Map<String, byte[]> database = new HashMap<>();
    @GetMapping("/")
    public String hello() {
        return "index";
    }
    @GetMapping("/arithmeticexception")
    public void throwArithmeticException() {
        throw new ArithmeticException("算数错误");
    }

    //上传文件，文件保存到其他位置，该方法保存到内存中
    @PostMapping("/upload")
    public String uploadFileToOther(MultipartFile photo) throws IOException {
        byte[] bytes = photo.getBytes();
        String encodedFileName = URLEncoder.encode(photo.getOriginalFilename(), "utf-8");
        database.put(encodedFileName, bytes);
        return "success";
    }

    //下载
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFileFromOtherSystem(
            @PathVariable String fileName) throws IOException {
        String encodeFileName = URLEncoder.encode(fileName, "utf-8");
        byte[] bytes = database.get(encodeFileName);
        MultiValueMap headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + encodeFileName);
        return new ResponseEntity(bytes, headers, HttpStatus.OK);
    }
}
