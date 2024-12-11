/*
Name: Cheuk Man Edmond To
Student No.: 301378748
Project: COMP228-006 Lab6
Date: Dec 10 2024
Purpose: Write a Java application that handles multiple ATM transactions (withdraw, deposit) at the same time.
Use Java Runnable interface to implement a Transaction class.
Perform withdraw and deposit operations in run method.
 */

package exercise1;

import java.security.SecureRandom;

//Use Java Runnable interface to implement a Transaction class.
public class Transaction implements Runnable {

    private final Account account; //Account object
    private final boolean isDeposit; //define is deposit or withdraw
    private final double amount;//add or deduct amount
    private final int sleepTime; //random sleep time for thread
    private final static SecureRandom rand = new SecureRandom();

    //constructor
    public Transaction(Account account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
        // pick random sleep time between 0 and 5 seconds
        sleepTime = rand.nextInt(5000); //milliseconds
    }

    @Override
    public void run() { //Perform withdraw and deposit operations in run method.
        try {
            Thread.sleep(sleepTime); // put thread to sleep to show random multi-thread
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); //re-interrupt the thread
        }

        if (isDeposit) { //define action
            account.deposit(amount); //deposit
        }else{
            account.withdraw(amount); //withdraw
        }
    }
}
