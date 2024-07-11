/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 * Homework Assignment 4
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 *
 * This class represents a character in a file, including its position within the file.
 */

public class CharacterInFile {
    private char character;
    private int line;
    private int position;

    // Default constructor
    public CharacterInFile() {
        this.character = '\0';
        this.line = -1;
        this.position = -1;
    }

    // Parameterized constructor
    public CharacterInFile(char character, int line, int position) {
        this.character = character;
        this.line = line;
        this.position = position;
    }

    // Accessor methods
    public char getCharacter() {
        return character;
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }

    // Mutator methods
    public void setCharacter(char character) {
        this.character = character;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Character: " + character + ", Line: " + line + ", Position: " + position;
    }
}
