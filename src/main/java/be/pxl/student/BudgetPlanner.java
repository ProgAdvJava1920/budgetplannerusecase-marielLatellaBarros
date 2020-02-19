package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BudgetPlanner {
    public static void main(String[] args) {

        Path path = Paths.get("src/main/resources/account_payments.csv");
        BudgetPlannerImporter bpi = new BudgetPlannerImporter(path);

        bpi.getPayments();
        System.out.println(bpi);
    }


}
