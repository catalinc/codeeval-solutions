import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/172/
 */
public class CardNumberValidation {

    private static boolean isValid(String cardNumber) {
        int checkSum = 0;
        boolean shouldDouble = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            char ch = cardNumber.charAt(i);
            if (ch == ' ') continue;
            int digit = Character.digit(ch, 10);
            if (shouldDouble) {
                digit += digit;
                if (digit >= 10) {
                    digit = 1 + (digit % 10);
                }
            }
            checkSum += digit;
            shouldDouble = !shouldDouble;
        }
        return checkSum % 10 == 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String cardNumber;
            while ((cardNumber = reader.readLine()) != null) {
                System.out.println(isValid(cardNumber) ? 1 : 0);
            }
        }
    }

}
