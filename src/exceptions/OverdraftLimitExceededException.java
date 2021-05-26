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
public class OverdraftLimitExceededException extends Exception {

    /**
     * Creates a new instance of <code>OverdraftLimitExceededException</code>
     * without detail message.
     */
    public OverdraftLimitExceededException() {
    }

    /**
     * Constructs an instance of <code>OverdraftLimitExceededException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public OverdraftLimitExceededException(String msg) {
        super(msg);
    }
}
