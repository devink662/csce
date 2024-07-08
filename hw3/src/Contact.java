
/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */
import java.util.Objects;
public class Contact {
    private String name;
    private String info;

    // Default constructor
    public Contact() {
        this.name = "";
        this.info = "";
    }

    // Parameterized constructor
    public Contact(String name, String info) {
        if (name != null && info != null) {
            this.name = name;
            this.info = info;
        } else {
            throw new IllegalArgumentException("Name and info cannot be null");
        }
    }

    // Accessor methods
    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    // Mutator methods
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    public void setInfo(String info) {
        if (info != null) {
            this.info = info;
        } else {
            throw new IllegalArgumentException("Info cannot be null");
        }
    }

    // toString method
    @Override
    public String toString() {
        return name + "\t" + info;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && info.equals(contact.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, info);
    }
}
