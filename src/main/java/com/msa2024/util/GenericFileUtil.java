package com.msa2024.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GenericFileUtil<T> implements FileUtil<T> {
    private String basePath;

    public GenericFileUtil() {
        this.basePath = "src/test/resources/";
    }

    public GenericFileUtil(String basePath) {
        this.basePath = basePath;
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
        return null;
    }

    @Override
    public List<T> readFromFile(String filename, TypeToken<List<T>> typeToken) {
        try (Reader reader = new FileReader(basePath + filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Returning empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
