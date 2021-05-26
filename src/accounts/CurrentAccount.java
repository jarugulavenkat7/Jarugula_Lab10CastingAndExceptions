/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import exceptions.OverdraftLimitExceededException;
import interfaces.Operations;

/**
 * This class extends the Account class implementing all the abstract method. This class is built on the below provided definition of current account.
A checking account is a type of bank account that is designed for everyday money transactions. A checking account does not have any withdrawal restrictions, but no interest is earned. It also provides features like overdraft with no additional fees.
 * @author Venkat Sai Jarugula
 */
public class CurrentAccount extends Account {

    /**
     *A two argument constructor that initializes the instance variables using the arguments passed.
     * @param accountNumber account number of the current account
     * @param customer an object containing the details of the customer
     */
    public CurrentAccount(long accountNumber, Customer customer) {
        super(accountNumber, customer);
    }

    /**
     * This method takes transaction object as parameter and performs necessary action, based on the transaction type.
     * @param transaction
     * @return the updated balance after transaction
     * @throws Exception
     */
    @Override
    public double makeTransaction(Transaction transaction) throws Exception {
      double finalbalance=0.0;
    
        if((transaction.getTransactionType().equals(TransactionType.ONLINEPURCHASE)
                ||transaction.getTransactionType().equals(TransactionType.WITHDRAW) )&&
                (this.getBalance()+Operations.OVERDRAFT_LIMIT)<transaction.getAmount()){
            
                transaction.setAdditionalCharges(0.00);
                transaction.setStatus("FAILED");
                super.getTransactions().add(transaction);
                throw new OverdraftLimitExceededException();
        }
        else{
            if(transaction.getTransactionType().equals(TransactionType.WITHDRAW)){
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("SUCCESS");
                finalbalance=this.getBalance()-transaction.getAmount();
                super.setBalance(finalbalance);
        }
            else if(transaction.getTransactionType().equals(TransactionType.ONLINEPURCHASE)){
                 transaction.setAdditionalCharges(1.59);
                transaction.setStatus("SUCCESS");
                finalbalance=this.getBalance()-(transaction.getAmount()+transaction.getAdditionalCharges());
                super.setBalance(finalbalance);
            }
            else if(transaction.getTransactionType().equals(TransactionType.DEPOSIT)){
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("SUCCESS");
                finalbalance=this.getBalance()+transaction.getAmount();
                                super.setBalance(finalbalance);
            }
            super.getTransactions().add(transaction);
        }
         return finalbalance;       
    }

    /**
     * This method generates the statement for an account in a specified format.
     * @return the statement as a String
     */
    @Override
     public String generateStatement(){
         double overDraftUsage=0;
       String statementString=this.toString()
                +"\n-------------------------------------------------------------------------------\n"
               +"Transaction Type	Transaction Time	Amount	Additional Charges	Status";
               // +String.format("%-16s %-16s %-15s %-15s %-5s",       "Transaction Type", "Transaction Time", "Amount", "Additional Charges", "Status");
        for(Transaction transaction:this.getTransactions()){
            statementString+=transaction.toString();
        }
        if(this.getBalance()<0){
            
            overDraftUsage=Math.abs(this.getBalance());
            this.setBalance(0);
        }
       statementString+="\n-------------------------------------------------------------------------------"
               +"\nCurrent Balance: "+String.format("%.2f",this.getBalance() )
               +"\nOverdraft usage: $"+String.format("%.2f",overDraftUsage)+"		Overdraft available: $"+String.format("%.2f",(Operations.OVERDRAFT_LIMIT-overDraftUsage))
               +"\n*******************************************************************************";
             
        return statementString;
     }
     
    /**
     * This overriding method returns the details of an account.
     * @return formatted string with the details of the current account as a String
     */
    @Override
     public String toString(){
         return super.toString()
                 +"\nAccount Type: Current Account	Overdraft Limit: $"+String.format("%.2f",Operations.OVERDRAFT_LIMIT);
     }
}
