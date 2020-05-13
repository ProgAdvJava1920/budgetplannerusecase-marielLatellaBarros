package Repositories;

import Entities.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountDAOImpl implements AccountDAO {
    private static final Logger LOGGER = LogManager.getLogger(AccountDAOImpl.class);

    private EntityManager entityManager;

    public AccountDAOImpl(EntityManager em) {
        this.entityManager = em;
    }



    @Override
    public Account getByNameOrIban(Account account) {
        Account foundAccount = null;
        try {
            TypedQuery<Account> query = entityManager.createNamedQuery("getByNameOrIban", Account.class);
            query.setParameter("name", account.getName());
            query.setParameter("iban", account.getIban());

            foundAccount = query.getSingleResult();

        } catch (NoResultException e) {
            LOGGER.trace("No account found");
        } catch (IllegalArgumentException e) {
            LOGGER.trace("Wrong query name or parameter given to query or result cannot be inserted into variable type");
        }
        return foundAccount;
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
    public boolean updateAccount(Account account) {
        try {
            Account accountToUpdate = getByIban(account.getIban());

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

    @Override
    public Account saveAccount(Account account) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(account);
        tx.commit();

        return account;
    }
}
