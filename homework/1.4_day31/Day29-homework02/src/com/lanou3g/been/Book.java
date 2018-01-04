package com.lanou3g.been;

public class Book {
    private int bid;
    private String bname;
    private String bauthor;
    private double bprice;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public double getBprice() {
        return bprice;
    }

    public void setBprice(double bprice) {
        this.bprice = bprice;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                ", bprice=" + bprice +
                '}';
    }

    public Book(int bid, String bname, String bauthor, double bprice) {
        this.bid = bid;
        this.bname = bname;
        this.bauthor = bauthor;
        this.bprice = bprice;
    }

    public Book() {

    }
}
