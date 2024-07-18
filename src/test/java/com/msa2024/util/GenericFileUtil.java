package com.msa2024.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericFileUtil<T> implements FileUtil<T> {
    private String basePath;
    private ObjectMapper objectMapper;

    public GenericFileUtil() {
        this.basePath = "src/test/resources/";
        this.objectMapper = new ObjectMapper();
    }

    public GenericFileUtil(String basePath) {
        this.basePath = basePath;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void writeToFile(String filename, List<T> items) {
        try (Writer writer = new FileWriter(basePath + filename)) {
            Gson gson = new Gson();
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> readFromFile(String filename) {
        try (Reader reader = new FileReader(basePath + filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<T>>() {}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Returning empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> readFromFile(String filename, TypeToken<List<T>> typeToken) {
        try (Reader reader = new FileReader(basePath + filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.: " + filename + ". Returning empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeToFileWithJackson(String filename, List<T> items) {
        try {
            objectMapper.writeValue(new File(basePath + filename), items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> readFromFileWithJackson(String filename, Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(basePath + filename), new TypeReference<List<T>>() {});
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + filename + ". Returning empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
