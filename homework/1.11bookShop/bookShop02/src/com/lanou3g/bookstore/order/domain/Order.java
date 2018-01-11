package com.lanou3g.bookstore.order.domain;

public class Order {
    private String oid;
    private String ordertime;
    private double price;
    private Boolean state;
    private String uid;
    private String address;

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", price='" + price + '\'' +
                ", state=" + state +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order() {

    }

    public Order(String oid, String ordertime, double price, Boolean state, String uid, String address) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.price = price;
        this.state = state;
        this.uid = uid;
        this.address = address;
    }
}
