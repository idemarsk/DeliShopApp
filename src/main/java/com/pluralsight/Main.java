package com.pluralsight;
com.pluralsight.UserInterface;

public class Main {
    public static <Interface> void main(String[] args) {
        Interface ui = new UserInterface();
        ui.display();
    }
}
