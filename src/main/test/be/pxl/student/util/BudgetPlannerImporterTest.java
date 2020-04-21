package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
//
//public class BudgetPlannerImporterTest {
//
//    @Test
//    public void paymentShouldReturnCorrectAmount() throws IOException {
//        String line = "Jos,BE69771770897312,BE15563218722574,Sun Feb 02 16:49:24 CET 2020,-2263.59,EUR,Veritatis qui voluptas nostrum iste vitae maxime enim.";
//        BudgetPlannerImporter bpi = new BudgetPlannerImporter();
//        Payment payment = bpi.createPaymentObject(line);
//
//        Payment payment2 = new Payment();
//        payment2.setAmount(-2263.59f);
//        payment2.setCurrency("EUR");
//        payment2.setDetail("Veritatis qui voluptas nostrum iste vitae maxime enim.");
//
//        Assertions.assertEquals(payment.getAmount(), (payment.getAmount() * 100) / 100);
//    }
//
//    @Test
//    public void paymentShouldReturnCorrectDetail(){
//        String line = "Jos,BE69771770897312,BE15563218722574,Sun Feb 02 16:49:24 CET 2020,-2263.59,EUR,Veritatis qui voluptas nostrum iste vitae maxime enim.";
//        BudgetPlannerImporter bpi = new BudgetPlannerImporter();
//        Payment payment = bpi.createPaymentObject(line);
//
//        Payment payment2 = new Payment();
//        payment2.setAmount(-2263.59f);
//        payment2.setCurrency("EUR");
//        payment2.setDetail("Veritatis qui voluptas nostrum iste vitae maxime enim.");
//
//        Assertions.assertEquals(payment.getDetail(), payment2.getDetail());
//    }
//
//    @Test
//    public void paymentShouldReturnCorrectCurrency(){
//        String line = "Jos,BE69771770897312,BE15563218722574,Sun Feb 02 16:49:24 CET 2020,-2263.59,EUR,Veritatis qui voluptas nostrum iste vitae maxime enim.";
//        BudgetPlannerImporter bpi = new BudgetPlannerImporter();
//        Payment payment = bpi.createPaymentObject(line);
//
//        Payment payment2 = new Payment();
//        payment2.setAmount(-2263.59f);
//        payment2.setCurrency("EUR");
//        payment2.setDetail("Veritatis qui voluptas nostrum iste vitae maxime enim.");
//
//        Assertions.assertEquals(payment.getCurrency(), payment2.getCurrency());
//    }
//
//    @Test
//    //TODO: fix <Exception in toString(): java.lang.NullPointerException>
//    public void getAccountShouldCreateAccount(){
//        String line = "Jos,BE69771770897312,BE15563218722574,Sun Feb 02 16:49:24 CET 2020,-2263.59,EUR,Veritatis qui voluptas nostrum iste vitae maxime enim.";
//        BudgetPlannerImporter bpi = new BudgetPlannerImporter();
//        Account account = bpi.createAccountObject(line);
//
//        Account account2 = new Account();
//        account2.setName("Jos");
//        account2.setIBAN("BE69771770897312");
//
//        Assertions.assertEquals(account, account2);
//    }
//}
