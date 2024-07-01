/*
 * Main.java
 * CSCE 146 Homework Assignment 2
 * Author: [Your Name]
 * Date: [Date]
 *
 * This program reads search terms from a file into an array, sorts the array
 * by term, allows the user to search for terms, updates the search count for
 * found terms, and writes the updated search terms to a new file.
 */

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read searchTerms.txt file
        System.out.print("Enter the name of the search terms file: ");
        String searchTermsFile = scanner.nextLine();
        SearchTerm[] searchTerms = readSearchTermsFromFile(searchTermsFile);

        // Sort the array of search terms by term
        ArraySearchSort.bubbleSort(searchTerms);

        // Allow the user to search for terms
        while (true) {
            System.out.print("Enter a search term (or type 'exit' to quit): ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            SearchTerm searchKey = new SearchTerm(userInput, 0);
            int index = ArraySearchSort.binarySearch(searchTerms, searchKey);

            if (index >= 0) {
                SearchTerm foundTerm = searchTerms[index];
                foundTerm.setSearchCount(foundTerm.getSearchCount() + 1);
                System.out.println("The term '" + userInput + "' has been searched for " + foundTerm.getSearchCount() + " times.");
            } else {
                System.out.println("The term '" + userInput + "' is not in the array.");
            }
        }

        // Sort the array of search terms by search count in descending order
        ArraySearchSort.sortBySearchCountDesc(searchTerms);

        // Write the updated array to updatedSearchTerms.txt
        writeSearchTermsToFile("updatedSearchTerms.txt", searchTerms);
    }

    /*
     * Reads search terms from a file into an array.
     *
     * @param fileName The name of the file to read from.
     * @return An array of SearchTerm objects.
     */
    private static SearchTerm[] readSearchTermsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int numberOfRecords = Integer.parseInt(reader.readLine().trim());
            SearchTerm[] searchTerms = new SearchTerm[numberOfRecords];
            for (int i = 0; i < numberOfRecords; i++) {
                String[] fields = reader.readLine().split("\t");
                searchTerms[i] = new SearchTerm(fields[0], Integer.parseInt(fields[1]));
            }
            return searchTerms;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new SearchTerm[0];
        }
    }

    /*
     * Writes an array of search terms to a file.
     *
     * @param fileName The name of the file to write to.
     * @param searchTerms The array of SearchTerm objects to write.
     */
    private static void writeSearchTermsToFile(String fileName, SearchTerm[] searchTerms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (SearchTerm searchTerm : searchTerms) {
                writer.write(searchTerm.getTerm() + "\t" + searchTerm.getSearchCount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
