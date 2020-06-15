package com.company;

import java.util.ArrayList;
import java.util.List;

public class CashRegister
{
    private Cashier cashier;
    private Store store;
    private Receipt receipt;
    private static int id=1;
    private int idC;

    public CashRegister(Cashier cashier,Store store)
    {
        this.cashier = cashier;
        this.receipt=new Receipt(this.cashier);
        this.store=store;

        this.idC=CashRegister.id;
        CashRegister.id++;

    }


    public void payForStock(Stock stock,int quant)
    {
        boolean flag = false;
        try {


            flag=this.store.checkForAvailableStock(stock,quant);
        } catch (InsufficientNumberOfStockException e) {
            e.printStackTrace();
        }
        if(flag)
        {

           this.receipt.addStock(stock,quant);
        }
        else
        {
            System.out.println("You cannot buy this Stock!");
        }

    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CashRegister.id = id;
    }

    public void calculate()
    {
        System.out.println(cashier.getName()+" works on CashRegister:"+this.idC +" ReceiptPrice: " +this.receipt.getReceiptPrice());
        this.receipt.writeInFile();
    }
    public Receipt getReceipt()
    {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public int getIdC() {
        return idC;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    @Override
    public String toString()
    {
        return "CashRegister{" +
                "cashRegisterID="+this.idC+
                " cashier=" + cashier.getName() +
                " cashierID="+cashier.getId()+
                '}';
    }
}
