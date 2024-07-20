package com.msa2024.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public interface FileUtil<T> {
    void writeToFile(String filename, List<T> items);
    List<T> readFromFile(String filename);
    List<T> readFromFile(String filename, TypeToken<List<T>> typeToken);

    List<T> readFromFile(String filename, TypeReference<List<T>> typeReference);

    void writeToFileWithJackson(String filename, List<T> items);
    List<T> readFromFileWithJackson(String filename, Class<T> clazz);

    List<T> readFromFileWithJackson(String filename, TypeReference<List<T>> typeReference);
}