/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2  Lab Assignment 5
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 *
 * This program manages packages to be delivered across campus using a binary search tree.
 */
import java.io.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BinarySearchTree<Package> bst = new BinarySearchTree<>();
        System.out.println("Enter the name of a file to read");
        String inputFileName = scanner.nextLine();

        // Set comparator before reading the file
        System.out.println("Would you like to compare the packages by volume, weight, or the recipient's name?");
        String criteria = scanner.nextLine().toLowerCase();

        switch (criteria) {
            case "volume":
                Package.setComparator(Comparator.comparingDouble(Package::getVolume));
                break;
            case "weight":
                Package.setComparator(Comparator.comparingDouble(Package::getWeight));
                break;
            case "name":
                Package.setComparator(Comparator.comparing(Package::getRecipient));
                break;
            default:
                System.out.println("Invalid choice. Choose volume, weight, or name.");
                return;
        }

        // Read input file and insert packages into BST
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String recipient = parts[0];
                double volume = Double.parseDouble(parts[1]);
                double weight = Double.parseDouble(parts[2]);
                Package pkg = new Package(recipient, volume, weight);
                bst.insert(pkg);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    printPackages(bst);
                    break;

                case 2:
                    addPackage(bst);
                    break;

                case 3:
                    removePackage(bst);
                    break;

                case 4:
                    outputPackagesToFile(bst);
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Would you like to");
        System.out.println("1) Print the packages in sorted order to the screen");
        System.out.println("2) Add a package");
        System.out.println("3) Remove a package");
        System.out.println("4) Exit and output the currently held packages to a tab-delimited file named updatedpackages.txt");
    }

    private static void printPackages(BinarySearchTree<Package> bst) {
        System.out.println("Here are all of the packages");
        for (Package pkg : bst.inOrder()) {
            System.out.println(pkg);
        }
    }

    private static void addPackage(BinarySearchTree<Package> bst) {
        System.out.println("Enter the recipient's name:");
        String recipient = scanner.nextLine();
        System.out.println("Enter the package's volume (a decimal number):");
        double volume = scanner.nextDouble();
        System.out.println("Enter the package's weight (a decimal number):");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // consume the newline

        Package pkg = new Package(recipient, volume, weight);
        bst.insert(pkg);
        System.out.println("Package added.");
    }

    private static void removePackage(BinarySearchTree<Package> bst) {
        System.out.println("Enter the weight of the package to remove:");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // consume the newline

        Package toRemove = findPackage(bst, weight);
        if (toRemove != null) {
            bst.delete(toRemove);
            System.out.println("Package removed.");
        } else {
            System.out.println("Package not found.");
        }
    }

    private static void outputPackagesToFile(BinarySearchTree<Package> bst) {
        String outputFileName = "updatedpackages.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Package pkg : bst.inOrder()) {
                bw.write(pkg.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }

    private static Package findPackage(BinarySearchTree<Package> bst, double weight) {
        for (Package pkg : bst.inOrder()) {
            if (pkg.getWeight() == weight) {
                return pkg;
            }
        }
        return null;
    }
}
