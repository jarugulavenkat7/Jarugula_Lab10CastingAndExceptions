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
public class MaxTransactionsException extends Exception {

    /**
     * Creates a new instance of <code>MaxTransactionsException</code> without
     * detail message.
     */
    public MaxTransactionsException() {
    }

    /**
     * Constructs an instance of <code>MaxTransactionsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MaxTransactionsException(String msg) {
        super(msg);
    }
}
