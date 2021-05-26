/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

/**
 * This class contains details of a customer like name, date of birth etc.
 * @author Venkat Sai Jarugula
 */
public class Customer {

    private String dob;
    private String firstName;
    private String lastName;

    /**
     * An all argument constructor that takes first name, last name and date of birth as parameters to initialize the instance variables.
     * @param dob Date of birth of the customer
     * @param firstName First name of the customer
     * @param lastName Last name of the customer
     */
    public Customer(String dob, String firstName, String lastName) {
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * The getter method that returns the date of birth of a customer
     * @return returns date of birth as a String in MM/DD/YYYY format
     */
    public String getDob() {
        return dob;
    }

    /**
     * The getter method that returns the first name of the customer
     * @return first name of the customer as a String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The getter method that returns the last name of the customer
     * @return last name of the customer as a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This overriding method returns the formatted string containing the details of a customer.
     * @return details of a customer as a String
     */
    @Override
    public String toString() {
        return "Name:  " + lastName + ", " + firstName + "\nDate of Birth: " + dob;
    }

}
