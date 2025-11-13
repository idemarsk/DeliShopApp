package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.currentOrder = null;
    }

    public void display() {
        boolean running = true;

        while (running) {
            int choice = showHomeScreen();

            switch (choice) {
                case 1:
                    handleNewOrder();
                    break;
                case 0:
                    System.out.println("\nThank you for visiting! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private int showHomeScreen() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║   DELI-CIOUS SANDWICH SHOP    ║");
        System.out.println("║      Welcome to Deli Store!     ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("\n1) New Order");
        System.out.println("0) Exit");
        System.out.print("\nEnter your choice: ");
        return getIntInput();
    }

    private int showOrderMenu() {
        System.out.println("\n🧺 ORDER MENU");
        System.out.println("═══════════════════════════════");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("\nEnter your choice: ");
        return getIntInput();
    }

    private void handleNewOrder() {
        currentOrder = new Order();
        boolean orderInProgress = true;

        while (orderInProgress) {
            int choice = showOrderMenu();

            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
                    orderInProgress = false;
                    break;
                case 0:
                    System.out.println("\n❌ Order cancelled.");
                    currentOrder = null;
                    orderInProgress = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addSandwich() {
        System.out.println("\n🥖 BUILD YOUR SANDWICH");
        System.out.println("═══════════════════════════════");

        String breadType = promptForBreadType();
        int size = promptForSize();
        List<Topping> meats = promptForMeats();
        List<Topping> cheeses = promptForCheeses();
        List<String> regularToppings = promptForRegularToppings();
        List<String> sauces = promptForSauces();
        boolean toasted = promptForToasted();

        Sandwich sandwich = new Sandwich(breadType, size, toasted);

        for (Topping meat : meats) {
            sandwich.addMeat(meat);
        }

        for (Topping cheese : cheeses) {
            sandwich.addCheese(cheese);
        }

        for (String topping : regularToppings) {
            sandwich.addRegularTopping(topping);
        }

        for (String sauce : sauces) {
            sandwich.addSauce(sauce);
        }

        currentOrder.addItem(sandwich);
        System.out.println("\n✓ Sandwich added to order!");
    }

    private String promptForBreadType() {
        System.out.println("\nAvailable Bread Types:");
        System.out.println("  - white");
        System.out.println("  - wheat");
        System.out.println("  - rye");
        System.out.println("  - wrap");
        System.out.print("Enter bread type: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private int promptForSize() {
        System.out.print("\nWhat size? (4, 8, or 12 inches): ");
        int size = getIntInput();
        while (size != 4 && size != 8 && size != 12) {
            System.out.print("Please enter 4, 8, or 12: ");
            size = getIntInput();
        }
        return size;
    }

    private boolean promptForToasted() {
        System.out.print("\nWould you like it toasted? (yes or no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    private List<Topping> promptForMeats() {
        List<Topping> meats = new ArrayList<>();
        System.out.println("\nAvailable Meats:");
        System.out.println("  - steak");
        System.out.println("  - ham");
        System.out.println("  - salami");
        System.out.println("  - roast beef");
        System.out.println("  - chicken");
        System.out.println("  - bacon");

        while (true) {
            System.out.print("\nEnter meat type (or 'done' to finish): ");
            String meat = scanner.nextLine().trim().toLowerCase();

            if (meat.equals("done")) {
                break;
            }

            if (!meat.isEmpty()) {
                System.out.print("Do you want Extra meat? (yes or no): ");
                String extraResponse = scanner.nextLine().trim().toLowerCase();
                boolean extra = extraResponse.equals("yes") || extraResponse.equals("y");

                meats.add(new Topping(meat, extra));
                System.out.println("✓ " + meat + (extra ? " (Extra)" : "") + " added!");
            }
        }

        return meats;
    }

    private List<Topping> promptForCheeses() {
        List<Topping> cheeses = new ArrayList<>();
        System.out.println("\nAvailable Cheeses:");
        System.out.println("  - american");
        System.out.println("  - provolone");
        System.out.println("  - cheddar");
        System.out.println("  - swiss");

        while (true) {
            System.out.print("\nEnter cheese type (or 'done' to finish): ");
            String cheese = scanner.nextLine().trim().toLowerCase();

            if (cheese.equals("done")) {
                break;
            }

            if (!cheese.isEmpty()) {
                System.out.print("Do you want Extra cheese? (yes or no): ");
                String extraResponse = scanner.nextLine().trim().toLowerCase();
                boolean extra = extraResponse.equals("yes") || extraResponse.equals("y");

                cheeses.add(new Topping(cheese, extra));
                System.out.println("✓ " + cheese + (extra ? " (Extra)" : "") + " added!");
            }
        }

        return cheeses;
    }

    private List<String> promptForRegularToppings() {
        List<String> toppings = new ArrayList<>();
        System.out.println("\nAvailable Regular Toppings (FREE):");
        System.out.println("  - lettuce");
        System.out.println("  - peppers");
        System.out.println("  - onions");
        System.out.println("  - tomatoes");
        System.out.println("  - jalapenos");
        System.out.println("  - cucumbers");
        System.out.println("  - pickles");
        System.out.println("  - guacamole");
        System.out.println("  - mushrooms");

        while (true) {
            System.out.print("\nEnter topping (or 'done' to finish): ");
            String topping = scanner.nextLine().trim().toLowerCase();

            if (topping.equals("done")) {
                break;
            }

            if (!topping.isEmpty()) {
                toppings.add(topping);
                System.out.println("✓ " + topping + " added!");
            }
        }

        return toppings;
    }

    private List<String> promptForSauces() {
        List<String> sauces = new ArrayList<>();
        System.out.println("\nAvailable Sauces (FREE):");
        System.out.println("  - mayo");
        System.out.println("  - mustard");
        System.out.println("  - ketchup");
        System.out.println("  - ranch");
        System.out.println("  - thousand islands");
        System.out.println("  - vinaigrette");

        while (true) {
            System.out.print("\nEnter sauce (or 'done' to finish): ");
            String sauce = scanner.nextLine().trim().toLowerCase();

            if (sauce.equals("done")) {
                break;
            }

            if (!sauce.isEmpty()) {
                sauces.add(sauce);
                System.out.println("✓ " + sauce + " added!");
            }
        }

        return sauces;
    }

    private void addDrink() {
        System.out.println("\n🥤 ADD DRINK");
        System.out.println("═══════════════════════════════");

        String size = promptForDrinkSize();
        String flavor = promptForDrinkFlavor();

        Drink drink = new Drink(size, flavor);
        currentOrder.addItem(drink);
        System.out.println("\n✓ Drink added to order!");
    }

    private String promptForDrinkSize() {
        System.out.println("\nAvailable Sizes:");
        System.out.println("  - small");
        System.out.println("  - medium");
        System.out.println("  - large");
        System.out.print("Enter size: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private String promptForDrinkFlavor() {
        System.out.println("\nAvailable Flavors:");
        System.out.println("  - coke");
        System.out.println("  - sprite");
        System.out.println("  - fanta");
        System.out.println("  - lemonade");
        System.out.println("  - iced tea");
        System.out.println("  - water");
        System.out.print("Enter flavor: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private void addChips() {
        System.out.println("\n🍟 ADD CHIPS");
        System.out.println("═══════════════════════════════");

        String type = promptForChipType();

        Chips chips = new Chips(type);
        currentOrder.addItem(chips);
        System.out.println("\n✓ Chips added to order!");
    }

    private String promptForChipType() {
        System.out.println("\nAvailable Chip Types:");
        System.out.println("  - regular");
        System.out.println("  - bbq");
        System.out.println("  - sour cream and onion");
        System.out.println("  - salt and vinegar");
        System.out.print("Enter chip type: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private void checkout() {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            System.out.println("\n❌ No items in order!");
            return;
        }

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║       ORDER SUMMARY           ║");
        System.out.println("╚════════════════════════════════╝");
        currentOrder.displayReceipt();

        System.out.println("\n1) Confirm");
        System.out.println("2) Cancel");
        System.out.print("\nEnter your choice: ");

        int choice = getIntInput();

        if (choice == 1) {
            if (currentOrder.saveReceiptToFile()) {
                System.out.println("\n✓ Receipt saved successfully!");
                System.out.println("✓ Thank you for your order!");
            } else {
                System.out.println("\n✗ Failed to save receipt.");
            }
            currentOrder = null;
        } else {
            System.out.println("\n❌ Order discarded.");
            currentOrder = null;
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}