package Structures;


import java.security.SecureRandom;
/**
 * Utility class for generating ticket codes with row, seat, date, and random suffix.
 */
public class CodeGenerator {

    /**
     * Generates a string with random digits
     *
     * @return string of random digits
     */
    public static String randomSuffixGenerator(int len){
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < len; i++){
            sb.append(numbers.charAt(secureRandom.nextInt(9)));
        }
        return sb.toString();
    }
    public static String generateCode(int row, int seat, String date, String name) {
        StringBuilder curKey = new StringBuilder();
        curKey.append(row).append("-").append(seat).append(date).append(randomSuffixGenerator(2));
        return curKey.toString();
    }

}
