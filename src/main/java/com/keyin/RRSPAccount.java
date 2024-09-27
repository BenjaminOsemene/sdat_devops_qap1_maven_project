package com.keyin;

public class RRSPAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void rollover(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid rollover amount.");
        }
        balance -= amount;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }
        balance += amount;
    }
}



