/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Venkat Sai Jarugula
 */
public class InsufficentFundsException extends Exception {

    /**
     * Creates a new instance of <code>InsufficentFundsException</code> without
     * detail message.
     */
    public InsufficentFundsException() {
    }

    /**
     * Constructs an instance of <code>InsufficentFundsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InsufficentFundsException(String msg) {
        super(msg);
    }
}
