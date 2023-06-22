/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;
import models.Flower;
import models.Order;
import models.OrderDetail;
import tools.Loader;

/**
 *
 * @author Administrator
 */
public class FlowerStoreManager {

    private FlowerManager fm;
    private OrderManager om;
    private final String FLOWERPATH = "\\data\\flowers.dat";
    private final String ORDERPATH = "\\data\\orders.dat";

    public FlowerStoreManager() {
        fm = new FlowerManager();
        om = new OrderManager();
        loadData();
    }

    public void addFlower() {
        fm.add();
    }

    public void findFlower() {
        fm.find();
    }

    public void displayFlower() {
        fm.display();
    }

    public void updateFlower() {
        fm.update();
    }

    public void deleteFlower() {
        fm.delete(om);
    }

    public void addOrder() {
        om.add(fm);
    }

    public void displayOrder() {
        om.display();
    }

    public void sortOrder() {
        om.sort();
    }

    /**
     * Loads data from files and populates the FlowerManager and OrderManager
     * objects. Reads flower data from the FLOWERPATH file and order data from
     * the ORDERPATH file. The loaded data is used to create Flower and Order
     * objects and add them to the respective managers.
     */
    public void loadData() {
        fm.clear();
        om.clear();
        ArrayList<String> dta = new ArrayList<>();
        dta.addAll(Loader.readFromFile(FLOWERPATH));
        dta.addAll(Loader.readFromFile(ORDERPATH));
        for (String line : dta) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            String[] lineSplit = line.split(",");
            if (lineSplit[0].matches("F\\d{3}")) {
                fm.add(new Flower(lineSplit[0],
                        lineSplit[1],
                        lineSplit[2],
                        Double.parseDouble(lineSplit[3]),
                        lineSplit[4]));
            } else if (lineSplit[0].matches("O\\d{3}")) {
                Order o = new Order(lineSplit[0], lineSplit[2], lineSplit[1]);
                int count = 0;
                for (String detail : lineSplit) {
                    if (count > 2) {
                        String od[] = detail.split(":");
                        Flower f = fm.find(od[1]);
                        int quantity = Integer.parseInt(od[2]);
                        o.addDetail(new OrderDetail(od[0], f, quantity));
                    }
                    count++;
                }
                om.add(o);
            }
        }
    }

    /**
     * Saves the data from FlowerManager and OrderManager objects to files.
     * Converts the Flower and Order objects into string representations and
     * writes them to the FLOWERPATH and ORDERPATH files, respectively.
     */
    public void saveData() {
        ArrayList<String> ordDta = om.toStringArrayList();
        ArrayList<String> floDta = fm.toStringArrayList();
        Loader.writeToFile(ORDERPATH, ordDta);
        Loader.writeToFile(FLOWERPATH, floDta);
    }
}
