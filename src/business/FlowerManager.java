/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;
import java.util.TreeSet;
import models.Flower;
import tools.Inputter;

/**
 *
 * @author Administrator
 */
public class FlowerManager extends TreeSet<Flower> {

    public FlowerManager() {
    }
    /**
     * Adds a new flower to the FlowerManager. Prompts the user to enter details
     * such as flower ID, description, import date, unit price, and category.
     * Validates the input and ensures the flower ID is unique.
     */
    public void add() {
        String id = "";
        while (true) {
            id = Inputter.getString("Enter flower id [FXXX]: ", "[FXXX]", "F\\d{3}");
            if (find(id) == null) {
                break;
            } else {
                System.out.println("Id must be unique! ");
            }
        }
        String description = Inputter.getString("Enter description: ", 3, 50);
        String importDate = Inputter.getDate("Enter import date: ", "dd/mm/yyyy");
        double price = Inputter.getPositiveReal("Enter unit price: ");
        String category = Inputter.getStringNonBlank("Enter category: ");
        this.add(new Flower(id, description, importDate, price, category));
    }

    /**
     * Finds a flower in the FlowerManager by the specified ID.
     *
     * @param id The ID of the flower to find.
     * @return The found flower object, or null if no flower with the specified
     * ID is found.
     */
    public Flower find(String id) {
        for (Flower f : this) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Prompts the user to choose whether to find a flower by name or ID and
     * calls the respective find method. If the user enters an invalid choice,
     * they are prompted again until a valid choice is entered.
     */
    public void find() {
        String choice = tools.Inputter.getString("Find By [Name or ID]: ", "Enter again [Name or ID]: ", "^(Name|ID)$");
        if (choice.equalsIgnoreCase("Name")) {
            findByName();
        } else {
            findById();
        }
    }

    /**
     * Prompts the user to enter a flower ID and finds the flower with the
     * specified ID. If the flower is found, its information is displayed. If
     * the flower is not found, a message indicating that the flower does not
     * exist is printed.
     */
    public void findById() {
        String id = Inputter.getString("Enter flower id [FXXX]: ", "[FXXX]", "F\\d{3}");
        Flower f = find(id);
        if (f == null) {
            System.out.println("The flower does not exist.");
        } else {
            f.showInfo();
        }
    }

    /**
     * Prompts the user to enter a flower name and finds all flowers with the
     * specified name. If flowers with the specified name are found, their
     * information is displayed. If no flowers with the specified name are
     * found, a message indicating that the flowers do not exist is printed.
     */
    public void findByName() {
        String name = Inputter.getString("Enter flower's name: ", 3, 50);
        boolean flag = true;
        for (Flower flower : this) {
            if (flower.getDescription().contains(name)) {
                flower.showInfo();
                flag = false;
            }
            if (flag) {
                System.out.println("The flower does not exist! ");
            }
        }
    }

    /**
     * Displays the information of all flowers in the FlowerManager. The
     * information of each flower is displayed using the showInfo() method.
     */
    public void display() {
        for (Flower flower : this) {
            flower.showInfo();
        }
    }

    /**
     * Updates the information of a flower in the FlowerManager. Prompts the
     * user to enter the flower ID and finds the corresponding flower. If the
     * flower is found, prompts the user to enter optional updated information
     * such as description, import date, price, and category. Validates the
     * input and updates the flower information accordingly. Prints a success
     * message if the update is successful.
     */
    public void update() {
        String id = Inputter.getString("Enter flower id [FXXX]: ", "[FXXX]", "F\\d{3}");
        Flower f = find(id);
        if (f == null) {
            System.out.println("The flower does not exist.");
        } else {
            String description = Inputter.getString("Enter description (You can ignore by just enter): ");
            String importDate = Inputter.getString("Enter import date (You can ignore by just enter): ");
            String price = Inputter.getString("Enter price (You can ignore by just enter): ");
            String category = Inputter.getString("Enter category (You can ignore by just enter): ");
            try {
                if (!description.isEmpty() && (description.length() < 3 || description.length() > 50)) {
                    throw new Exception("Description length must be in [3,50]");
                }
                if (!importDate.isEmpty() && importDate.matches("dd/mm/yyyy")) {
                    throw new Exception("Invalid date format.");
                }
                if (!price.isEmpty() && Double.parseDouble(price) < 0) {
                    throw new Exception("Price must higher than 0.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Update failed.");
                return;
            }
            if (!description.isEmpty()) {
                f.setDescription(description);
            }
            if (!importDate.isEmpty()) {
                f.setImportDate(importDate);
            }
            if (!price.isEmpty()) {
                f.setPrice(Double.parseDouble(price));
            }
            if (!category.isEmpty()) {
                f.setCategory(category);
            }
            System.out.println("Update successfully.");
        }
    }

    /**
     * Deletes a flower from the FlowerManager. Prompts the user to enter the
     * flower ID and finds the corresponding flower. Checks if the flower is
     * associated with any orders in the given OrderManager. If the flower is
     * found and not present in any orders, deletes the flower from the
     * FlowerManager. Prints a success message if the deletion is successful.
     * Prints an error message if the flower is found in an order and cannot be
     * deleted.
     *
     * @param om The OrderManager to check if the flower is present in any
     * orders.
     */
    public void delete(OrderManager om) {
        String id = Inputter.getString("Enter flower id [FXXX]: ", "[FXXX]", "F\\d{3}");
        Flower f = find(id);
        if (om.isFlowerInOrder(f)) {
            System.out.println("Delete failed. The flower is in an order.");
        } else {
            System.out.println("Delete Successfully!");
        }
    }

    /**
     * Converts the list of flowers to an ArrayList of strings.
     *
     * @return The ArrayList of strings representing the flowers.
     */
    public ArrayList<String> toStringArrayList() {
        ArrayList<String> ret = new ArrayList<>();
        for (Flower f : this) {
            ret.add(f.toString());
        }
        return ret;
    }
}
