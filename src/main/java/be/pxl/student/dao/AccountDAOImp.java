package be.pxl.student.dao;

import be.pxl.student.entity.Account;

import javax.persistence.*;
import java.util.List;

public class AccountDAOImp implements AccountDAO {
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    @Override
    public Account create(Account account) throws AccountException {
        return null;
    }

    @Override
    public Account getById(int id) throws AccountException {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("course");
            entityManager = entityManagerFactory.createEntityManager();

            TypedQuery<Account> query = entityManager.createNamedQuery("getById", Account.class);
            query.setParameter("id", id);

            return query.getSingleResult();

        } catch (Exception e) {
            throw new AccountException(e);
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
    public Account getByName(String name) throws AccountException {
        return null;
    }

    @Override
    public List<Account> getAll() throws AccountException {
        return null;
    }

    @Override
    public boolean update(Account account) throws AccountException {
        return false;
    }

    @Override
    public boolean delete(long id) throws AccountException {
        return false;
    }
}
