package be.pxl.student.util;

import be.pxl.student.entities.Account;
import be.pxl.student.entities.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetPlannerMapperTest {
    private static final String testDataLine = "Jos,BE69771770897312,BE44421084390028," + "Mon Apr 06 06:52:00 CEST 2020,-3012.9,EUR,bla.";
    private BudgetPlannerMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new BudgetPlannerMapper();
    }

    @AfterEach
    void teatDown(){
    }

    @Test
    void it_should_convert_date_to_string_and_back_again(){

    }

    @Test
    void it_should_map_to_account_list_with_1_account(){

    }

    @Test
    void it_should_map_line_to_account_object() throws ParseException, BudgetPlannerException {
        Account expectedAccount = new Account("Jos","BE69771770897312");
        Account lineToAccount = mapper.mapDataLineToAccount(testDataLine);

        assertEquals(expectedAccount, lineToAccount);
    }

    @Test
    void it_should_return_non_empty_list(){

    }

    @Test
    //TODO: Fix test after removing IBAN
    void it_should_map_line_to_payment() throws ParseException{
        Payment expectedPayment = new Payment(
                "BE44421084390028",
                mapper.convertToDate("Mon Apr 06 06:52:00 CEST 2020"),
                -3012.9f,
                "EUR",
                "bla"
        );

        Payment actualPayment = mapper.mapItemsToPayment(testDataLine.split(","));

        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    void it_should_map_to_account_list_with_1_account_with_2_parameters() throws ParseException{
        String testDate = "Mon Apr 06 06:52:00 CEST 2020";
        Date date = mapper.convertToDate(testDate);
        String dateToString = mapper.convertDateToString(date);

        assertEquals(testDate, dateToString);
    }
}
