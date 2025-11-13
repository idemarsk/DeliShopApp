package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private String breadType;
    private int size;
    private boolean toasted;
    private List<Topping> meats;
    private List<Topping> cheeses;
    private List<String> regularToppings;
    private List<String> sauces;

    public Sandwich(String breadType, int size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
    }

    public void addMeat(Topping meat) {
        meats.add(meat);
    }

    public void addCheese(Topping cheese) {
        cheeses.add(cheese);
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    @Override
    public double getPrice() {
        double basePrice = 0;

        // Base price by size
        switch (size) {
            case 4:
                basePrice = 5.50;
                break;
            case 8:
                basePrice = 7.00;
                break;
            case 12:
                basePrice = 8.50;
                break;
        }

        // Add meat prices
        for (Topping meat : meats) {
            double meatPrice = 0;
            switch (size) {
                case 4:
                    meatPrice = 1.00;
                    break;
                case 8:
                    meatPrice = 2.00;
                    break;
                case 12:
                    meatPrice = 3.00;
                    break;
            }
            if (meat.isExtra()) {
                meatPrice += meatPrice * 0.50; // Extra meat costs 50% more
            }
            basePrice += meatPrice;
        }

        // Add cheese prices
        for (Topping cheese : cheeses) {
            double cheesePrice = 0;
            switch (size) {
                case 4:
                    cheesePrice = 0.75;
                    break;
                case 8:
                    cheesePrice = 1.50;
                    break;
                case 12:
                    cheesePrice = 2.25;
                    break;
            }
            if (cheese.isExtra()) {
                cheesePrice += cheesePrice * 0.30; // Extra cheese costs 30% more
            }
            basePrice += cheesePrice;
        }

        // Regular toppings and sauces are free

        return basePrice;
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder();
        desc.append(size).append("\" ").append(capitalize(breadType)).append(" Sandwich");
        if (toasted) {
            desc.append(" (Toasted)");
        }
        desc.append("\n");

        if (!meats.isEmpty()) {
            desc.append("  Meats:\n");
            for (Topping meat : meats) {
                desc.append("    - ").append(capitalize(meat.getName()));
                if (meat.isExtra()) {
                    desc.append(" (Extra)");
                }
                desc.append("\n");
            }
        }

        if (!cheeses.isEmpty()) {
            desc.append("  Cheeses:\n");
            for (Topping cheese : cheeses) {
                desc.append("    - ").append(capitalize(cheese.getName()));
                if (cheese.isExtra()) {
                    desc.append(" (Extra)");
                }
                desc.append("\n");
            }
        }

        if (!regularToppings.isEmpty()) {
            desc.append("  Toppings:\n");
            for (String topping : regularToppings) {
                desc.append("    - ").append(capitalize(topping)).append("\n");
            }
        }

        if (!sauces.isEmpty()) {
            desc.append("  Sauces:\n");
            for (String sauce : sauces) {
                desc.append("    - ").append(capitalize(sauce)).append("\n");
            }
        }

        return desc.toString();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}