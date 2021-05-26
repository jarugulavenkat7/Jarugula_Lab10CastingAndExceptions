/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import exceptions.InsufficentFundsException;
import exceptions.MaxTransactionsException;
import interfaces.Operations;
import java.time.LocalDateTime;

/**
 * This class extends the Account class implementing all the abstract method. This class is built on the below provided definition of savings account.
 * @author Venkat Sai Jarugula
 */
public class SavingsAccount extends Account {

    private boolean hasLimitedWithdrawals;

    /**
     * An three argument constructor that initializes the instance variables of with the values passed.
     * @param accountNumber  account number of the savings account
     * @param customer an object containing the details of the customer
     * @param hasLimitedWithdrawals boolean flag to determine withdrawal limit
     */
    public SavingsAccount(long accountNumber, Customer customer, boolean hasLimitedWithdrawals) {
        super(accountNumber, customer);
        this.hasLimitedWithdrawals = hasLimitedWithdrawals;
    }

    /**
     *This method takes transaction object as parameter and performs necessary action, based on the transaction type.
     * @param transaction
     * @return the updated balance after transaction
     * @throws Exception
     */
    @Override
    public double makeTransaction(Transaction transaction) throws Exception {
        super.getTransactions().add(transaction);
        double finalbalance = this.getBalance();

        if ((transaction.getTransactionType().equals(TransactionType.ONLINEPURCHASE)
                || transaction.getTransactionType().equals(TransactionType.WITHDRAW))
                && (this.getBalance() < transaction.getAmount())) {

            transaction.setAdditionalCharges(0.00);
            transaction.setStatus("FAILED");
           // super.getTransactions().add(transaction);
            throw new InsufficentFundsException();
        } else {

            if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
                transaction.setStatus("SUCCESS");
                transaction.setAdditionalCharges(0.0);
                finalbalance = this.getBalance() + transaction.getAmount();
                super.setBalance(finalbalance);

            } else if (transaction.getTransactionType().equals(TransactionType.ONLINEPURCHASE)) {
                transaction.setStatus("SUCCESS");
                transaction.setAdditionalCharges(1.99);
                finalbalance = finalbalance - (transaction.getAmount() + transaction.getAdditionalCharges());
                super.setBalance(finalbalance);
            } else if (transaction.getTransactionType().equals(TransactionType.WITHDRAW)) {

                if ((this.getBalance() > transaction.getAmount()) && hasLimitedWithdrawals == false) {
                    if (getNoofWithdrawals() > 6) {
                        if (transaction.getAmount() * 0.01 > 2.59) {
                            transaction.setAdditionalCharges(transaction.getAmount() * 0.01);
                        } else {
                            transaction.setAdditionalCharges(2.59);
                        }
                        transaction.setStatus("SUCCESS");
                        finalbalance = finalbalance - (transaction.getAmount() + transaction.getAdditionalCharges());
                        super.setBalance(finalbalance);

                    } else {

                        transaction.setStatus("SUCCESS");
                        finalbalance = finalbalance - (transaction.getAmount());
                        super.setBalance(finalbalance);
                    }
                } else {
                    if (getNoofWithdrawals() > 6) {

                        transaction.setStatus("FAILED");
                        transaction.setAdditionalCharges(0.0);
                        throw new MaxTransactionsException();
                    } else {

                        transaction.setStatus("SUCCESS");
                        finalbalance = finalbalance - transaction.getAmount();
                        super.setBalance(finalbalance);

                    }
                }

            }
        }
        return finalbalance;
    }

    /**
     * This method generates the statement for an account in a specified format.
     * @return the statement as a String
     */
    @Override
    public String generateStatement() {
        String statementString = this.toString()
                + "\n-------------------------------------------------------------------------------\n"
                + "Transaction Type	Transaction Time	Amount	Additional Charges	Status";

        for (Transaction transaction : this.getTransactions()) {
            statementString += transaction.toString();
        }
        statementString += "\n-------------------------------------------------------------------------------"
                + "\nCurrent Balance: " + String.format("%.2f", this.getBalance()) + "		Interest: $" + String.format("%.2f", (this.getBalance() * (Operations.SAVING_INTEREST / 100)))
                + "\n*******************************************************************************";
        return statementString;
    }

    /**
     * This overriding method returns the details of an account.
     * @return formatted string with the details of the savings account as a String
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nAccount Type: " + "Savings Account	Interest Rate: " + String.format("%.2f", Operations.SAVING_INTEREST) + "%"
                + "\nTransaction Limit for withdrawal: " + (this.hasLimitedWithdrawals ? "6 Transactions" : "No Limit");
    }

    /**
     *This method returns the number of withdrawal transactions made by a customer for an account in the current month using the system date.
     * @return the number of withdrawals within the current month
     */
    public int getNoofWithdrawals() {
        int currentMonthValue = LocalDateTime.now().getMonthValue();
        int numberOfTransactions = 0;
        for (Transaction transaction : this.getTransactions()) {
            if (transaction.getTransactionType().equals(TransactionType.WITHDRAW)
                    && transaction.getTrannsactonTime().getMonthValue() == currentMonthValue) {
                numberOfTransactions += 1;

            }
        }
        return numberOfTransactions;
    }

}
