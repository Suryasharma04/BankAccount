package edu.ithaca.dturnbull.bank;

/**
 * Exception indicating that there are insufficient funds for a particular operation.
 */
public class InsufficientFundsException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InsufficientFundsException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
