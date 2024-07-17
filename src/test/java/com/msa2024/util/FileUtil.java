package com.msa2024.util;

import java.util.List;

public interface FileUtil<T> {
    void writeToFile(String filename, List<T> items);
    List<T> readFromFile(String filename);
}

