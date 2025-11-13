package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private LocalDateTime orderTime;
    private static int orderCounter = 1000;
    private int orderNumber;

    public Order() {
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.orderNumber = ++orderCounter;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayReceipt() {
        System.out.println();
        for (OrderItem item : items) {
            System.out.println(item.getDescription());
            System.out.printf("  Price: $%.2f\n", item.getPrice());
            System.out.println();
        }
        System.out.println("═══════════════════════════════");
        System.out.printf("TOTAL: $%.2f\n", getTotalPrice());
        System.out.println("═══════════════════════════════");
    }

    public boolean saveReceiptToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = orderTime.format(formatter);
        String filename = "receipts/receipt_" + orderNumber + "_" + timestamp + ".txt";

        // Create receipts directory if it doesn't exist
        java.io.File directory = new java.io.File("receipts");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("═══════════════════════════════════════════\n");
            writer.write("          DELI-CIOUS SANDWICH SHOP\n");
            writer.write("             ORDER RECEIPT\n");
            writer.write("═══════════════════════════════════════════\n");
            writer.write("Order #: " + orderNumber + "\n");
            writer.write("Date: " + orderTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\n");
            writer.write("Time: " + orderTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
            writer.write("═══════════════════════════════════════════\n\n");

            for (OrderItem item : items) {
                writer.write(item.getDescription() + "\n");
                writer.write(String.format("  Price: $%.2f\n", item.getPrice()));
                writer.write("\n");
            }

            writer.write("═══════════════════════════════════════════\n");
            writer.write(String.format("TOTAL: $%.2f\n", getTotalPrice()));
            writer.write("═══════════════════════════════════════════\n\n");
            writer.write("      Thank you for your order!\n");
            writer.write("         Please come again!\n\n");
            writer.write("═══════════════════════════════════════════\n");

            System.out.println("\n📄 Receipt saved as: " + filename);
            return true;

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
            return false;
        }
    }
}