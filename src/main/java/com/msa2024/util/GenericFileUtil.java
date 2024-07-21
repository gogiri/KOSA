package com.msa2024.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenericFileUtil<T> {
    private String basePath;
    private ObjectMapper objectMapper;

    public GenericFileUtil(String basePath) {
        this.basePath = basePath;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록
    }

    public void createFileIfNotExists(String filename) throws IOException {
        File file = new File(basePath + filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public List<T> readFromFileWithJackson(String filename, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(basePath + filename);
            if (!file.exists()) {
                return null;
            }
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToFileWithJackson(String filename, List<T> items) {
        try {
            objectMapper.writeValue(new File(basePath + filename), items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
