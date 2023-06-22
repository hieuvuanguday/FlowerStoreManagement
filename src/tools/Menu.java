/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.ArrayList;

/**
 *
 * @author Hoang Ha Oanh
 */
public class Menu {

    private final String title;
    private final ArrayList<String> options;
    private int size;

    public Menu(String title) {
        this.title = title;
        this.size = 0;
        this.options = new ArrayList<>();
    }

    /**
     * Adds an option to the list of options.
     *
     * @param option The option to be added.
     */
    public void addOption(String option) {
        options.add(option);
        size++;
    }

    /**
     * Prints the menu title and options.
     */
    public void print() {
        System.out.println(title);
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("%d. %s", i + 1, options.get(i)));
        }
    }

    /**
     * Gets the user's choice from the menu.
     *
     * @return The user's choice.
     */
    public int getChoice() {
        while (true) {
            try {
                int choice = Inputter.getInt("Enter your choice: ");
                if (choice < 1 || choice > size) {
                    throw new Exception();
                }
                return choice;
            } catch (Exception e) {
                System.out.println("Choose 1 - " + size + ".");
            }
        }
    }
}
