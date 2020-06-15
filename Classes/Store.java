package com.company;
import java.util.ArrayList;
import java.util.List;

public class Store implements Chechkable,Printable
{
    private List<Stock> stocksStore;
    private List<Cashier> cashiersStore;
    private List<CashRegister> cashRegisters;
    private String name;
    private int receiptCount;



    public Store(String name)
    {
        this.name=name;
        this.stocksStore=new ArrayList<>();
        this.cashRegisters=new ArrayList<>();
        this.cashiersStore=new ArrayList<>();

    }

    public void addCashier(Cashier cashier)
    {
        if(!this.cashiersStore.contains(cashier))
        {

            this.cashiersStore.add(cashier);
        }
        else
        {
            System.out.println("You added this cashier!");
        }
    }

    public void removeCashier(Cashier cashier)
    {
        if(this.cashiersStore.contains(cashier))
        {
            this.cashiersStore.remove(cashier);
        }
        else
        {
            System.out.println("This cashier doesnt work in this store!");
        }
    }

    public void addCashRegisters(CashRegister cash)
    {

        this.cashRegisters.add(cash);
    }

    public void addGoods(Stock stock)
    {
        this.stocksStore.add(stock);
    }

    public void deliveryGoods(String name,int quant)
    {
        boolean flag=false;
        for(Stock x:this.stocksStore)
        {
            if(x.getStockName().equals(name))
            {
                x.setStockQuantity(x.getStockQuantity()+quant);
                flag=true;
                break;

            }
        }

        if(!flag)
        {
            System.out.println("This stock doesnt exist in the Store!");
        }
    }

    public void printStocks()
    {
        System.out.println("Goods in store are:");
        for(Stock x:this.stocksStore)
        {
            if(x.getStockQuantity()>0)
            {
                System.out.println(x);
            }
        }
    }

    public void printCashRegisters()
    {
        System.out.println("CashRegisters in store are:");
        for(CashRegister x:this.cashRegisters)
        {
            System.out.println(x);
        }
    }
    public CashRegister getCashRegister(int num)
    {
        CashRegister cash=null;
        boolean flag=false;
        for(CashRegister x:this.cashRegisters)
        {
              if(x.getIdC()==num)
              {
                  cash=x;
                  flag=true;
                  break;
              }

        }

        if(!flag)
        {
            return this.cashRegisters.get(this.cashRegisters.size()-1);
        }
        else
        {
            return cash;
        }
    }

    public Stock getStockFromStore(String name)
    {
        Stock stock=null;
        for(Stock x:this.stocksStore)
        {
            if(x.getStockName().equals(name))
            {
                stock=x;

                break;
            }
        }

        return stock;
    }
    public boolean checkForAvailableStock(Stock stock,int quantity)throws InsufficientNumberOfStockException
    {
        if (this.stocksStore.contains(stock))
        {
            int index;
            index = this.stocksStore.indexOf(stock);
            if (this.stocksStore.get(index).getStockQuantity()>=quantity)
            {
                this.stocksStore.get(index).setStockQuantity(this.stocksStore.get(index).getStockQuantity()-quantity);

                return true;
            }
            else
             {
                    throw new InsufficientNumberOfStockException(stock,quantity-stock.getStockQuantity());
            }
        }
        else
        {
            return false;
        }
    }
    public synchronized void sellStocks()
    {
        for (CashRegister x: this.cashRegisters)
                {
                    CashRegisterThread  ctr=new CashRegisterThread(this,x);
                    Thread tr=new Thread(ctr);
                    tr.start();

                }
    }


    public double totalIncome()
    {
        double sum=0.;
        for(CashRegister x:this.cashRegisters)
        {
            sum+=x.getReceipt().getReceiptPrice();
        }

        return sum;
    }


    public int getReceiptCount()
    {
        return receiptCount;
    }

    public void setReceiptCount()
    {
        this.receiptCount=Receipt.getReceiptGlobalID();
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", receiptCount=" + receiptCount +
                '}';
    }
}



