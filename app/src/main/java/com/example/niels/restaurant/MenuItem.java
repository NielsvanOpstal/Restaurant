package com.example.niels.restaurant;

import java.io.Serializable;

/*
Makes a MenuItem object which contains the name, description, imageUrl, price and category of the
menu item
*/

public class MenuItem implements Serializable {
    private String name;
    private String description;
    private String imageUrl;
    private float price;
    private String category;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
