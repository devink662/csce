/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

import java.util.Objects;

public class SearchTerm implements Comparable<SearchTerm> {
    private String term;
    private int searchCount;

    // Default constructor
    public SearchTerm() {
        this.term = "";
        this.searchCount = 0;
    }

    // Parameterized constructor
    public SearchTerm(String term, int searchCount) {
        this.term = term;
        this.searchCount = searchCount;
    }

    // Accessor methods
    public String getTerm() {
        return term;
    }

    public int getSearchCount() {
        return searchCount;
    }

    // Mutator methods
    public void setTerm(String term) {
        if (term != null) {
            this.term = term;
        }
    }

    public void setSearchCount(int searchCount) {
        if (searchCount >= 0) {
            this.searchCount = searchCount;
        }
    }

    // toString method
    @Override
    public String toString() {
        return "SearchTerm{" +
                "term='" + term + '\'' +
                ", searchCount=" + searchCount +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchTerm that = (SearchTerm) o;
        return searchCount == that.searchCount &&
                Objects.equals(term.toLowerCase(), that.term.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(term.toLowerCase(), searchCount);
    }

    // compareTo method for sorting by term
    @Override
    public int compareTo(SearchTerm other) {
        return this.term.toLowerCase().compareTo(other.term.toLowerCase());
    }
}
