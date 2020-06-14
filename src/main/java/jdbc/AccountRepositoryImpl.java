package jdbc;

import entities.Account;
import be.pxl.student.DAO;
import exceptions.AccountException;
import exceptions.AccountNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handmatig, zelf queries schrijven, zelf objecten halen. Use a one time connection per method.
 */
//public class AccountRepositoryImpl implements DAO
public class AccountRepositoryImpl implements DAO<Account, AccountException> {

    private static Logger logger = LogManager.getLogger(AccountRepositoryImpl.class);

    public static final String SELECT_BY_ID = "select * from Account where id = ?";
    private static final String SELECT_ALL = " select * from Account";

    //private EntityManagerFactory entityManagerFactory = null;
    //private EntityManager entityManager = null;

    private DAOManager daoManager;

    public AccountRepositoryImpl(DAOManager daoManager) {
        this.daoManager = daoManager;
    }

    public Account getById(int id) throws AccountException {
        try (PreparedStatement preparedStatement = daoManager.getConnection().prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()){
                return new Account(
                        resultSet.getInt("id"),
                        resultSet.getString(" IBAN"),
                        resultSet.getString(" name")
                );
            } else {
                throw new AccountNotFoundException(String.format(" Account with id [%d] not found.", id));
            }
        } catch (SQLException e) {
            throw new AccountException(String.format(" Exception while retrieving account with id: [%d].", id));
        }
    }

    @Override
    public List<Account> getAll() throws AccountException {
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement preparedStatement = daoManager.getConnection().prepareStatement(SELECT_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accounts.add(
                        new Account(
                                resultSet.getInt(" id"),
                                resultSet.getString(" IBAN"),
                                resultSet.getString("name")
                        )
                );
            }
        } catch (SQLException e) {
            throw new AccountException(" Error retrieving accounts", e);
        }
        return accounts;
    }

    public Account create(Account account) throws AccountException{
        try(PreparedStatement preparedStatement = daoManager.getConnection().prepareStatement(
                "insert into account (' IBAN', ' name') values(?, ?)")){

//            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(1, account.getIBAN());
            preparedStatement.setString(2, account.getName());
            int result = preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.first()){
                int accountId = generatedKeys.getInt(1);
                account.setId(accountId);
            }
            if (result == 1){
                return account;
            }
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback(e);
            throw new AccountException(" Error creating account", e);
        }
        throw new AccountException(" Could not create account");
    }


//        public Account create2(Account account) throws AccountException {
//        try {
//            entityManagerFactory = Persistence.createEntityManagerFactory("course");
//            entityManager = entityManagerFactory.createEntityManager();
//
//            EntityTransaction transaction = entityManager.getTransaction();
//            transaction.begin();
//            entityManager.persist(account);
//            transaction.commit();
//            entityManager.clear();
//
//            return account;
//
//        } catch (IllegalArgumentException e) {
//            throw new AccountException("illegal or inapropiate argument passed: ", e);
//        } catch (IllegalStateException e) {
//            throw new AccountException("getTransaction: If invoked on a JTA entity manager: ", e);
//        } catch (TransactionRequiredException e){
//            throw new AccountException ("flush: If there is no transaction or " +
//                    "if the entity manager has not been joined to the current transaction: ", e);
//        }catch (PersistenceException e) {
//            throw new AccountException("flush or commit: If it fails: ", e);
//        }
//        finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//            if (entityManagerFactory != null) {
//                entityManagerFactory.close();
//            }
//        }
//    }



//    public Account getById2(int id) throws AccountException {
//        try {
//            entityManagerFactory = Persistence.createEntityManagerFactory("course");
//            entityManager = entityManagerFactory.createEntityManager();
//
//            TypedQuery<Account> query = entityManager.createNamedQuery("getById", Account.class);
//            query.setParameter("id", id);
//
//            return query.getSingleResult();
//
//        } catch (Exception e) {
//            throw new AccountException("Something went wrong", e);
//        } finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//            if (entityManagerFactory != null) {
//                entityManagerFactory.close();
//            }
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
