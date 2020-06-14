package be.pxl.student;

import be.pxl.student.entities.Account;
import be.pxl.student.exceptions.AccountException;
import be.pxl.student.jpa.AccountRepositoryImpl;
import be.pxl.student.util.BudgetPlannerException;
import be.pxl.student.util.BudgetPlannerImporter;
import be.pxl.student.util.BudgetPlannerMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Paths;
import java.util.List;

public class BudgetPlanner {

    private static final Logger logger = LogManager.getLogger(BudgetPlanner.class);

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

            insertIntoDatabase(accounts);
            logger.info("accounts effectively inserted into database.");

        } catch (BudgetPlannerException e) {
            logger.error("Exception importing accounts", e);
        } catch (AccountException e) {
            logger.error("Exception inserting accounts into database", e);
        }
    }

    private static void insertIntoDatabase(List<Account> accounts) throws AccountException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AccountRepositoryImpl accountRepo = new AccountRepositoryImpl(entityManager);

        for (Account account :
                accounts) {
            accountRepo.create(account);
        }
    }
}
