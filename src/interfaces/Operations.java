/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import accounts.Transaction;

/**
 *
 * @author Venkat Sai Jarugula
 */
public interface Operations {

    /**
     *A constant that holds the overdraft limit provided for a checking account customer.
     */
    static final double OVERDRAFT_LIMIT = 500.00;

    /**
     * A constant that holds the percentage of interest for a savings account.
     */
    static final double SAVING_INTEREST = 5.80;

    /**
     * This method generates the statement for an account in a specified format.
     * @return the final balance of the account after making the transaction.
     */
    String generateStatement();

    /**
     *The abstract method takes a Transaction as a parameter to perform necessary action depending on the type of transaction.
     * @param transaction
     * @return the statement as a String
     * @throws Exception
     */
    double makeTransaction(Transaction transaction) throws java.lang.Exception;
}
