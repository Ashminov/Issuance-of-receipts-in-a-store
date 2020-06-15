package com.company;

public class CashRegisterThread implements Runnable {
  private Store st;
  private CashRegister cr;


    public CashRegisterThread(Store store, CashRegister x)
    {
        this.st=store;
        this.cr=x;
    }


    public void run()
    {

        cr.calculate();
    }

}
