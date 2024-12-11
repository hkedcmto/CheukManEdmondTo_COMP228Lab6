/*Name: Cheuk Man Edmond To
Student No.: 301378748
Project: COMP228-006 Lab6
Date: Dec 10 2024
Purpose: Write a Java application that handles multiple ATM transactions (withdraw, deposit) at the same time.
Create an Account class and implement both deposit and withdraw operations.
Synchronize the operations to allow thread synchronization.
 */

package exercise1;

public class Account {

    private double balance; //store the current balance

    public Account(double openBalance) { //constructor to store the opening balance
        this.balance = openBalance;
    }

    //Synchronize the operations to allow thread synchronization.
    //Handle deposit transaction
    public synchronized void deposit(double amount) {
        double oldBalance = balance;
        try{
            if (amount <= 0) { //input validation: amount must greater than 0
                throw new Exception("Error: "+ Thread.currentThread().getName() +" amount ("+amount+") must greater than 0"); //Error msg
            }
            balance += amount; //add amount to balance
            System.out.printf("%s deposit successful. New balance: %,.2f + %,.2f = $%,.2f%n", Thread.currentThread().getName(), oldBalance, amount, balance); //Display result to user
        }catch (Exception e) {
            System.err.println(e.getMessage()); //Display Error msg
        }
    }

    //Handle withdraw transaction
    public synchronized void withdraw(double amount) {
        double oldBalance = balance;
        try{
            if(amount <= 0) {//input validation: amount must greater than 0
                throw new Exception("Error: Amount must greater than 0"); //Error msg
            }
            if (balance - amount < 0) { //is enough balance for deduction
                throw new Exception("Error: "+ Thread.currentThread().getName() +" insufficient fund to -$"+amount+". Current balance: $"+oldBalance); //error msg
            }
            balance -= amount; //deduct amount in balance
            System.out.printf("%s withdraw successful. New balance: %,.2f - %,.2f = $%,.2f%n", Thread.currentThread().getName(), oldBalance, amount, balance); //Display result to user
        }catch (Exception e) {
            System.err.println(e.getMessage()); //display error msg
        }
    }

    //Return current balance
    public double getBalance() {
        return balance;
    }
}
