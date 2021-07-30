package com.example.shoppingapp;

public class Product {
    private String cat;
    private String prodName;
    private double price;
    private int image;
    private int qty;

    public Product(String cat, String prodName, double price, int image, int qty) {
        this.cat = cat;
        this.prodName = prodName;
        this.price = price;
        this.image = image;
        this.qty = qty;
    }

    public String getCat() {
        return cat;
    }

    public String getProdName() {
        return prodName;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getQty() {
        return qty;
    }
}
