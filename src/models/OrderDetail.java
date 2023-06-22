/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class OrderDetail implements Comparable<OrderDetail>, Serializable{
    private final String id ;
    private Flower flower ;
    private int quantity ;

    public OrderDetail(String id, Flower flower, int quantity) {
        this.id = id;
        this.flower = flower;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Flower getFlower() {
        return flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFlowerCost(){
        return flower.getPrice() * quantity ;
    }
    
    public String toString(){
        String str = String.format("%s:%s:%d",
                id , flower.getId() , quantity);
        return str ;
    }

    public void showInfo() {
        String str = String.format("|%-10s|%-20s|%10d|", this.id, this.flower, this.quantity);
        System.out.println(str);
    }
    
    @Override
    public int compareTo(OrderDetail o) {
        return this.id.compareTo(o.getId());
    }
}    
