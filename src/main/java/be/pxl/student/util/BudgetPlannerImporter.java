package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private Path path;
    private Account account;

    public BudgetPlannerImporter(Path pathFileToRead) { //src/main/resources/account_payments.csv
        this.path = pathFileToRead;
    }

    public List<Payment> getPayments(){
        List<Payment> payments = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            String line = null;

            while ((line = reader.readLine()) != null ) {

                if (account == null) {
                    account = getAccount(line);
                    Payment newPayment = getPayment(line);
                    payments.add(newPayment);
                } else {
                    Payment newPayment = getPayment(line);
                    payments.add(newPayment);
                }
            }
            addPaymentsToAccount(payments);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }

    private void addPaymentsToAccount(List<Payment> payments) {
        account.setPayments(payments);
    }

    private Payment getPayment(String line) {
        Payment payment= new Payment();
        String[] lines = line.split(",");

        //TODO: fix LocalDate format
        //LocalDate date = LocalDate.parse(lines[3]);
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd hh:mm:ss Z yyyy", Locale.ENGLISH); //Tue Feb 18 04:15:12 CET 2020
        //DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        //LocalDate date = LocalDate.parse(lines[3], dtf);
        float amount = Float.parseFloat(lines[4]);
        String currency = lines[5];
        String detail = lines[6];

        //payment.setDate(date);
        payment.setAmount(amount);
        payment.setCurrency(currency);
        payment.setDetail(detail);

        return payment;
    }

    private Account getAccount(String line) {
        String[] lines = line.split(",");
        account = new Account();
        account.setIBAN(lines[1]);
        account.setName(lines[0]);
        return account;
    }

    //TODO: Use log4j to print the Account toString to console

    //TODO: Use log4j to print the Account toString to an output.log file

    //TODO: Use log4j to print errors and warnings

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + account.getIBAN() + '\'' +
                ", name='" + account.getName() + '\'' +
                ", payments=[" + account.getPayments().stream().map(Payment::toString).collect(Collectors.joining(",")) + "]}";
    }

}
