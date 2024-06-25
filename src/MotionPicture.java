/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.util.Objects;

public class MotionPicture implements Comparable<MotionPicture> {
    private String id;
    private String type;
    private String title;
    private int startYear;
    private int endYear;
    private int runTime;
    private String genres;
    private double averageRating;
    private int numberOfRatings;

    // Default constructor
    public MotionPicture() {
        this.id = "";
        this.type = "";
        this.title = "";
        this.startYear = 0;
        this.endYear = -1;
        this.runTime = 0;
        this.genres = "";
        this.averageRating = 0.0;
        this.numberOfRatings = 0;
    }

    // Parameterized constructor
    public MotionPicture(String id, String type, String title, int startYear, int endYear, int runTime, String genres, double averageRating, int numberOfRatings) {
        this.setId(id);
        this.setType(type);
        this.setTitle(title);
        this.setStartYear(startYear);
        this.setEndYear(endYear);
        this.setRunTime(runTime);
        this.setGenres(genres);
        this.setAverageRating(averageRating);
        this.setNumberOfRatings(numberOfRatings);
    }

    public String getId() {
        return id;
    }

    // Accessor methods
    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getRunTime() {
        return runTime;
    }

    public String getGenres() {
        return genres;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    // Mutator methods
    public void setId(String id) {
        if (id != null) {
            this.id = id;
        }
    }
    public void setType(String type) {
        if (type != null) {
            this.type = type;
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }

    public void setStartYear(int startYear) {
        if (startYear >= 0) {
            this.startYear = startYear;
        }
    }

    public void setEndYear(int endYear) {
        if (endYear >= startYear || endYear == -1) {
            this.endYear = endYear;
        }
    }

    public void setRunTime(int runTime) {
        if (runTime >= 0) {
            this.runTime = runTime;
        }
    }

    public void setGenres(String genres) {
        if (genres != null) {
            this.genres = genres;
        }
    }

    public void setAverageRating(double averageRating) {
        if (averageRating >= 0.0 && averageRating <= 10.0) {
            this.averageRating = averageRating;
        }
    }

    public void setNumberOfRatings(int numberOfRatings) {
        if (numberOfRatings >= 0) {
            this.numberOfRatings = numberOfRatings;
        }
    }

    //method to get the year range
    public String getYearRange(Integer startYear, Integer endYear) {
        // Return a string representation of the starting year and ending year
        // "years: startYear - endYear" if endYear is not -1, otherwise just "year"
        return endYear != 0 ? "years: " + startYear + " - " + endYear : "year: " + startYear;
    }

    // toString method
    @Override
    public String toString() {
        // Return a string representation of the starting year and ending year
        String years = startYear + (endYear != -1 ? " - " + endYear : "");
        return "type: " + type + ' ' +
                "title: " + title + ' ' +
                getYearRange(startYear, endYear) + ' ' + // call getYearRange method
                "runTime: " + runTime + ' ' +
                "genres: " + genres + ' ' +
                "averageRating: " + averageRating + ' ' +
                "numberOfRatings: " + numberOfRatings;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotionPicture that = (MotionPicture) o;
        return id.equals(that.id) &&
                startYear == that.startYear &&
                endYear == that.endYear &&
                runTime == that.runTime &&
                Double.compare(that.averageRating, averageRating) == 0 &&
                numberOfRatings == that.numberOfRatings &&
                Objects.equals(type, that.type) &&
                Objects.equals(title, that.title) &&
                Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, startYear, endYear, runTime, genres, averageRating, numberOfRatings);
    }

    // compareTo method for sorting
    @Override
    public int compareTo(MotionPicture other) {
        if (this.averageRating != other.averageRating) {
            return Double.compare(other.averageRating, this.averageRating);
        } else {
            return Integer.compare(other.numberOfRatings, this.numberOfRatings);
        }
    }
}
