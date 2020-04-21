package be.pxl.student.dao;

public class AccountException extends Exception{
    public AccountException(String message, Throwable e){
        super(message, e);
    }
}
