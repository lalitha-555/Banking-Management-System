package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CustomerService {

    Connection conn = DBConnection.getConnection();
    Scanner sc = new Scanner(System.in);


 // Add a new customer
    public void addCustomer() {

        try {

            String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter Pincode: ");
            int pincode = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();


            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, city);
            ps.setInt(5, pincode);
            ps.setString(6, phone);
            ps.setDouble(7, balance);


            int rows = ps.executeUpdate();


            if(rows > 0) {
                System.out.println("Customer Added Successfully!");
            }
            else {
                System.out.println("Failed To Add Customer!");
            }


            ps.close();


        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }



    // View Customers
    public void viewCustomers() {

        try {

            String sql = "SELECT * FROM Customer";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            System.out.println("\n========== CUSTOMER DETAILS ==========");


            while(rs.next()) {

                System.out.println("-----------------------------");

                System.out.println("Customer ID : " + rs.getInt("Cust_id"));
                System.out.println("Name        : " + rs.getString("Cust_name"));
                System.out.println("Address     : " + rs.getString("Address"));
                System.out.println("City        : " + rs.getString("City"));
                System.out.println("Pincode     : " + rs.getInt("Pincode"));
                System.out.println("Phone No    : " + rs.getString("Phone_no"));
                System.out.println("Balance     : ₹" + rs.getDouble("Balance"));

            }


            rs.close();
            ps.close();


        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }



    // Search Customer
    public void searchCustomer() {

        try {


            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();


            String sql = "SELECT * FROM Customer WHERE Cust_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1,id);


            ResultSet rs = ps.executeQuery();



            if(rs.next()) {


                System.out.println("\n===== CUSTOMER FOUND =====");


                System.out.println("Customer ID : " + rs.getInt("Cust_id"));
                System.out.println("Name        : " + rs.getString("Cust_name"));
                System.out.println("Address     : " + rs.getString("Address"));
                System.out.println("City        : " + rs.getString("City"));
                System.out.println("Pincode     : " + rs.getInt("Pincode"));
                System.out.println("Phone No    : " + rs.getString("Phone_no"));
                System.out.println("Balance     : ₹" + rs.getDouble("Balance"));


            }
            else {

                System.out.println("Customer Not Found!");

            }


            rs.close();
            ps.close();


        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }



    // Update Customer
    public void updateCustomer() {


        try {


            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();
            sc.nextLine();


            System.out.print("Enter New Phone Number: ");
            String phone = sc.nextLine();



            String sql = "UPDATE Customer SET Phone_no=? WHERE Cust_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(1,phone);
            ps.setInt(2,id);



            int rows = ps.executeUpdate();



            if(rows > 0) {

                System.out.println("Customer Updated Successfully!");

            }
            else {

                System.out.println("Customer Not Found!");

            }


            ps.close();


        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }



    // Delete Customer
    public void deleteCustomer() {


        try {


            System.out.print("Enter Customer ID to Delete: ");
            int id = sc.nextInt();



            String sql = "DELETE FROM Customer WHERE Cust_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1,id);



            int rows = ps.executeUpdate();



            if(rows > 0) {

                System.out.println("Customer Deleted Successfully!");

            }
            else {

                System.out.println("Customer Not Found!");

            }



            ps.close();



        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }
   
 // Deposit money into customer account
    public void depositMoney() {

        try {

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();


            System.out.print("Enter Amount to Deposit: ");
            double amount = sc.nextDouble();



            String sql = "UPDATE Customer SET Balance = Balance + ? WHERE Cust_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setDouble(1, amount);
            ps.setInt(2, id);



            int rows = ps.executeUpdate();


            if(rows > 0) {

                addTransaction(id, "Deposit", amount);

                System.out.println("Money Deposited Successfully!");

            }
            else {

                System.out.println("Customer Not Found!");

            }


            ps.close();


        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }
 // Withdraw Money

    public void withdrawMoney() {

        try {

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();


            System.out.print("Enter Amount to Withdraw: ");
            double amount = sc.nextDouble();



            // Check current balance

            String checkSql = "SELECT Balance FROM Customer WHERE Cust_id=?";


            PreparedStatement checkPs = conn.prepareStatement(checkSql);

            checkPs.setInt(1, id);


            ResultSet rs = checkPs.executeQuery();



            if(rs.next()) {


                double balance = rs.getDouble("Balance");


                if(balance >= amount) {


                    String sql = "UPDATE Customer SET Balance = Balance - ? WHERE Cust_id=?";


                    PreparedStatement ps = conn.prepareStatement(sql);


                    ps.setDouble(1, amount);

                    ps.setInt(2, id);



                    int rows = ps.executeUpdate();



                    if(rows > 0) {
                    	addTransaction(id, "Withdraw", amount);
                        System.out.println("Money Withdrawn Successfully!");

                        System.out.println("Remaining Balance: ₹" + (balance - amount));

                    }


                    ps.close();


                }
                else {

                    System.out.println("Insufficient Balance!");

                }


            }
            else {

                System.out.println("Customer Not Found!");

            }



            rs.close();

            checkPs.close();



        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }
 // Check Balance

    public void checkBalance() {

        try {

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();


            String sql = "SELECT Cust_name, Balance FROM Customer WHERE Cust_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1, id);


            ResultSet rs = ps.executeQuery();



            if(rs.next()) {


                System.out.println("\n===== ACCOUNT BALANCE =====");

                System.out.println("Customer Name : " + rs.getString("Cust_name"));

                System.out.println("Current Balance : ₹" + rs.getDouble("Balance"));


            }
            else {


                System.out.println("Customer Not Found!");


            }


            rs.close();

            ps.close();



        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public void addTransaction(int id, String type, double amount) {

        try {

            String sql = "INSERT INTO Transactions(Cust_id, Type, Amount) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, type);
            ps.setDouble(3, amount);

            ps.executeUpdate();

            ps.close();

        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public void viewTransactions() {

        try {

            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();


            String sql = "SELECT * FROM Transactions WHERE Cust_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);


            ResultSet rs = ps.executeQuery();


            System.out.println("\n===== TRANSACTION HISTORY =====");


            while(rs.next()) {

                System.out.println("-----------------------------");

                System.out.println("Transaction ID : " 
                + rs.getInt("Transaction_id"));

                System.out.println("Type           : " 
                + rs.getString("Type"));

                System.out.println("Amount         : ₹" 
                + rs.getDouble("Amount"));

                System.out.println("Date           : " 
                + rs.getTimestamp("Date"));

            }


            rs.close();
            ps.close();


        }
        catch(Exception e) {

            System.out.println(e.getMessage());

        }

    }

}