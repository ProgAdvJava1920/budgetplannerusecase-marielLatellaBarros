package be.pxl.student.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {

    public static List<String> readCsvFile(Path path) throws BudgetPlannerException {
        List<String> accountLines = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            String line;

            while((line = reader.readLine()) != null) {
                accountLines.add(line);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return accountLines;
    }
}
