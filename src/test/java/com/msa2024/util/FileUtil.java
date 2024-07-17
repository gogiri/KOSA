package com.msa2024.util;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public interface FileUtil<T> {
    void writeToFile(String filename, List<T> items);
    List<T> readFromFile(String filename);

    List<T> readFromFile(String filename, TypeToken<List<T>> typeToken);
}

