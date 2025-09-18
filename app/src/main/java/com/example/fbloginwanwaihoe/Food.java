package com.example.fbloginwanwaihoe;

public class Food {
    double price;
    int quantity;
    String name;

    Food(){
        price = 0;
        quantity = 0;
        name = "";
    }

    public double getPrice(){return price;}
    public int getQuantity(){return quantity;}
    public String getName(){return name;}

    public void setPrice(double price){this.price = price; }
    public void setQuantity(int quantity){this.quantity = quantity; }
    public void setName(String name){this.name = name; }
}
