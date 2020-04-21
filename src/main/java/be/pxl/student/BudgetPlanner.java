package be.pxl.student;



import be.pxl.student.util.BudgetPlannerImporter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BudgetPlanner {
    public static void main(String[] args) throws IOException {
        Path pathToFile = Paths.get("src/main/resources/account_payments.csv");
        BudgetPlannerImporter bpi = new BudgetPlannerImporter(pathToFile);

        System.out.println(bpi.getAccount());
        bpi.getPayments().forEach(System.out::println);
    }
}
