package com.msa2024.util;

public class InputValidator {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]{3,30}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public static boolean isValidRole(String role) {
        return "student".equals(role) || "professor".equals(role) || "admin".equals(role);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^(010|012)-\\d{4}-\\d{4}$");
    }
}
