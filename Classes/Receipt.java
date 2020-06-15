package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt
{
    private static int receiptGlobalID=1;
    private int id;
    private Cashier cashier;
    private String dateAndTime;
    private List<Stock> stocks;
    private int  totalNumberOfReceipts;
    private double receiptPrice;

    private  List<Integer> quantity;
    private static double totalIncomeGlobal=0.;

    public Receipt(Cashier cashier) {

        this.cashier = cashier;

        this.stocks=new ArrayList<>();
        this.quantity=new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.dateAndTime=formatter.format(date);

        this.totalNumberOfReceipts = Receipt.receiptGlobalID;
        this.id=Receipt.receiptGlobalID;
        receiptGlobalID++;

    }

    public void addStock(Stock stock,int quantity)
    {
        int index = this.stocks.indexOf(stock);
        if(!this.stocks.contains(stock))
        {


            this.stocks.add(stock);
            //this.stocks.get(index).setStockQuantity(quantity);
            this.receiptPrice += quantity * stock.getPrice();
            this.quantity.add(quantity);
            Receipt.totalIncomeGlobal += stock.getPrice();
        }
        else
        {
            this.receiptPrice += quantity * stock.getPrice();
            this.quantity.set(index,quantity+this.quantity.get(index));
            Receipt.totalIncomeGlobal += stock.getPrice();
        }
    }


    public void printStocksInReceipt()
    {
        System.out.println("Stocks in Receipt are:");
        for(Stock x:this.stocks)
        {
            System.out.println(x);
        }
    }

    public void writeInFile()
    {
        String str=new String("Receipt"+getId()+".txt");
        File file = new File("Receipts");
        file.mkdir();
        try(FileWriter fr=new FileWriter(file.getName()+"/"+str))
        {
            fr.write(this+System.lineSeparator());


        } catch (IOException e)
        {

            e.printStackTrace();
        }
    }

    public static void readFromFile(String string)
    {
        try(FileReader fr=new FileReader(string))
        {
            BufferedReader br=new BufferedReader(fr);

            String line;

            br.read();
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static int getReceiptGlobalID()
    {
        return receiptGlobalID;
    }

    public static void setReceiptGlobalID(int receiptGlobalID) {
        Receipt.receiptGlobalID = receiptGlobalID;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public int getTotalNumberOfReceipts() {
        return totalNumberOfReceipts;
    }

    public void setTotalNumberOfReceipts(int totalNumberOfReceipts) {
        this.totalNumberOfReceipts = totalNumberOfReceipts;
    }

    public double getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(double receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public static double getTotalIncomeGlobal() {
        return totalIncomeGlobal;
    }

    public static void setTotalIncomeGlobal(double totalIncomeGlobal)
    {
        Receipt.totalIncomeGlobal = totalIncomeGlobal;
    }

    public int getId() {
        return id;
    }

    public int getQuantity(int index)
    {
        return this.quantity.get(index);
    }

    @Override
    public String toString() {
        String header =  String.format(" %-20s\n %-9s %-13s %7s\n %-15s %5s %10s\n %-15s %5s %10s\n",
                dateAndTime, "Cashier:",cashier.getName(), cashier.getId(),
                "Item", "Qty", "Price",
                "----", "---", "-----");
        String content = "";
        int i=0;
        for (Stock x:this.stocks){
            content += String.format(" %-15s %5d %10.2f\n", x.getStockName(),getQuantity(i), x.getPrice());
            i++;
        }
        String footer = String.format(" %-15s %5s %10s\n %-15s %16.2f",
                "----", "---", "-----",
                "Total", receiptPrice);

        return header + content + footer;
    }
}
