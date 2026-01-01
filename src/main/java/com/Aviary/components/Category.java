package com.Aviary.components;

public class Category {
    private int id;
    private String name;
    private String pathImage;

    public Category() {
    }

    public Category(int id, String name, String pathImage) {
        this.id = id;
        this.name = name;
        this.pathImage = pathImage;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int categoryId) {
        this.id = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

}
