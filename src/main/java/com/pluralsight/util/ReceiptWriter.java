package com.pluralsight.util;

import com.pluralsight.Order;
import com.pluralsight.OrderItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    public static void saveReceipt(Order order) {

        String timestamp = generateTimestamp();
        String filename = "src/main/resources/receipts/" + timestamp + ".txt";

        // Create receipts directory if it doesn't exist
        java.io.File directory = new java.io.File("src/main/resources/receipts");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // create a FileWriter and wrap in BufferedWriter (src/main/resources/receipts)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // Write header
            writer.write("═══════════════════════════════════════════\n");
            writer.write("          DELI-CIOUS SANDWICH SHOP\n");
            writer.write("             ORDER RECEIPT\n");
            writer.write("═══════════════════════════════════════════\n");

            // Format current date and time for display
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            writer.write("Date: " + now.format(dateFormatter) + "\n");
            writer.write("Time: " + now.format(timeFormatter) + "\n");
            writer.write("═══════════════════════════════════════════\n\n");

            // loop through all order items
            for (OrderItem item : order.getItems()) {
                //   - write the items to the receipt
                writer.write(item.getDescription() + "\n");
                writer.write(String.format("  Price: $%.2f\n", item.getPrice()));
                writer.write("\n");
            }

            writer.write("═══════════════════════════════════════════\n");

            // write total cost
            writer.write(String.format("TOTAL: $%.2f\n", order.getTotalPrice()));
            writer.write("═══════════════════════════════════════════\n\n");
            writer.write("      Thank you for your order!\n");
            writer.write("         Please come again!\n\n");
            writer.write("═══════════════════════════════════════════\n");

            System.out.println("\n📄 Receipt saved as: " + filename);

        } catch (IOException e) {

            System.out.println("Error saving receipt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String generateTimestamp() {
        // Create timestamp string using a formatter
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        // return that string
        return formatter.format(now);
    }
}
