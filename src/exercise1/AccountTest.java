/*
Name: Cheuk Man Edmond To
Student No.: 301378748
Project: COMP228-006 Lab6
Date: Dec 10 2024
Purpose: Write a Java application that handles multiple ATM transactions (withdraw, deposit) at the same time.
Create an AccountTest class to test multiple transactions (threads).
 */
package exercise1;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {

    //Create an AccountTest class to test multiple transactions (threads)
    public static void main(String[] args){

        //opening balance accounts
        Account account = new Account(1000.0);

        //Use an ArrayList to create a list of three or more Transaction objects
        ArrayList<Transaction> transactions = new ArrayList<>();
        //Transaction(Account account, boolean isDeposit, double amount)
        transactions.add(new Transaction(account, true, 300.2));
        transactions.add(new Transaction(account, true, 300.4));
        transactions.add(new Transaction(account, true, -300.7));
        transactions.add(new Transaction(account, false, 50.89));
        transactions.add(new Transaction(account, false, 100.2));
        transactions.add(new Transaction(account, false, 10000.9));


        //print opening balance
        System.out.printf("Account opening balance: $%,.2f%n", account.getBalance());

        try {
            //create ExecutorService to manage threads
            ExecutorService executorService = Executors.newCachedThreadPool();

            //Start the transactions
            for(Transaction transaction : transactions){
                executorService.execute(transaction);
            }

            //shut down executorService
            executorService.shutdown();

            // Loop to wait for all tasks to complete before continuing
            while (!executorService.isTerminated()) {
                // empty
            }

            // Print out final balance
            System.out.printf("Final account balance: $%,.2f%n", account.getBalance());


        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
