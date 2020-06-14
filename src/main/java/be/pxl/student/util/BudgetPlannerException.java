package be.pxl.student.util;

public class BudgetPlannerException extends Exception {
    public BudgetPlannerException(String message, Throwable e){
        super(message, e);
    }

    public BudgetPlannerException(String message) {
        super(message);
    }
}
