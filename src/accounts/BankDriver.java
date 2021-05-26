/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The driver class to test all the classes
 *
 * @author Venkat Sai Jarugula
 */
public class BankDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Account> accounts = new ArrayList<>();

        Scanner sc = new Scanner(new File("input.txt"));

        while (sc.hasNext("savings") || sc.hasNext("current")) {

            String accountType = sc.nextLine();

            String name = sc.nextLine();
            String[] nameArray = name.split(" ");
            String firstName = nameArray[0];

            String lastName = nameArray[1];

            String dob = sc.nextLine();

            Customer customer = new Customer(dob, firstName, lastName);
            long accountNumber = sc.nextLong();

            Account account = null;
            if (accountType.equals("current")) {
                account = new CurrentAccount(accountNumber, customer);
            } else {
                boolean hasLimitedWithdrawals = sc.nextBoolean();

                account = new SavingsAccount(accountNumber, customer, hasLimitedWithdrawals);
            }

            System.out.println("------------------------------------------------------------");
            System.out.println("Name of the customer: " + customer.getFirstName() + "  " + customer.getLastName());

            System.out.println("------------------------------------------------------------");
            sc.nextLine();
            while (sc.hasNext("DEPOSIT") || sc.hasNext("WITHDRAW") || sc.hasNext("ONLINEPURCHASE")) {
                String completeLine = sc.nextLine();
                //System.out.println(completeLine);
                String[] transactionLine = completeLine.split(" ");

                String transactionType = transactionLine[0];

                double amount = Double.parseDouble(transactionLine[1]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
                LocalDateTime transactionTime = LocalDateTime.parse(transactionLine[2] + " " + transactionLine[3], formatter);

                Transaction transaction = new Transaction(TransactionType.valueOf(transactionType), amount, transactionTime);
                try {
                    double balance = account.makeTransaction(transaction);
                    System.out.println("The balance after " + transaction.getTransactionType() + " in dollars is " + String.format("%.2f", Math.round(balance * 100) / 100.0));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            accounts.add(account);
        }

        System.out.println("************************************************************************");
        System.out.println("*********Invoke getNoofWithdrawals() on SavingsAccount objects**********");
        System.out.println("************************************************************************");
        for (Account account : accounts) {

            if (account.getClass().toString().contains("SavingsAccount")) {
                SavingsAccount sa = (SavingsAccount) account;

                System.out.println(sa.getCustomer().getFirstName() + " made " + sa.getNoofWithdrawals() + " withdrawals in this month.");
            }

        }

        System.out.println("***********************************************************************");
        System.out.println("****Invoke generateStatement() on all objects in accounts ArrayList****");
        System.out.println("************************************************************************");
        for (Account account : accounts) {
            System.out.println(account.generateStatement());
        }

    }

}
