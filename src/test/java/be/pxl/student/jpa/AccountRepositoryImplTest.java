package be.pxl.student.jpa;

import be.pxl.student.DAO;
import be.pxl.student.entities.Account;
import be.pxl.student.exceptions.AccountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTest {
    DAO<Account, AccountException> repository;

    @BeforeEach
    void setUp() {
        repository = new AccountRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
    }

    @Test
    void getById() throws AccountException{
        Account account = repository.getById(1);
        Account expected = new Account(1, " dummyIBAN", " dummyName");

        assertEquals(expected, account);
    }

    @Test
    void getAll() throws AccountException{
        List<Account> accounts = repository.getAll();
        assertEquals(2, accounts.size());
    }

    @Test
    void update() throws AccountException{
        fail("not yet implemented");
    }

    @Test
    void updateAccount() throws AccountException{
        fail("not yet implemented");
    }

    @Test
    void delete() throws AccountException{
        Account dummy = new Account(1, "dummyIBAN", "dummyName");
        repository.delete(dummy);

        assertNull(this.repository.getById(1));
    }

    @Test
    void getByNameOrIban() throws AccountException{
        fail("not yet implemented");
    }

    @Test
    void getByIban() throws AccountException{
        fail("not yet implemented");
    }
}