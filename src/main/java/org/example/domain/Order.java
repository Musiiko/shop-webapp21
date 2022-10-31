package org.example.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String number;
    private LocalDate date;

    private List<ProductInOrder> productsInOrder;

    public Order(LocalDate date, String number) {
        this.date = date;
        this.number = number;
        productsInOrder = new ArrayList<>();
    }

    public Order(int id, String number, LocalDate date) {
        this(date, number);
        this.id = id;
        productsInOrder = new ArrayList<>();
    }

    public List<ProductInOrder> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(List<ProductInOrder> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }

    public void addProduct(ProductInOrder product) {
        productsInOrder.add(product);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", products=" + productsInOrder +
                '}';
    }

}