/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Administrator
 */
public class Order implements Comparable<Order>, Serializable{
    private final String id ;
    private String name ;
    private String date ;
    private Set<OrderDetail> orderDetails = new TreeSet<>() ;
    private int count ;
    private double total ;
    
    public Order(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        count = 0 ;
        total = 0 ;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Set<OrderDetail> getOrderDetail(){
        return orderDetails ;
    }
    
    public void addDetail(OrderDetail od){
        orderDetails.add(od) ;
        count += od.getQuantity() ;
        total += od.getFlowerCost() ;
    }
    
    public int getCount(){
        return count ;
    }
    
    public double getTotal(){
        return total ;
    }
    
    private String toStringDetail(){
        String ret = "" ;
        for(OrderDetail od : orderDetails){
            ret += String.format("%s,",od.toString()) ;
        }
        return ret.substring(0 , ret.length()-1) ;
    }
    
    @Override
    public String toString(){
        String str = String.format("%s,%s,%s,%s",
                id , date , name , toStringDetail()) ;
        return str ;
    }

    public void showInfo() {
        String str = String.format("|%-10s|%-20s|%-10s|%-10d|%-20f|", this.id, this.name, this.date, this.count, this.total);
        System.out.println(str);
    }
    
    @Override
    public int compareTo(Order o) {
        return this.id.compareTo(o.getId());
    }
}
