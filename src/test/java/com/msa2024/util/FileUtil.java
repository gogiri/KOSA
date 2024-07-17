package com.msa2024.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.msa2024.user.User;

public class FileUtil {
    private static final String BASE_PATH = "src/test/resources/";

    public static void writeToFile(String filename, List<User> users) {
        try (Writer writer = new FileWriter(BASE_PATH + filename)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> readFromFile(String filename) {
        try (Reader reader = new FileReader(BASE_PATH + filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Returning empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeAnnouncementsToFile(String filename, List<String> announcements) {
        try (Writer writer = new FileWriter(BASE_PATH + filename)) {
            Gson gson = new Gson();
            gson.toJson(announcements, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readAnnouncementsFromFile(String filename) {
        try (Reader reader = new FileReader(BASE_PATH + filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<String>>() {}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Returning empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
