package com.pluralsight;

import com.pluralsight.util.ReceiptWriter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
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
        try {
            ReceiptWriter.saveReceipt(this);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving receipt: " + e.getMessage());
            return false;
        }
    }
}