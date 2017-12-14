package com.lanou3g.homework.login;

public class Person {
    private String name;
    private String userName;
    private String passWord;

    public Person() {
    }

    public Person(String name, String userName, String passWord) {
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
