package be.pxl.student.util;

import be.pxl.student.entities.Account;
import be.pxl.student.entities.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BudgetPlannerMapper {

    private static Logger logger = LogManager.getLogger(BudgetPlannerMapper.class);

    //TODO: Move to property file
    public static final String DATE_PATTERN = "EEE MMM d MM:mm:ss z yyyy";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    public static final int CSV_ITEM_COUNT = 7;

    private Map<String, Account> accountMap = new HashMap<>();

    public List<Account> mapAccounts(List<String> accountLines) {

        for (String accountLine :
                accountLines) {
            try {
                Account account = mapDataLineToAccount(accountLine);
                accountMap.putIfAbsent(account.getIBAN(), account);
            }catch (BudgetPlannerException | ParseException e){
                logger.warn(String.format("Could not parse line [%s]", accountLine));
            }
        }
        return new ArrayList<>(accountMap.values());
}

    public Account mapDataLineToAccount(String accountLine) throws BudgetPlannerException, ParseException {
        String[] items = accountLine.split(",");

        if (items.length != CSV_ITEM_COUNT){
            throw new BudgetPlannerException("Invalid line, expected 7 items");
        }

        String name = items[0];
        String iban = items[1];

        Account account = accountMap.getOrDefault(iban, new Account(name, iban));
        Payment payment = mapItemsToPayment(items);
        account.getPayments().add(payment);

        return account;
    }

    public Date convertToDate(String dateString) throws ParseException {
        //"EEE MMM d MM:mm:ss z yyyy"
        return DATE_FORMAT.parse(dateString);
    }

    public String convertDateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public Payment mapItemsToPayment(String[] items) throws ParseException {
        return new Payment(
                //TODO: item 2 is actually the counterAccount => create counter account
                //TODO: remove IBAN from entity
                items[2],                   //IBAN
                convertToDate(items[3]),    //Transaction date
                Float.parseFloat(items[4]), // amount
                items[5],                   // currency
                items[6]                    // detail
        );
    }
}

