package com.company;

public class Stock
{

    private String stockName;
    private int stockID;
    private int stockQuantity;
    private double price;
    private String expiryDate;

    public Stock(String stockName, double price,int quant, String dateExpired,int id)
    {
        this.stockName = stockName;
        this.price = price;
        this.expiryDate = dateExpired;
        this.stockQuantity=quant;
        this.stockID=id;

    }

    public int getStockQuantity()
    {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    public int getStockID() {
        return stockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString()
    {
        return "Stock{" +
                "stockName='" + stockName + '\'' +
                ", stockID=" + stockID +
                ", price=" + price +
                ", quantity="+stockQuantity+
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
