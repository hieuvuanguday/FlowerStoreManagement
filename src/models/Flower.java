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
public class Flower implements Comparable <Flower>, Serializable {
    private final String id ;
    private String description ;
    private String importDate ;
    private double price ;
    private String category ;
    
    public Flower(String id, String description, String importDate, double price, String category) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void display(){
        System.out.println(this.toString());
    }
            
    @Override
    public String toString(){
        String msg = String.format("%s,%s,%s,%f,%s",
                id , description , importDate , price , category) ;
        return msg ;
    }
    
    public void showInfo() {
        String str = String.format("|%-10s|%-20s|%-10s|%10.3f|%20s|", this.id, this.description, this.importDate, this.price, this.category);
        System.out.println(str);
    }

    @Override
    public int compareTo(Flower o) {
        return this.id.compareTo(o.getId()) ;
    }
}
