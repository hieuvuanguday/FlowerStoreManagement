/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import models.Flower;
import models.Order;
import models.OrderDetail;
import tools.Formatter;
import tools.Inputter;

/**
 *
 * @author Administrator
 */
public class OrderManager extends TreeSet<Order> {

    public OrderManager() {
    }

    /**
     * Adds a new order to the FlowerManager.
     *
     * @param fm The FlowerManager object.
     */
    public void add(FlowerManager fm) {
        if (!fm.isEmpty()) {
            boolean flag = true;
            String id = "";
            while (flag) {
                id = Inputter.getString("Enter order id [OXXX]: ", "[OXXX]", "O\\d{3}");
                if (find(id) == null) {
                    flag = false;
                } else {
                    System.out.println("Id must be unique! ");
                }
            }
            String date = Inputter.getDate("Enter date: ", "dd/mm/yyyy");
            String cusName = Inputter.getStringNonBlank("Enter customer name: ");
            Order o = new Order(id, cusName, date);
            System.out.println("Add order details:");
            int countId = 0;
            flag = true;
            while (flag) {
                String fId = "";
                boolean subFlag = true;
                while (subFlag) {
                    fId = Inputter.getString("Enter flower id (Just enter to stop add detail): ");
                    if (countId == 0 && fId.isEmpty()) {
                        System.out.println("Must have at least 1 order detail! ");
                    } else {
                        subFlag = false;
                    }
                }
                if (fId.isEmpty()) {
                    flag = false;
                }
                if (flag) {
                    Flower f = fm.find(fId);
                    if (f == null) {
                        System.out.println("Flower does not exist! ");
                        continue;
                    }
                    int quantity = Inputter.getPositiveInt("Enter quantity: ");
                    String oId = Integer.toString(countId);
                    o.addDetail(new OrderDetail(oId, f, quantity));
                    countId++;
                }
            }
            this.add(o);
        } else {
            System.out.println("Do not have any flower in store!");
        }
    }

