//package be.pxl.student;
//
//import be.pxl.student.dao.*;
//import be.pxl.student.entity.Account;
//import be.pxl.student.entity.Payment;
//import be.pxl.student.util.BudgetPlannerImporter;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class BudgetPlanner {
//    public static void main(String[] args) {
//        Path pathToFile = Paths.get("src/main/resources/account_payments.csv");
//        BudgetPlannerImporter bpi = new BudgetPlannerImporter(pathToFile);
//
//        bpi.createAccountAndPayments();
//
//        AccountRepository accountRepository = new AccountRepositoryImpl();
//        //TODO: With getById this one does not print
//        Account account = null;
//        try {
//            account = accountRepository.create(bpi.getAccount());
//
//            assert account != null;
//            System.out.println("Account is: " + account);
//
//            for (Account counterAccount : bpi.getCounterAccounts()) {
//                 Account createdAccount = accountRepository.create(counterAccount);
//                assert createdAccount != null;
//                System.out.println("CounterAccount is: " + createdAccount);
//            }
//
//        } catch (AccountException e) {
//            e.printStackTrace();
//        }
//
////        Account accountById = null;
////        try{
////            accountById = accountRepository.getById(13);
////
////            assert accountById != null;
////            System.out.println("Account id is: " + accountById.getId());
////
////        } catch (AccountException e) {
////            e.printStackTrace();
////        }
//
//
//
//        //Payments
//        PaymentRepository paymentRepository = new PaymentRepositoryImpl();
//
//        List<Payment> payments = null;
//        try {
//            payments = paymentRepository.create(bpi.getAccount().getPayments());
//        } catch (PaymentException e) {
//            e.printStackTrace();
//        }
//
//        assert payments != null;
//        System.out.println("Payments are: " + payments);
//    }
//}
