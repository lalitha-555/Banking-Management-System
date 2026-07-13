package com.bank;

import java.util.Scanner;

public class BankingManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerService service = new CustomerService();

        int choice;


        do {

            System.out.println("\n======================================");
            System.out.println("       BANKING MANAGEMENT SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Deposit Money");
            System.out.println("7. Withdraw Money");
            System.out.println("8. Check Balance");
            System.out.println("9. Transaction History");
            System.out.println("10. Exit");


            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();



            switch(choice) {


                case 1:

                    service.addCustomer();

                    break;



                case 2:

                    service.viewCustomers();

                    break;



                case 3:

                    service.searchCustomer();

                    break;



                case 4:

                    service.updateCustomer();

                    break;



                case 5:

                    service.deleteCustomer();

                    break;



                case 6:

                    service.depositMoney();

                    break;



                case 7:

                    service.withdrawMoney();

                    break;



                case 8:

                    service.checkBalance();

                    break;



                case 9:
                    service.viewTransactions();
                    break;


                case 10:
                    System.out.println("Thank You For Using Banking System!");
                    break;


                default:

                    System.out.println("\nInvalid Choice!");

            }


        }  while(choice != 10);


        sc.close();

    }

}