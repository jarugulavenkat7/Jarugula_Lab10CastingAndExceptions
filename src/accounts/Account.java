/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import interfaces.Operations;
import java.util.ArrayList;

/**
 *This abstract class contains attributes and methods of a bank account implementing the Operations.
 * @author Venkat Sai Jarugula
 */
public abstract class Account implements Operations {

    private long accountNumber;
    private double balance;
    private Customer customer;
    private ArrayList<Transaction> transactions;

    /**
     *A two argument constructor that initializes the instance variables using the parameters passed.
     * @param accountNumber Attribute that stores the account number of a customer.
     * @param customer Attribute that stores the details of a customer as a Customer object.
     */ 
    public Account(long accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();

    }

    /**
     * Sets the balance
     * @param balance Balance amount to be set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * The getter method that returns the account number.
     * @return account number of an account as a long value.
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     *The getter method that returns the balance of an account.
     * @return balance of an account as a double
     */
    public double getBalance() {
        return balance;
    }

    /**
     *The getter method that returns the Customer object associated with this account.
     * @return Customer object containing details of a customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *This getter method returns all the transactions made by an account as an ArrayList.
     * @return transactions as an ArrayList
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * This overriding method returns the details of an account.
     * @return formatted string with the details of an account as a String
     */
    @Override
    public String toString() {
        return customer.toString()
                + "\nAccount Number: " + accountNumber;
    }

    /**
     * This method generates the statement for an account in a specified format. Output format: Account.toString()+ All transactions
     * @return  the statement as a String
     */
    @Override
    public String generateStatement() {
        String statementString = this.toString()
                + "-------------------------------------------------------------------------------"
                + String.format("%-19s %-24s %-15s %-24s %-9s %n",
                        "Transaction Type", "Transaction Time", "Amount", "Additional Charges", "Status");
        for (Transaction transaction : transactions) {
            statementString += transaction.toString();
        }
        statementString += "-------------------------------------------------------------------------------";
        return statementString;
    }

    /**
     *
     * @param transaction
     * @return
     * @throws Exception
     */
    @Override
    public abstract double makeTransaction(Transaction transaction) throws Exception;

}
