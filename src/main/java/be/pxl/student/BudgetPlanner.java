package be.pxl.student;

import be.pxl.student.entities.Account;
import be.pxl.student.util.BudgetPlannerException;
import be.pxl.student.util.BudgetPlannerImporter;
import be.pxl.student.util.BudgetPlannerMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Paths;
import java.util.List;

public class BudgetPlanner {

    private static Logger logger = LogManager.getLogger(BudgetPlanner.class);

    public static void main(String[] args) {
        String csvFile = "src/main/resources/account_payments.csv";

        try{
            logger.info("Starting csv import");
            List<String> list = BudgetPlannerImporter.readCsvFile(Paths.get(csvFile));
            logger.info("Csv import done");
            logger.info("Starting account mapping");
//            Can also with Payments
            List<Account> accounts = new BudgetPlannerMapper().mapAccounts(list);
            accounts.forEach(logger::debug);
            logger.info("account mapping done");

            insertIntoDatabase();

        } catch (BudgetPlannerException e) {
            logger.error("Exception importing accounts", e);
        }
    }

    private static void insertIntoDatabase() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner");
    }
}
