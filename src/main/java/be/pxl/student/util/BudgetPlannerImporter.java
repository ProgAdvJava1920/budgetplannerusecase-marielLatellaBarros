package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private Account account;
    private List<Payment> payments;
    private final Path path;

    public BudgetPlannerImporter(Path pathToRead) throws IOException { //src/main/resources/account_payments.csv
    this.path = pathToRead;
    }

    public List<Payment> getPayments() {
        convertDataToObjects();
        return payments;
    }

    public Account getAccount() {
        return account;
    }

    private void convertDataToObjects() {

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            String line = null;

            payments = new ArrayList<>();

            while ((line = reader.readLine()) != null ) {
                if (account == null) {
                    account = createAccountObject(line);

                    Payment newPayment = createPaymentObject(line);
                    payments.add(newPayment);
                } else {
                    Payment newPayment = createPaymentObject(line);
                    payments.add(newPayment);
                }
            }
            account.setPayments(payments);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Account createAccountObject(String line) {
        String[] lines = line.split(",");
        account = new Account();
        account.setIBAN(lines[1]);
        account.setName(lines[0]);
        return account;
    }

    private Payment createPaymentObject(String line) {
        String[] lines = line.split(",");
        Payment payment= new Payment();

        //TODO: fix LocalDate format
        //LocalDate date = LocalDate.parse(lines[3]);
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH); //Tue Feb 18 04:15:12 CET 2020
        //DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        //LocalDate date = LocalDate.parse(lines[3], dtf);
        //payment.setDate(date);

        payment.setAmount(Float.parseFloat(lines[4]));
        payment.setCurrency(lines[5]);
        payment.setDetail(lines[6]);

        return payment;
    }

    //TODO: Use log4j to print the Account toString to console
    //TODO: Use log4j to print the Account toString to an output.log file
    //TODO: Use log4j to print errors and warnings


    @Override
    public String toString() {
        return "BudgetPlannerImporter{" +
                "account=" + account +
                ", payments=" + payments +
                ", path=" + path +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetPlannerImporter that = (BudgetPlannerImporter) o;
        return Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }
}
