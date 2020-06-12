package exceptions;

public class PaymentException extends Exception {
    public PaymentException(String message, Throwable e){
        super  (message, e);
    }
}
