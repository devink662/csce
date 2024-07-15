/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the Java source file: ");
        String fileName = scanner.nextLine();

        MyStack<CharacterInFile> stack = new MyStack<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            boolean errorFound = false;

            while ((line = reader.readLine()) != null && !errorFound) {
                lineNumber++;
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch == '(' || ch == '[' || ch == '{') {
                        stack.push(new CharacterInFile(ch, lineNumber, i + 1));
                    } else if (ch == ')' || ch == ']' || ch == '}') {
                        if (stack.isEmpty()) {
                            System.out.printf("Unmatched closing symbol '%c' on line %d, column %d%n", ch, lineNumber, i + 1);
                            errorFound = true;
                            break;
                        }
                        CharacterInFile lastOpen = stack.pop();
                        if (!isMatchingPair(lastOpen.getCharacter(), ch)) {
                            System.out.printf("Mismatched symbol '%c' on line %d, column %d is invalid. Expected a close for the character '%c' on line %d in column %d%n", ch, lineNumber, i + 1, lastOpen.getCharacter(), lastOpen.getLine(), lastOpen.getPosition());
                            errorFound = true;
                            break;
                        }
                    }
                }
            }

            if (!errorFound) {
                while (!stack.isEmpty()) {
                    CharacterInFile unclosed = stack.pop();
                    System.out.printf("Unclosed symbol '%c' at line %d, column %d%n", unclosed.getCharacter(), unclosed.getLine(), unclosed.getPosition());
                    errorFound = true;
                }

                if (!errorFound) {
                    System.out.println("No mismatched (), [], or {} found.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}
