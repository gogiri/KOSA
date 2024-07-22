package com.msa2024.util;

public class InputValidator {

    /**
     * 제공된 이메일이 유효한지 확인
     * 유효한 이메일은 "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$" 패턴과 일치해야 합니다.
     * 이 패턴은 이메일이 다음과 같은 구성을 갖추도록 보장합니다:
     * - "@" 기호 앞에 알파벳, 숫자, 점(.), 밑줄(_), 퍼센트(%), 더하기(+), 하이픈(-)을 포함합니다.
     * - 도메인 부분에 알파벳 및 하이픈(-)을 포함
     * - 2에서 6자의 유효한 최상위 도메인(TLD)을 포함
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    /**
     * 제공된 이름이 유효한지 확인
     * 유효한 이름은 "^[a-zA-Z\\s]{3,30}$" 패턴과 일치해야 합니다.
     * 이 패턴은 이름이 다음과 같은 구성을 갖추도록 보장
     * - 알파벳 문자와 공백을 포함
     * - 최소 3자 이상 30자 이하의 길이
     */
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]{3,30}$");
    }

    /**
     * 제공된 전화번호가 유효한지 확인
     * 유효한 전화번호는 "^(010|012)-\\d{4}-\\d{4}$" 패턴과 일치.
     * 이 패턴은 전화번호가 다음과 같은 구성을 갖추도록 보장
     * - 010 또는 012로 시작
     * - 네 자리 숫자와 하이픈(-)으로 구분된 네 자리 숫자를 포함
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^(010|012)-\\d{4}-\\d{4}$");
    }

//    public static boolean isValidPassword(String password) {
//        return password != null && password.matches("^\\d{4}$");
//    }
//
//    public static boolean isValidRole(String role) {
//        return "student".equals(role) || "professor".equals(role) || "admin".equals(role);
//    }


}
