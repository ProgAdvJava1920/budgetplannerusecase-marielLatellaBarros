package be.pxl.student.entity;

import Entities.Account;
import exceptions.AccountException;
import jdbc.AccountRepositoryImpl;
import jdbc.DAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountRepositoryImplTest {
    private static final String DB_URL = "jdbc:h2:mem:test;MODE=MySQL;INIT=RUNSCRIPT FROM 'classpath:' "; //TODO

    DAOManager manager;
    AccountRepositoryImpl repository;

    @BeforeEach
    void setUp(){
        manager = new DAOManager(DB_URL);
        repository = new AccountRepositoryImpl(manager);
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
    void it_should_return_2_items() throws AccountException {
        List<Account> accounts = repository.getAll();
        assertEquals(2, accounts.size());
    }

    @Test
    void it_should_return_account_with_id_1() throws AccountException{
        Account account = repository.getById(1);
        Account expected = new Account(1, " dummyIBAN", " dummyName");

        assertEquals(expected, account);
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
