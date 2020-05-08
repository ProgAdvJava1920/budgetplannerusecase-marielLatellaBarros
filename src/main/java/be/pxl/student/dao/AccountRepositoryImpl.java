//package be.pxl.student.dao;
//
//import be.pxl.student.entity.AccountOld;
//
//import javax.persistence.*;
//import java.util.List;
//
//public class AccountRepositoryImpl implements AccountRepository {
//    private EntityManagerFactory entityManagerFactory = null;
//    private EntityManager entityManager = null;
//
//    @Override
//    public AccountOld create(AccountOld account) throws AccountException {
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
//
//    @Override
//    public AccountOld getById(int id) throws AccountException {
//        try {
//            entityManagerFactory = Persistence.createEntityManagerFactory("course");
//            entityManager = entityManagerFactory.createEntityManager();
//
//            TypedQuery<AccountOld> query = entityManager.createNamedQuery("getById", AccountOld.class);
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
//
//    @Override
//    public AccountOld getByName(String name) throws AccountException {
//        return null;
//    }
//
//    @Override
//    public List<AccountOld> getAll() throws AccountException {
//        return null;
//    }
//
//    @Override
//    public boolean update(AccountOld account) throws AccountException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(long id) throws AccountException {
//        return false;
//    }
//}
