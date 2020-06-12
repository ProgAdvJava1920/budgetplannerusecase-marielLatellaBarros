package jpa;

import Entities.Account;
import be.pxl.student.DAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountImpl implements DAO {
    private static final Logger LOGGER = LogManager.getLogger(AccountImpl.class);

    private EntityManager entityManager;

    public AccountImpl(EntityManager em) {
        this.entityManager = em;
    }


    public Account getByNameOrIban(Account account) {
        Account foundAccount = null;
        try {
            TypedQuery<Account> query = entityManager.createNamedQuery("getByNameOrIban", Account.class);
            query.setParameter("name", account.getName());
            query.setParameter("iban", account.getIBAN());

            foundAccount = query.getSingleResult();

        } catch (NoResultException e) {
            LOGGER.trace("No account found");
        } catch (IllegalArgumentException e) {
            LOGGER.trace("Wrong query name or parameter given to query or result cannot be inserted into variable type");
        }
        return foundAccount;
    }

    @Override
    public Object create(Object o) throws Exception {
        return null;
    }

    @Override
    public Object getById(int id) throws Exception {
        return null;
    }

    @Override
    public Object getByNameOrIban(Object o) throws Exception {
        return null;
    }

    @Override
    public List getAll() throws Exception {
        return null;
    }

    @Override
    public Account getByIban(String iban) {
        TypedQuery<Account> query = entityManager.createNamedQuery("getByIBAN", Account.class);
        query.setParameter("iban", iban);

        Account result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.trace("No account found");
        }
        return result;
    }

    @Override
    public boolean update(Object o) throws Exception {
        return false;
    }

    @Override
    public Object delete(Object o) throws Exception {
        return null;
    }

    public boolean updateAccount(Account account) {
        try {
            Account accountToUpdate = getByIban(account.getIBAN());

            EntityTransaction tx =entityManager.getTransaction();
            tx.begin();
            accountToUpdate.setName(account.getName());
            tx.commit();

            LOGGER.info("rows updated");
            return true;
        } catch (IllegalStateException e) {
            LOGGER.trace("Error with entityManager and getTransaction: " +
                    "if called for a Java Persistence query language SELECT statement or for a criteria query");
        }
        return false;
    }

    public Account create(Account account) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(account);
        tx.commit();

        return account;
    }
}
