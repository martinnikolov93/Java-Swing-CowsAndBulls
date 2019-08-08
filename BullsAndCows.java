import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class BullsAndCows {
    private static Set<Integer> randomNumber;
    private static int counter;
    public static boolean isGuessed;

    static {
        randomNumber = new LinkedHashSet<>();
        counter = 0;
        isGuessed = false;
    }

    public static void startNewGame() {
        isGuessed = false;
        BullsAndCows.pickRandomNumber();
        BullsAndCows.restartCounter();
    }

    public static int getCounter() {
        return counter;
    }

    public static void increaseCounterByOne() {
        counter++;
    }

    public static void restartCounter() {
        counter = 0;
    }

    public static void pickRandomNumber() {
        randomNumber.clear();

        Random random = new Random();

        while (randomNumber.size() < 4) {
            int tempNum = random.nextInt(8) + 1;
            randomNumber.add(tempNum);
        }
    }

    public static int getPickedRandomNumber() {
        String number = "";

        for (Integer value : randomNumber) {
            number += value;
        }

        return Integer.parseInt(number);
    }

    public static String getMessage(int number) {
        String message = "";

        int numberToCompare = getPickedRandomNumber();
        if (isGuessed) {
            message = "Go to menu to start a new game!";
        } else if (number == numberToCompare) {
            isGuessed = true;
            message = number + " - Bulls: 4 | Cows: 0";
            message += System.lineSeparator();
            message += "Congrats! The number is: " + getPickedRandomNumber() + System.lineSeparator();
            message += "It took you " + getCounter() + " tries." + System.lineSeparator();
        } else if (number == 9999) {
            // this is a cheat code for the user to reveal the number
            message = "The number is: " + getPickedRandomNumber();
        } else {
            message = getBullsAndCowsMessage(number);
        }

        return message;
    }

    private static String getBullsAndCowsMessage(int number) {
        int cows = 0;
        int bulls = 0;

        String stringNumber = String.valueOf(number);
        int index = 0;
        for (Integer value : randomNumber) {
            int currentDigit = Character.getNumericValue(stringNumber.charAt(index));

            if (currentDigit == value) {
                bulls++;
                index++;
                continue;
            }

            if (randomNumber.contains(currentDigit)) {
                cows++;
            }

            index++;
        }

        return number + " - Bulls: " + bulls + " | Cows: " + cows;
    }


}
