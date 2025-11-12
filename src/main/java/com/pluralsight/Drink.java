package com.pluralsight;

public class Drink implements OrderItem {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        switch (size) {
            case "Small":
                return 2.00;
            case "Medium":
                return 2.50;
            case "Large":
                return 3.00;
            default:
                return 2.50;
        }
    }

    @Override
    public String getDescription() {
        return size + " " + flavor + " Drink";
    }
}
