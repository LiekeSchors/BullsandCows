package bullscows;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int userInputLengthOfSecretCode = 0;
        System.out.println("Input the length of the secret code:");
        if (sc.hasNextInt()) {
            userInputLengthOfSecretCode = sc.nextInt();
        } else {
            System.out.println("Error: " + userInputLengthOfSecretCode + " isn't a valid number.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int userInputNrOfPosSymbols = sc.nextInt();


        if (userInputLengthOfSecretCode > userInputNrOfPosSymbols || userInputLengthOfSecretCode <= 0) {
            System.out.println("Error: it's not possible to generate a code with a length of " + userInputLengthOfSecretCode + " with " + userInputNrOfPosSymbols + " unique symbols.");
            return;
        }


        if (userInputNrOfPosSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z");
            return;
        }

        String secretCode = generateSecretCode3(userInputLengthOfSecretCode, userInputNrOfPosSymbols)[0];
        System.out.print("The secret is prepared: ");
        System.out.println("*".repeat(userInputLengthOfSecretCode) + generateSecretCode3(userInputLengthOfSecretCode, userInputNrOfPosSymbols)[1]);

        System.out.println("Okay, let's start a game!");

        int counter = 1;
        int bullCounter = 0;

        while (bullCounter != secretCode.length()) {
            System.out.println("Turn " + counter + ":");

            String userInput = sc.next();
            String[] input = userInput.split("");
            bullCounter = countBullsAndCows(input, secretCode, userInputLengthOfSecretCode);

            counter++;
        }
        // System.out.println("Grade: " + bullCounter + " bulls");
        System.out.println("Congratulations! You guessed the secret code.");

    }

    private static int countBullsAndCows(String[] input, String secretCode, int userInputNrOfDigits) {
        String[] secretCodeArray = secretCode.split("");
        int bullCounter = 0;
        int cowCounter = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(secretCodeArray[i])) {
                bullCounter++;
            } else if (!input[i].equals(secretCodeArray[i]) && secretCode.contains(input[i])) {
                cowCounter++;
            }
        }

        System.out.print("Grade:");
        if (bullCounter == 0 && cowCounter == 0) {
            System.out.println(" None.");
        } else if (bullCounter == 1 && cowCounter == 1) {
            System.out.println(" 1 bull and 1 cow");
        } else if (bullCounter > 1 && cowCounter > 1) {
            System.out.println(bullCounter + " bulls and " + cowCounter + " cows");
        } else if (bullCounter == 1 && cowCounter > 1) {
            System.out.println(" 1 bull and " + cowCounter + " cows");
        } else if (bullCounter > 1 && cowCounter == 1) {
            System.out.println(" bulls and " + " 1 cow");
        } else if (bullCounter > 1 && bullCounter < userInputNrOfDigits) {
            System.out.println(" " + bullCounter + " bulls");
        } else if (cowCounter > 1) {
            System.out.println(" " + cowCounter + " cows");
        } else if (bullCounter == 1) {
            System.out.println(" 1 bull");
        } else if (cowCounter == 1) {
            System.out.println(" 1 cow");
        } else if (bullCounter == userInputNrOfDigits) {
            System.out.println(bullCounter + " bulls");
        }
        return bullCounter;
    }

    private static String[] generateSecretCode3(int userInputLength, int userInputPosSymbols) {
        String allChars = "0123456789abcdefghijklmnopqrstuvwxyz";
        String posCharsUser = allChars.substring(0, userInputPosSymbols);

        String[] posCharsByUser = posCharsUser.split("");
        String anzeige = "";
        for (int i = 0; i < userInputPosSymbols; i++) {
            if (userInputPosSymbols == 10) {
                anzeige = " (0-9).";
            } else if (userInputPosSymbols < 10) {
                anzeige = " (0-" + (userInputPosSymbols - 1) + ").";
            } else {
                anzeige = " (0-9, a-" + posCharsByUser[userInputPosSymbols-1] + ").";
            }
        }


        List<String> posCharsList = Arrays.asList(posCharsByUser);
        Collections.shuffle(posCharsList);

        String[] shuffledDigits = posCharsList.toArray(new String[0]);
        String current;
        StringBuilder mySecretCode = new StringBuilder();

        for (int i = 0; i < userInputLength; i++) {
            current = shuffledDigits[i];
            if (current != null) {
                mySecretCode.append(current);
            }
        }
        String[] output = {mySecretCode.toString(), anzeige};

        return output;
    }


}


