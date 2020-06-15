package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
	// write your code here
        Scanner scan=new Scanner(System.in);
        int n;
        System.out.println("Hello!");
        System.out.println("Please input name for your store:");
        String name=scan.nextLine();
        Store st=new Store(name);
        System.out.println("Your store is created!");
        do {

            System.out.println();

            System.out.println("Open CashRegisters:");
            st.sellStocks();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();

            System.out.println("1.Information for working CashRegisters");
            System.out.println("2.Create CashRegister with Cashier");
            System.out.println("3.Create Stock for Store");
            System.out.println("4.Delivery Stock for Store");
            System.out.println("5.Available Stocks in Store");
            System.out.println("6.SellGoods");
            System.out.println("7.TotalIncome");
            System.out.println("8.Exit");
            n=scan.nextInt();

            if(n==1)
            {
                st.printCashRegisters();
            }
            else if(n==2)
            {
                System.out.println("Input name for Cashier:");
                String nameCashier=new Scanner(System.in).nextLine();
                System.out.println("Input id for Cashier:");

                int cashierId=scan.nextInt();
                Cashier cashier=new Cashier(nameCashier,cashierId);
                st.addCashier(cashier);

                CashRegister cr=new CashRegister(cashier,st);
                st.addCashRegisters(cr);
            }
            else if(n==3)
            {
                System.out.println("Input name for Stock:");

                String nameStock=new Scanner(System.in).nextLine();


                System.out.println("Input price for Stock:");

                double price=new Scanner(System.in).nextDouble();

                System.out.println("Input quantity of Stock for Store:");
                int quantity=scan.nextInt();

                System.out.println("Input Date of Expire:");

                String date=new Scanner(System.in).nextLine();

                System.out.println("Input ID for Stock:");
                int id=scan.nextInt();
                Stock stock=new Stock(nameStock,price,quantity,date,id);
                st.addGoods(stock);
            }
            else if (n==4)
            {
                System.out.println("Input name for which Stock do you want to make delivery:");

                String nameStock=new Scanner(System.in).nextLine();

                System.out.println("Input quantity of Stock:");
                int quantity=scan.nextInt();

                st.deliveryGoods(nameStock,quantity);
            }
            else if(n==5)
            {
                st.printStocks();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(n==6)
            {
                st.printStocks();

                System.out.println("Which stock do you want to take:");
                String nameStock=new Scanner(System.in).nextLine();

                Stock stock=st.getStockFromStore(nameStock);

               System.out.println("Input quantity do you want:");
                int quan=scan.nextInt();

                st.printCashRegisters();

                System.out.println("Input number from 1 to.. for CashRegister");
                int num=scan.nextInt();


               st.getCashRegister(num).payForStock(stock,quan);
            }
            if(n==8)
            {

                System.out.println("GOODBYE!");
                break;
            }
            if(n==7)
            {
                System.out.println("Current total income for store is:");
                System.out.println(st.totalIncome());
            }
        }while(true);

    }


}
