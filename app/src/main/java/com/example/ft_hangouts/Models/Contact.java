package com.example.ft_hangouts.Models;

public class Contact {
    private int id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String nickName;
    private String picture;

    public Contact(String phoneNumber, String firstName, String picture) {
        this(phoneNumber, firstName, picture, "", "");
    }

    public Contact(String phoneNumber, String firstName, String picture, String lastName) {
        this(phoneNumber, firstName, picture, lastName, "");
    }

    public Contact(String phoneNumber, String firstName, String picture, String lastName, String nickName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.picture = picture;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public Contact(int id, String phoneNumber, String firstName, String picture, String lastName, String nickName) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.picture = picture;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
