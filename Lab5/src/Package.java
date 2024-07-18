/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Lab Assignment 5
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 *
 * This class represents a package with recipient's name, volume, and weight.
 * It includes methods to get and set these values, as well as methods for
 * comparing packages.
 */
import java.util.Comparator;

public class Package implements Comparable<Package> {
    private String recipient;
    private double volume;
    private double weight;
    private static Comparator<Package> comparator;

    public Package(String recipient, double volume, double weight) {
        if (recipient == null || volume <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Invalid package attributes.");
        }
        this.recipient = recipient;
        this.volume = volume;
        this.weight = weight;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient cannot be null.");
        }
        this.recipient = recipient;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        if (volume <= 0) {
            throw new IllegalArgumentException("Volume must be positive.");
        }
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return recipient + "\t" + volume + "\t" + weight;
    }

    @Override
    public int compareTo(Package other) {
        return comparator.compare(this, other);
    }

    public static void setComparator(Comparator<Package> comparator) {
        Package.comparator = comparator;
    }
}
