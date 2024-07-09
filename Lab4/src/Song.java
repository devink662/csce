/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.util.Objects;
public class Song {
    private String title;
    private String genre;
    private String artist;
    private int length;

    // Default constructor
    public Song() {
        this.title = "";
        this.genre = "";
        this.artist = "";
        this.length = 0;
    }

    // Parameterized constructor
    public Song(String title, String genre, String artist, int length) {
        if (title != null && genre != null && artist != null && length > 0) {
            this.title = title;
            this.genre = genre;
            this.artist = artist;
            this.length = length;
        } else {
            throw new IllegalArgumentException("Invalid input values");
        }
    }

    // Accessor methods
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public int getLength() {
        return length;
    }

    // Mutator methods
    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }

    public void setGenre(String genre) {
        if (genre != null) {
            this.genre = genre;
        } else {
            throw new IllegalArgumentException("Genre cannot be null");
        }
    }

    public void setArtist(String artist) {
        if (artist != null) {
            this.artist = artist;
        } else {
            throw new IllegalArgumentException("Artist cannot be null");
        }
    }

    public void setLength(int length) {
        if (length > 0) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length must be positive");
        }
    }

    // toString method
    @Override
    public String toString() {
        return title + "\t" + genre + "\t" + artist + "\t" + length;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return length == song.length &&
                title.equals(song.title) &&
                genre.equals(song.genre) &&
                artist.equals(song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, artist, length);
    }
}
