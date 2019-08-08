public class InputValidator {

    /**
     * Checks if input (string) is a valid 4 digit number
     * returns boolean
     */
    public static boolean isValid(String n) {

        String number = "";

        /* Tries to parse to int */
        try {
            Integer.parseInt(n);
            number = n;
        } catch (Exception e) {
            return false;
        }

        /* If parsed successfully below will check if it's a valid 4 digit number */

        if (number.length() != 4) {
            return false;
        /* Cheat code for user to reveal the number */
        } else if (number.equals("9999")) {
            return true;
        } else if (number.contains("0")) {
            return false;
        } else {
            for (int i = 0; i < number.length(); i++) {
                for (int j = i + 1; j < number.length(); j++) {
                    if (number.charAt(i) == number.charAt(j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
