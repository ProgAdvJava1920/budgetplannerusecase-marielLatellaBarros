package be.pxl.student.jpa;

import be.pxl.student.entities.Account;
import be.pxl.student.DAO;
import be.pxl.student.exceptions.AccountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountRepositoryImpl implements DAO<Account, AccountException> {
    private static final Logger LOGGER = LogManager.getLogger(AccountRepositoryImpl.class);

    private EntityManager entityManager;

    public AccountRepositoryImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Account create(Account account) throws AccountException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(account);
        tx.commit();

        return account;
    }

    @Override
    public Account getById(int id) throws AccountException {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> getAll() throws AccountException {
        TypedQuery<Account> query = entityManager.createNamedQuery("findAll", Account.class);
        return query.getResultList();
    }

    @Override
    public boolean update(Account account) throws AccountException {
        throw new AccountException(" not yet implemented");
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

    @Override
    public Account delete(Account account) throws AccountException {
        throw new AccountException(" not yet implemented");
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
