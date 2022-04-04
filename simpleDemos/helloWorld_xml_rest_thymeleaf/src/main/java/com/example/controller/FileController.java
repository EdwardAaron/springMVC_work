package com.example.controller;

import com.example.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * created by dfk
 * 3/23/2022
 */
@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    //从文件系统中下载文件
    @GetMapping("/download/filesystem/{fileName}")
    public ResponseEntity<byte[]> downloadFileFromFileSystem(
            @PathVariable String fileName,
            HttpServletRequest request) throws IOException {
        //中文乱码
        String encodeFileName = URLEncoder.encode(fileName, "utf-8");

        String path = request.getSession().getServletContext().getRealPath("/static/files/");
        path = path + fileName;
        //将文件读入字节中
        InputStream inputStream = new FileInputStream(path);
        byte[] fileBytes = new byte[inputStream.available()];
        inputStream.read(fileBytes);

        //设置头
        MultiValueMap headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + encodeFileName);
        return new ResponseEntity(fileBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/upload/filesystem")
    public String uploadFileToFileSytem(MultipartFile photo, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/static/files/");
        String fileName = photo.getOriginalFilename();
        System.out.println(fileName);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        photo.transferTo(new File(path + File.separator + fileName));
        return "success";
    }

    //上传文件，文件保存到其他位置，该方法保存到内存中
    @PostMapping("/upload/internal")
    public String uploadFileToOther(MultipartFile photo) throws IOException {
        byte[] bytes = photo.getBytes();
        String encodedFileName = URLEncoder.encode(photo.getOriginalFilename(), "utf-8");
        fileService.saveFile(encodedFileName, bytes);
        return "success";
    }


    @GetMapping("/download/internal/{fileName}")
    public ResponseEntity<byte[]> downloadFileFromOtherSystem(
            @PathVariable String fileName) throws IOException {
        String encodeFileName = URLEncoder.encode(fileName, "utf-8");
        byte[] bytes = fileService.getFile(encodeFileName);
        MultiValueMap headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + encodeFileName);
        return new ResponseEntity(bytes, headers, HttpStatus.OK);
    }
}
