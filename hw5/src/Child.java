/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

public class Child implements Comparable<Child> {
    private String name;
    private int candy;

    public Child(String name, int candy) {
        if (name == null || candy < 0) {
            throw new IllegalArgumentException("Invalid values for child properties");
        }
        this.name = name;
        this.candy = candy;
    }

    public String getName() {
        return name;
    }

    public int getCandy() {
        return candy;
    }

    public void setCandy(int candy) {
        if (candy >= 0) {
            this.candy = candy;
        } else {
            throw new IllegalArgumentException("Candy must be non-negative");
        }
    }

    @Override
    public int compareTo(Child other) {
        return Integer.compare(this.candy, other.candy);
    }

    @Override
    public String toString() {
        return String.format("%s has %d pieces of candy", name, candy);
    }
}

