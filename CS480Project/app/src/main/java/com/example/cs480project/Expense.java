package com.example.cs480project;

import java.util.Arrays;
import java.util.Date;

public class Expense {
    private int id;
    private double amount;
    private String category;
    private Date date;
    private String description;
    private byte[] receiptImage;
    private int budgetId;

    public Expense(int id, double amount, String category, Date date, String description, byte[] receiptImage, int budgetId) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
        this.receiptImage = receiptImage;
        this.budgetId = budgetId;
    }

    public String toString() {
        return "Expense{ " +
                "id= " + id +
                ", amount= " + amount +
                ", category= " + category +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", receiptImage= " + Arrays.toString(receiptImage) +
                ", budgetId= " + budgetId +
                " }";
    }

    // getters and setters
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceiptImage() {
        return receiptImage;
    }
    public void setReceiptImage(byte[] receiptImage) {
        this.receiptImage = receiptImage;
    }

    public long getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }
}
