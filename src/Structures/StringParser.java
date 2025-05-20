//package Structures;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StringParser {
//    public static String parseString(String input){
//        List<String> tokens = new ArrayList<String>();
//        int startPosition = 0;
//        boolean isInQuotes = false;
//        for (int currentPosition = 0; currentPosition < input.length(); currentPosition++) {
//            if (input.charAt(currentPosition) == '\"') {
//                isInQuotes = !isInQuotes;
//            }
//            else if (input.charAt(currentPosition) == ' ' && !isInQuotes) {
//                tokens.add(input.substring(startPosition, currentPosition));
//                startPosition = currentPosition + 1;
//            }
//        }
//
//        String lastToken = input.substring(startPosition);
//        if (lastToken.equals(" ")) {
//            tokens.add("");
//        } else {
//            tokens.add(lastToken);
//        }
//    }
//}
