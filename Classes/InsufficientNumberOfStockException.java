package com.company;

public class InsufficientNumberOfStockException extends Exception
{

    private final Stock stock;
    private final int number;

    public InsufficientNumberOfStockException(Stock stock, int number)
    {
        this.stock = stock;
        this.number = number;
    }

    public Stock getStock() {
        return stock;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString()
    {
        return "InsufficientNumberOfStockException{" +
                "stock=" + stock.getStockName() +
                ", number of shortages= " + number +
                '}';
    }
}
