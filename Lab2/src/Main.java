/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read realnumbers.txt file
        System.out.print("Enter the name of the real numbers file: ");
        String realNumbersFile = scanner.nextLine();
        Double[] realNumbers = readNumbersFromFile(realNumbersFile);

        // Prompt user for sorting method
        System.out.print("Choose a sorting method (bubble, selection, insertion, quick, merge) or 'none' to leave unsorted: ");
        String sortingMethod = scanner.nextLine().toLowerCase();

        switch (sortingMethod) {
            case "bubble":
                ArraySearchSort.bubbleSort(realNumbers);
                break;
            case "selection":
                ArraySearchSort.selectionSort(realNumbers);
                break;
            case "insertion":
                ArraySearchSort.insertionSort(realNumbers);
                break;
            case "quick":
                ArraySearchSort.quickSort(realNumbers, 0, realNumbers.length - 1);
                break;
            case "merge":
                ArraySearchSort.mergeSort(realNumbers, 0, realNumbers.length - 1);
                break;
            case "none":
                // No sorting
                break;
            default:
                System.out.println("Invalid sorting method.");
                return;
        }

        // Output sorted/unsorted values
        System.out.println("Values:");
        for (Double number : realNumbers) {
            System.out.println(number);
        }

        // Read searchnumbers.txt file
        System.out.print("Enter the name of the search numbers file: ");
        String searchNumbersFile = scanner.nextLine();
        Double[] searchNumbers = readNumbersFromFile(searchNumbersFile);

        // Search for each number and output the position
        boolean isSorted = !sortingMethod.equals("none");
        for (Double searchNumber : searchNumbers) {
            int position = isSorted ? ArraySearchSort.binarySearch(realNumbers, searchNumber)
                    : ArraySearchSort.linearSearch(realNumbers, searchNumber);
            if (position >= 0) {
                System.out.println(searchNumber + " is in position " + position);
            } else {
                System.out.println(searchNumber + " is not in the array");
            }
        }
    }

    private static Double[] readNumbersFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int numberOfRecords = Integer.parseInt(reader.readLine().trim());
            Double[] numbers = new Double[numberOfRecords];
            for (int i = 0; i < numberOfRecords; i++) {
                numbers[i] = Double.parseDouble(reader.readLine().trim());
            }
            return numbers;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new Double[0];
        }
    }
}
