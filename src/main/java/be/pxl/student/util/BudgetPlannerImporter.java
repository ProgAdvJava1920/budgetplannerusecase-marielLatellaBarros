//package be.pxl.student.util;
//
//import be.pxl.student.entity.AccountOld;
//import be.pxl.student.entity.Payment;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// * Util class to import csv file
// */
//public class BudgetPlannerImporter {
//    private AccountOld account;
//    private List<AccountOld> counterAccounts;
//    private List<Payment> payments;
//    private final Path path;
//
//    public BudgetPlannerImporter(Path pathToRead) { //src/main/resources/account_payments.csv
//    this.path = pathToRead;
//    }
//
//    public List<Payment> createAccountAndPayments() {
//        convertDataToObjects();
//        return payments;
//    }
//
//    public AccountOld getAccount() {
//        return account;
//    }
//    public List<AccountOld> getCounterAccounts() {
//        return counterAccounts;
//    }
//
//    private void convertDataToObjects() {
//
//        try (BufferedReader reader = Files.newBufferedReader(path)) {
//            reader.readLine();
//            String line = null;
//
//            payments = new ArrayList<>();
//
//            while ((line = reader.readLine()) != null ) {
//                if (account == null) {
//                    account = createAccountObject(line);
//
//                    Payment newPayment = createPaymentObject(line);
//                    payments.add(newPayment);
//                } else {
//                    Payment newPayment = createPaymentObject(line);
//                    payments.add(newPayment);
//                }
//            }
//            account.setPayments(payments);
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private AccountOld createAccountObject(String line) {
//        String[] lines = line.split(",");
//        account = new AccountOld();
//        account.setIBAN(lines[1]);
//        account.setName(lines[0]);
//        return account;
//    }
//
//    private Payment createPaymentObject(String line) {
//        String[] lines = line.split(",");
//        Payment payment= new Payment();
//
//        //TODO: fix LocalDate format
//        //LocalDate date = LocalDate.parse(lines[3]);
//        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH); //Tue Feb 18 04:15:12 CET 2020
//        //DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
//        //LocalDate date = LocalDate.parse(lines[3], dtf);
//        //payment.setDate(date);
//
//        //payment.setCounterAccount(lines[2]);
//        payment.setAmount(Float.parseFloat(lines[4]));
//        payment.setCurrency(lines[5]);
//        payment.setDetail(lines[6]);
//
//        payment.setAccount(this.account);
//        AccountOld counterAccount = createCounterAccount(lines[2]);
//        payment.setCounterAccount(counterAccount);
//        counterAccounts.add(counterAccount);
//
//        return payment;
//    }
//
//    private AccountOld createCounterAccount(String line) {
//        AccountOld counterAccount = new AccountOld();
//        counterAccount.setIBAN(line);
//        return counterAccount;
//    }
//
//    //TODO: Use log4j to print the Account toString to console
//    //TODO: Use log4j to print the Account toString to an output.log file
//    //TODO: Use log4j to print errors and warnings
//
//
//    @Override
//    public String toString() {
//        return "BudgetPlannerImporter{" +
//                "account=" + account +
//                ", counterAccounts=" + counterAccounts +
//                ", payments=" + payments +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        BudgetPlannerImporter that = (BudgetPlannerImporter) o;
//        return Objects.equals(account, that.account);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(account);
//    }
//}
