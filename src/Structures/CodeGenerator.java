package Structures;


import java.security.SecureRandom;

public class CodeGenerator {
    public static String randomSuffixGenerator(int len){
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < len; i++){
            sb.append(numbers.charAt(secureRandom.nextInt(9)));
        }
        return sb.toString();
    }
    public static String generateCode(String row, String seat, String date, String name) {
        StringBuilder curKey = new StringBuilder();
        curKey.append(row).append(seat).append(date).append(name).append(randomSuffixGenerator(4));
        return curKey.toString();
    }

}
