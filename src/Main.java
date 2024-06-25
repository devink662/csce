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

    // Generic static sort method
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) < 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input file name
        System.out.print("Enter the name of the motion picture file: ");
        String fileName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int numberOfRecords = Integer.parseInt(reader.readLine().trim());
            reader.readLine(); // Skip the field names line

            MotionPicture[] motionPictures = new MotionPicture[numberOfRecords];
            for (int i = 0; i < numberOfRecords; i++) {
                String[] fields = reader.readLine().split("[\t]");
                motionPictures[i] = new MotionPicture(
                        // if field isn't null, parse it to an integer, otherwise set it to 0
                        fields[0] != null && !fields[0].equals("null") ? fields[0] : "",
                        fields[1] != null && !fields[1].equals("null") ? fields[1] : "",
                        fields[2] != null && !fields[2].equals("null") ? fields[2] : "",
                        fields[3] != null && !fields[3].equals("null") ? Integer.parseInt(fields[3]) : 0,
                        fields[4] != null && !fields[4].equals("null") ? Integer.parseInt(fields[4]) : 0,
                        fields[5] != null && !fields[5].equals("null") ? Integer.parseInt(fields[5]) : 0,
                        fields[6] != null && !fields[6].equals("null") ? fields[6] : "",
                        fields[7] != null && !fields[7].equals("null") ? Double.parseDouble(fields[7]) : 0.0,
                        fields[8] != null && !fields[8].equals("null") ? Integer.parseInt(fields[8]) : 0
                );
            }

            // Sort by average rating and number of ratings
            sort(motionPictures);

            // Prompt user for filter criteria
            System.out.print("Enter the type (or 'all'): ");
            String type = scanner.nextLine().toLowerCase();
            System.out.print("Enter the genre (or 'all'): ");
            String genre = scanner.nextLine().toLowerCase();
            System.out.print("Enter the number of titles to display: ");
            int numberOfTitles = Integer.parseInt(scanner.nextLine());

            // Display the top rated titles that match the criteria
            int count = 0;
            for (MotionPicture mp : motionPictures) {
                if ((type.equals("all") || mp.getType().toLowerCase().equals(type)) &&
                        (genre.equals("all") || Arrays.asList(mp.getGenres().toLowerCase().split(",")).contains(genre))) {
                    System.out.println(mp);
                    count++;
                    if (count >= numberOfTitles) {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }
}
