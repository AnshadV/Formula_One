package com.example.formulaone.models;

public class Product {
    public Long id;
    public String name;
    public String price;
    public String constructor;
    public String image;

    public Product() {

    }

    public Product(Long id, String name, String price, String constructor, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.constructor = constructor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }


}
