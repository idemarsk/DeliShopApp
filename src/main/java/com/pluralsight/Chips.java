package com.pluralsight;

public class Chips implements OrderItem {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String getDescription() {
        return capitalize(type) + " Chips";
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        // Handle multi-word chip types like "sour cream and onion"
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            result.append(words[i].substring(0, 1).toUpperCase())
                    .append(words[i].substring(1));
        }
        return result.toString();
    }
}