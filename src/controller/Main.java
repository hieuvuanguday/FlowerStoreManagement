/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import business.FlowerStoreManager;
import tools.Inputter;
import tools.Menu;

/**
 *
 * @author
 */
public class Main {

    public static void main(String[] args) {
        FlowerStoreManager fsm = new FlowerStoreManager();
        Menu mainMenu = new Menu("FLOWER STORE MANAGEMENT");
        mainMenu.addOption("Manage Flower");
        mainMenu.addOption("Manage Order");
        mainMenu.addOption("Quit");
        Menu flowerMenu = new Menu("Manage Flower");
        flowerMenu.addOption("Add Flower");
        flowerMenu.addOption("Find Flower");
        flowerMenu.addOption("Display Flower List");
        flowerMenu.addOption("Update Flower");
        flowerMenu.addOption("Delete Flower");
        flowerMenu.addOption("Back To Main Menu");
        Menu orderMenu = new Menu("Manage Order");
        orderMenu.addOption("Add Order");
        orderMenu.addOption("Display Order");
        orderMenu.addOption("Sort Order");
        orderMenu.addOption("Save Data");
        orderMenu.addOption("Load Data");
        orderMenu.addOption("Back To Main Menu");
        boolean saved = true;
        boolean flag = true;
        while (flag) {
            mainMenu.print();
            int n = mainMenu.getChoice();
            switch (n) {
                case 1: {
                    flowerMenu.print();
                    switch (flowerMenu.getChoice()) {
                        case 1: {
                            while (true) {
                                fsm.addFlower();
                                if (!Inputter.getYesOrNo("Countinously add new flower?")) {
                                    break;
                                }
                            }
                            saved = false;
                            break;
                        }
                        case 2: {
                            fsm.findFlower();
                            break;
                        }
                        case 3: {
                            fsm.displayFlower();
                            break;
                        }
                        case 4: {
                            fsm.updateFlower();
                            break;
                        }
                        case 5: {
                            fsm.deleteFlower();
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    orderMenu.print();
                    switch (orderMenu.getChoice()) {
                        case 1: {
                            while (true) {
                                fsm.addOrder();
                                if (!Inputter.getYesOrNo("Countinously add new order?")) {
                                    break;
                                }
                            }
                            saved = false;
                            break;
                        }
                        case 2: {
                            fsm.displayOrder();
                            break;
                        }
                        case 3: {
                            fsm.sortOrder();
                            break;
                        }
                        case 4: {
                            fsm.saveData();
                            saved = true;
                            System.out.println("Load Data Successfully!");
                            break;
                        }
                        case 5: {
                            fsm.loadData();
                            System.out.println("Save Data Successfully!");
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    if (Inputter.getYesOrNo("Quit program?")) {
                        if (!saved && Inputter.getYesOrNo("Do you want to save before quit?")) {
                            fsm.saveData();
                        }
                        return;
                    }
                }
            }
        }
    }
}
