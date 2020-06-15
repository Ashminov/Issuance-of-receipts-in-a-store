package com.company;

public interface Chechkable
{
    public boolean checkForAvailableStock(Stock stock,int quantity)throws InsufficientNumberOfStockException;

}
