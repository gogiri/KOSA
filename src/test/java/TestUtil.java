import java.util.Date;

public class TestUtil {

    public static void main(String[] args) {
        testFileUtil();
        testDateUtil();
        testInputValidator();
    }

    private static void testFileUtil() {
        System.out.println("FileUtil 테스트 중...");

        String testFilename = "testfile.ser";
        String testContent = "이것은 FileUtil 테스트를 위한 내용입니다.";

        // writeFile과 readFile 메서드 테스트
        FileUtil.writeFile(testFilename, testContent);
        String loadedContent = FileUtil.readFile(testFilename);
        System.out.println("작성된 내용: " + testContent);
        System.out.println("불러온 내용: " + loadedContent);

        if (testContent.equals(loadedContent)) {
            System.out.println("FileUtil 테스트 통과!");
        } else {
            System.out.println("FileUtil 테스트 실패.");
        }
    }

    private static void testDateUtil() {
        System.out.println("DateUtil 테스트 중...");

        Date currentDate = new Date();
        String formattedDate = DateUtil.formatDate(currentDate);
        Date parsedDate = DateUtil.parseDate(formattedDate);

        System.out.println("현재 날짜: " + currentDate);
        System.out.println("포맷된 날짜: " + formattedDate);
        System.out.println("파싱된 날짜: " + parsedDate);

        if (currentDate.equals(parsedDate)) {
            System.out.println("DateUtil 테스트 통과!");
        } else {
            System.out.println("DateUtil 테스트 실패.");
        }
    }

    private static void testInputValidator() {
        System.out.println("InputValidator 테스트 중...");

        // 이메일 테스트
        String validEmail = "test@example.com";
        String invalidEmail = "test@.com";

        System.out.println("유효한 이메일 테스트 (" + validEmail + "): " + InputValidator.isValidEmail(validEmail));
        System.out.println("유효하지 않은 이메일 테스트 (" + invalidEmail + "): " + InputValidator.isValidEmail(invalidEmail));

        // 이름 테스트
        String validName = "홍길동";
        String invalidName = "홍";

        System.out.println("유효한 이름 테스트 (" + validName + "): " + InputValidator.isValidName(validName));
        System.out.println("유효하지 않은 이름 테스트 (" + invalidName + "): " + InputValidator.isValidName(invalidName));

        // 비밀번호 테스트
        String validPassword = "Password1";
        String invalidPassword = "pass";

        System.out.println("유효한 비밀번호 테스트 (" + validPassword + "): " + InputValidator.isValidPassword(validPassword));
        System.out.println("유효하지 않은 비밀번호 테스트 (" + invalidPassword + "): " + InputValidator.isValidPassword(invalidPassword));

        // 역할 테스트
        String validRole = "student";
        String invalidRole = "guest";

        System.out.println("유효한 역할 테스트 (" + validRole + "): " + InputValidator.isValidRole(validRole));
        System.out.println("유효하지 않은 역할 테스트 (" + invalidRole + "): " + InputValidator.isValidRole(invalidRole));
    }
}
