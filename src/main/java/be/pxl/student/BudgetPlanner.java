package be.pxl.student;

import be.pxl.student.dao.AccountRepository;
import be.pxl.student.dao.AccountRepositoryImpl;
import be.pxl.student.dao.AccountException;
import be.pxl.student.entity.Account;
import be.pxl.student.util.BudgetPlannerImporter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BudgetPlanner {
    public static void main(String[] args) {
        Path pathToFile = Paths.get("src/main/resources/account_payments.csv");
        BudgetPlannerImporter bpi = new BudgetPlannerImporter(pathToFile);

        bpi.createAccountAndPayments();

        AccountRepository accountRepository = new AccountRepositoryImpl();
        Account account = null;
        try {
            account = accountRepository.create(bpi.getAccount());
        } catch (AccountException e) {
            e.printStackTrace();
        }

        assert account != null;
        System.out.println("Account id is: " + account.getId());
    }
}
