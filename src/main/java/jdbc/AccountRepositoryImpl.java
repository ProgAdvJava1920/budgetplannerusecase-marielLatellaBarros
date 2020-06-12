package jdbc;

import Entities.Account;
import Repositories.DAO;
import exceptions.AccountException;
import exceptions.AccountNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//public class AccountRepositoryImpl implements DAO
public class AccountRepositoryImpl implements DAO<Account, AccountException> {

    private static Logger logger = LogManager.getLogger(AccountRepositoryImpl.class);

    public static final String SELECT_BY_ID = "select * from Account where id = ?";
    private static final String SELECT_ALL = " select * from Account";

    private DAOManager daoManager;

    public AccountRepositoryImpl(DAOManager daoManager) {
        this.daoManager = daoManager;
    }

    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    public Account create(Account account) throws AccountException {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("course");
            entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(account);
            transaction.commit();
            entityManager.clear();

            return account;

        } catch (IllegalArgumentException e) {
            throw new AccountException("illegal or inapropiate argument passed: ", e);
        } catch (IllegalStateException e) {
            throw new AccountException("getTransaction: If invoked on a JTA entity manager: ", e);
        } catch (TransactionRequiredException e){
            throw new AccountException ("flush: If there is no transaction or " +
                    "if the entity manager has not been joined to the current transaction: ", e);
        }catch (PersistenceException e) {
            throw new AccountException("flush or commit: If it fails: ", e);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }

    public Account getById2(int id) throws AccountException {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("course");
            entityManager = entityManagerFactory.createEntityManager();

            TypedQuery<Account> query = entityManager.createNamedQuery("getById", Account.class);
            query.setParameter("id", id);

            return query.getSingleResult();

        } catch (Exception e) {
            throw new AccountException("Something went wrong", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }

    @Override
//    public Account getById(int id) throws AccountException {
//        try { (PreparedStatement preparedStatement  = daoManager.getConnection().prepareStatement(SELECT_BY_ID);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.first()) {
//                return new Account (
//                    resultSet.getInt(" id"),
//                            resultSet.getString(" IBAN"),
//                            resultSet.getString(" name")
//                );
//            } else {
//                throw new AccountNotFoundException(String.format("Account with id [%d] not found.", id));
//            }

//        } catch (SQLException sqlException) {
//            throw new AccountException(String.format(" Exception while retrieving account with id: %d ", id));
//        }
//    }










    @Override
    public Account getByNameOrIban(Account account) throws AccountException {
        return null;
    }

    public Account getByName(String name) throws AccountException {
        return null;
    }

    @Override
    public List<Account> getAll() throws AccountException {
        return null;
    }

    @Override
    public Account getByIban(String iban) throws AccountException {
        return null;
    }

    @Override
    public boolean update(Account account) throws AccountException {
        return false;
    }

    @Override
    public Account delete(Account account) throws AccountException {
        return null;
    }

}
