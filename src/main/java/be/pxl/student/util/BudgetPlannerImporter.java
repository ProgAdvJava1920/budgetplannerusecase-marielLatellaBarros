package be.pxl.student.util;

import java.nio.file.Path;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private Path path;

    public BudgetPlannerImporter(Path pathFileToRead) { //src/main/resources/account_payments.csv
        this.path = pathFileToRead;
    }


    //TODO: Read lines from file.
        //TODO: Per line create an Account and a Payment object
        //TODO: Add to list

    //TODO: Use log4j to print the Account toString to console

    //TODO: Use log4j to print the Account toString to an output.log file

    //TODO: Use log4j to print errors and warnings

}
