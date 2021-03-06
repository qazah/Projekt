package com.Darek.Programik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String author;
    private String type;
    private int quantity;
    private  float price;

    public Book(int id,String title, String author, String type, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.quantity = quantity;
    }

    public Book(String title, String author, String type, int quantity, float price) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public int calculatingQuantity(int x)
    {
        this.quantity = quantity - x;

        return quantity;
    }
    public int addingQuantity(int x)
    {
        this.quantity = quantity + x;

        return quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
