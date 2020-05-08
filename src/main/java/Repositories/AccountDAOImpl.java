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
    public Account saveAccount(Account account) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(account);
        tx.commit();

        return account;
    }

    @Override
    public Account getByName(String accountHolder) {
        TypedQuery<Account> query = entityManager.createNamedQuery("getByName", Account.class);
        query.setParameter("name", accountHolder);

        Account result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.trace("No account found");
        }

        return result;
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
}
