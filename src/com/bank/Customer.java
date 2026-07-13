package com.bank;

public class Customer {

    private int custId;
    private String custName;
    private String address;
    private String city;
    private int pincode;
    private String phoneNo;
    private double balance;

    // Default Constructor
    public Customer() {

    }

    // Parameterized Constructor
    public Customer(int custId, String custName, String address,
                    String city, int pincode, String phoneNo,
                    double balance) {

        this.custId = custId;
        this.custName = custName;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.phoneNo = phoneNo;
        this.balance = balance;
    }

    // Getters and Setters

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}