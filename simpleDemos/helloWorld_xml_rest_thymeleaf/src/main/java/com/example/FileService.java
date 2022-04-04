package com.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * created by dfk
 * 3/23/2022
 */
@Service
public class FileService {
    private static Map<String, byte[]> files=new HashMap<>();

    public byte[] getFile(String fileName) {
        byte[] fileBytes = files.get(fileName);
        return fileBytes != null ? fileBytes : new byte[0];
    }

    public void saveFile(String fileName, byte[] fileBytes) {
        files.put(fileName, fileBytes);
    }
}
