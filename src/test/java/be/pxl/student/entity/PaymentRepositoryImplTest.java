package be.pxl.student.entity;

import Entities.Account;
import exceptions.AccountException;
import jdbc.AccountRepositoryImpl;
import jdbc.DAOManager;
import jdbc.PaymentRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaymentRepositoryImplTest {
    private static final String DB_URL = "jdbc:h2:mem:test;MODE=MySQL;INIT=RUNSCRIPT FROM 'classpath:BudgetPlannerTest.sql'";

    DAOManager manager;
    PaymentRepositoryImpl repository;

    @BeforeEach
    void setUp(){
        manager = new DAOManager(DB_URL);
        //repository = new PaymentRepositoryImpl(manager);
    }

    @AfterEach
    void teatDown(){
        manager.close();
    }


    @Test
    void create(){
        fail("not yet implemented");
    }

    @Test
    void getById(){
        fail("not yet implemented");
    }

    @Test
    void getAll(){
        fail("not yet implemented");
    }

    @Test
    void update(){
        fail("not yet implemented");
    }

    @Test
    void delete(){
        fail("not yet implemented");
    }
}
