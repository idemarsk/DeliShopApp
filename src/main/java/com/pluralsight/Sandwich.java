package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private String bread;
    private String size;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(String bread, String size) {
        this.bread = bread;
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toasted = false;
    }

    public void addTopping(String name, String type, boolean extra) {
        toppings.add(new Topping(name, type, extra));
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public double getPrice() {
        double basePrice = 0;

        // Base price by size
        switch (size) {
            case "4\"":
                basePrice = 5.50;
                break;
            case "8\"":
                basePrice = 7.00;
                break;
            case "12\"":
                basePrice = 8.50;
                break;
        }

        // Add topping prices
        for (Topping topping : toppings) {
            basePrice += topping.getPrice(size);
        }

        return basePrice;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder();
        desc.append(size).append(" ").append(bread).append(" Sandwich");
        if (toasted) {
            desc.append(" (Toasted)");
        }
        desc.append("\n");

        if (!toppings.isEmpty()) {
            desc.append("  Toppings:\n");
            for (Topping topping : toppings) {
                desc.append("    - ").append(topping.getName());
                if (topping.isExtra()) {
                    desc.append(" (Extra)");
                }
                desc.append("\n");
            }
        }

        return desc.toString();
    }

    private class Topping {
        private String name;
        private String type;
        private boolean extra;

        public Topping(String name, String type, boolean extra) {
            this.name = name;
            this.type = type;
            this.extra = extra;
        }

        public String getName() {
            return name;
        }

        public boolean isExtra() {
            return extra;
        }

        public double getPrice(String sandwichSize) {
            double price = 0;

            // Meat prices
            if (type.equals("meat")) {
                switch (sandwichSize) {
                    case "4\"":
                        price = 1.00;
                        break;
                    case "8\"":
                        price = 2.00;
                        break;
                    case "12\"":
                        price = 3.00;
                        break;
                }
                if (extra) {
                    price += price * 0.5; // Extra meat costs 50% more
                }
            }

            // Cheese prices
            if (type.equals("cheese")) {
                switch (sandwichSize) {
                    case "4\"":
                        price = 0.75;
                        break;
                    case "8\"":
                        price = 1.50;
                        break;
                    case "12\"":
                        price = 2.25;
                        break;
                }
                if (extra) {
                    price += price * 0.3; // Extra cheese costs 30% more
                }
            }

            // Regular toppings and sauces are free

            return price;
        }
    }
}