package exceptions;

public class AccountNotFoundException extends AccountException{
    public AccountNotFoundException(String message, Throwable e){
        super(message, e);
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
