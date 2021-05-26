/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import java.time.LocalDateTime;

/**
 * This class contains the details of a transaction made on an account.
 * @author Venkat Sai Jarugula
 */
public class Transaction {

    private double additionalCharges;
    private double amount;
    private String status;
    private LocalDateTime trannsactonTime;
    private TransactionType transactionType;

    /**
     * The three argument constructor that initializes the instance variables except status and additional charges.
     * @param transactionType transaction type as a TransactionType enum
     * @param amount transaction amount as a double value
     * @param trannsactonTime Time of the transaction as a as LocalDateTime
     */ 
    public Transaction(TransactionType transactionType, double amount, LocalDateTime trannsactonTime) {
        this.amount = amount;
        this.trannsactonTime = trannsactonTime;
        this.transactionType = transactionType;
    }

    /**
     *
     * @return
     */
    public double getAdditionalCharges() {
        return additionalCharges;
    }

    /**
     *
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getTrannsactonTime() {
        return trannsactonTime;
    }

    /**
     *
     * @return
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     *
     * @param additionalCharges
     */
    public void setAdditionalCharges(double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\n" + String.format("%-23s %-23s %-15s %-16s %s", transactionType, trannsactonTime, "$" + String.format("%.2f", amount), "$" + String.format("%.2f", additionalCharges), status);
    }

}
