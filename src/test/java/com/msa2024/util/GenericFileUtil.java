package com.msa2024.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GenericFileUtil<T> implements FileUtil<T> {
    private static final String BASE_PATH = "src/test/resources/";

    @Override
    public void writeToFile(String filename, List<T> items) {
        try (Writer writer = new FileWriter(BASE_PATH + filename)) {
            Gson gson = new Gson();
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> readFromFile(String filename) {
        try (Reader reader = new FileReader(BASE_PATH + filename)) {
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
}
