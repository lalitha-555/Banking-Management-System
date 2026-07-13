package com.bank;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();

        try {

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Customer ("
                    + "Cust_id INTEGER PRIMARY KEY,"
                    + "Cust_name TEXT NOT NULL,"
                    + "Address TEXT,"
                    + "City TEXT,"
                    + "Pincode INTEGER,"
                    + "Phone_no TEXT,"
                    + "Balance REAL)";

            stmt.execute(sql);

            System.out.println("Customer Table Created Successfully!");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}