    /**
     * Finds an order by its ID.
     *
     * @param id The ID of the order to find.
     * @return The found Order object, or null if not found.
     */
    public Order find(String id) {
        for (Order f : this) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Checks if a given flower is present in any of the orders.
     *
     * @param flower The flower to check.
     * @return true if the flower is found in an order, false otherwise.
     */
    public boolean isFlowerInOrder(Flower flower) {
        for (Order o : this) {
            Set<OrderDetail> ods = o.getOrderDetail();
            for (OrderDetail od : ods) {
                if (od.getFlower().getId().equals(flower.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Displays the orders within a specified date range. Prompts the user to
     * enter a start date and an end date. Only the orders that fall within the
     * specified date range will be displayed.
     */
    public void display() {
        String sDate = Inputter.getDate("Start date: ", "dd/mm/yyyy");
        String eDate = Inputter.getDate("End date: ", "dd/mm/yyyy");
        Set<Order> displaySet = new TreeSet<>(Comparator.comparing(Order::getId));
        for (Order o : this) {
            if (isInRangeDate(sDate, eDate, o)) {
                displaySet.add(o);
            }
        }
        System.out.println("LIST ORDER FROM " + sDate + " TO " + eDate);
        display(displaySet);
    }

    /**
     * Sorts the orders based on the specified criteria and displays the sorted
     * results. The sorting criteria include Order Id, Order Date, Flower Count,
     * and Order Total. The sort order can be specified as ascending (ASC) or
     * descending (DESC).
     */
    public void sort() {
        Set<Order> displaySet = new TreeSet<>();
        String sOrd = Inputter.getString("ORDER BY (Order Id, Order Date, Flower Count, Order Total): ");
        String tOrd = Inputter.getString("SORT ORDER (ASC, DESC): ");
        boolean asc = tOrd.equalsIgnoreCase("asc");
        if (sOrd.equalsIgnoreCase("order id")) {
            if (asc) {
                displaySet = new TreeSet<>(Comparator.comparing(Order::getId));
            } else {
                displaySet = new TreeSet<>(Comparator.comparing(Order::getId).reversed());
            }
        } else if (sOrd.equalsIgnoreCase("order date")) {
            if (asc) {
                displaySet = new TreeSet<>(new CmpDate());
            } else {
                displaySet = new TreeSet<>(new CmpDate().reversed());
            }
        } else if (sOrd.equalsIgnoreCase("flower count")) {
            if (asc) {
                displaySet = new TreeSet<>(new CmpCount());
            } else {
                displaySet = new TreeSet<>(new CmpCount().reversed());
            }
        } else if (sOrd.equalsIgnoreCase("order total")) {
            if (asc) {
                displaySet = new TreeSet<>(new CmpTotal().reversed());
            } else {
                displaySet = new TreeSet<>(new CmpTotal());
            }
        } else {
            System.out.println("Does not exist.");
        }
        for (Order o : this) {
            displaySet.add(o);
        }
        display(displaySet);
    }

    /**
     * Displays the information of orders in a formatted table.
     *
     * @param displaySet The set of orders to display.
     */
    private void display(Set<Order> displaySet) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| No.| Order Id | Order Date | Customer           | Flower Count | Order Total |");
        System.out.println("--------------------------------------------------------------------------------");
        int count = 1;
        for (Order o : displaySet) {
            String line = Formatter.toInfoLine(count, o.getId(), o.getDate(), o.getName(), o.getCount(), o.getTotal());
            System.out.println(line);
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    /**
     * Checks if the date of the given order is within the specified date range.
     *
     * @param sDate The start date of the range (in the format "dd/mm/yyyy").
     * @param eDate The end date of the range (in the format "dd/mm/yyyy").
     * @param o The order to check.
     * @return True if the order date is within the specified range, false
     * otherwise.
     */
    private boolean isInRangeDate(String sDate, String eDate, Order o) {
        String current = o.getDate();
        boolean isAfter = tools.Formatter.toDate(sDate, "dd/mm/yyyy").compareTo(tools.Formatter.toDate(current, "dd/mm/yyyy")) <= 0;
        boolean isBefore = tools.Formatter.toDate(eDate, "dd/mm/yyyy").compareTo(tools.Formatter.toDate(current, "dd/mm/yyyy")) >= 0;
        return isAfter && isBefore;
    }

    /**
     * Converts the orders in the collection to an ArrayList of strings. Each
     * string in the ArrayList represents the toString representation of an
     * order.
     *
     * @return ArrayList of strings representing the orders.
     */
    public ArrayList<String> toStringArrayList() {
        ArrayList<String> ret = new ArrayList<>();
        for (Order f : this) {
            ret.add(f.toString());
        }
        return ret;
    }
}

class CmpDate implements Comparator<Order> {

    /**
     * Compares two Order objects based on their dates and IDs. If the dates are
     * the same, compares the IDs.
     *
     * @param o1 the first Order object to compare
     * @param o2 the second Order object to compare
     * @return a negative integer if o1 is less than o2, zero if o1 is equal to
     * o2, a positive integer if o1 is greater than o2
     */
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getDate().compareTo(o2.getDate()) == 0) {
            return o1.getId().compareTo(o2.getId());
        } else {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

}

class CmpCount implements Comparator<Order> {

    /**
     * Compares two Order objects based on their flower counts and IDs. If the
     * flower counts are the same, compares the IDs.
     *
     * @param o1 the first Order object to compare
     * @param o2 the second Order object to compare
     * @return a negative integer if o1 is less than o2, zero if o1 is equal to
     * o2, a positive integer if o1 is greater than o2
     */
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getCount() == o2.getCount()) {
            return o1.getId().compareTo(o2.getId());
        } else {
            return Integer.compare(o1.getCount(), o2.getCount());
        }
    }

}

class CmpTotal implements Comparator<Order> {

    /**
     * Compares two Order objects based on their total amounts and IDs. If the
     * total amounts are the same, compares the IDs.
     *
     * @param o1 the first Order object to compare
     * @param o2 the second Order object to compare
     * @return a negative integer if o1 is less than o2, zero if o1 is equal to
     * o2, a positive integer if o1 is greater than o2
     */
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getTotal() == o1.getTotal()) {
            return o1.getId().compareTo(o2.getId());
        } else {
            return Double.compare(o1.getTotal(), o2.getTotal());
        }
    }

}
