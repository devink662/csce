/*
 * © 2024 Devin Kemp. All rights reserved.
 * University of South Carolina, Summer 2024 Semester
 * CSCE 146 Algorithmic Design 2
 *
 * This work is the intellectual property of Devin Kemp. Unauthorized use, reproduction, or distribution of this material without explicit permission from the author is prohibited. This document is intended for educational purposes only and may not be used for commercial purposes or published without prior consent.
 */

public class RequestedItem implements Comparable<RequestedItem> {
    private String requestor;
    private String itemName;
    private double price;

    public RequestedItem(String requestor, String itemName, double price) {
        if (requestor == null || itemName == null || price <= 0) {
            throw new IllegalArgumentException("Invalid values for RequestedItem properties");
        }
        this.requestor = requestor;
        this.itemName = itemName;
        this.price = price;
    }

    // Accessor methods
    public String getRequestor() {
        return requestor;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    // Mutator methods
    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "Item: " + itemName + " Cost: $" + String.format("%.2f", price) + " Requested by: " + requestor;
    }

    // compareTo method
    @Override
    public int compareTo(RequestedItem other) {
        int priceComparison = Double.compare(this.price, other.price);
        if (priceComparison != 0) {
            return priceComparison;
        }
        return this.itemName.compareTo(other.itemName);
    }
}
