package be.pxl.student.exceptions;

public class AccountException extends Exception{
    public AccountException(String message, Throwable e){
        super(message, e);
    }

    public AccountException(String message) {
        super(message);
    }
}
