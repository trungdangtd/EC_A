package edu.huflit.ecapp1.models;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    String Id;

    public ShowAllModel(String id, String description,String img_url, String name,int price, String rating,  String type) {
        Id = id;
        this.description = description;
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.type = type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    String description;
    String name;
    String rating;
    int price;
    String img_url;
    String type;

    public ShowAllModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ShowAllModel(String description,String img_url, String name,int price, String rating, String type) {
        this.description = description;
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.type = type;
    }

}
