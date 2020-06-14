package be.pxl.student.jpa;

import be.pxl.student.DAO;
import be.pxl.student.entities.Account;
import be.pxl.student.exceptions.AccountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTest {
    DAO<Account, AccountException> repository;
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        repository = new AccountRepositoryImpl(entityManager);
        entityManagerFactory =  Persistence.createEntityManagerFactory("budgetplanner_test");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
        entityManagerFactory.close();

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
        String updateName = "otherName";

        Account account = repository.getById(1);
        account.setName(updateName);
        repository.update(account);
        entityManager.clear();

        assertEquals(updateName,repository.getById(1).getName());
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