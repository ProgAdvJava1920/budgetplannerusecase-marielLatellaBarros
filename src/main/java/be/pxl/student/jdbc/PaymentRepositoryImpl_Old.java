package be.pxl.student.jdbc;
//
//import be.pxl.student.entity.Payment;
//
//import javax.persistence.*;
//import java.util.List;
//
//public class PaymentRepositoryImpl implements PaymentRepository {
//    private EntityManagerFactory entityManagerFactory = null;
//    private EntityManager entityManager = null;
//
//    @Override
//    public List<Payment> create(List<Payment> payments) throws PaymentException {
//        try {
//            entityManagerFactory = Persistence.createEntityManagerFactory("course");
//            entityManager = entityManagerFactory.createEntityManager();
//
//            EntityTransaction transaction = entityManager.getTransaction();
//            transaction.begin();
//
//            payments.forEach(payment -> entityManager.persist(payment));
//            transaction.commit();
//            entityManager.clear();
//
//            TypedQuery<Payment> query = entityManager.createNamedQuery("getAllPayments", Payment.class);
//            List<Payment> result = query.getResultList();
//            result.forEach(System.out::println);
//            return result;
//
//        } catch (IllegalArgumentException e) {
//            throw new PaymentException("illegal or inapropiate argument passed: ", e);
//        } catch (IllegalStateException e) {
//            throw new PaymentException("getTransaction: If invoked on a JTA entity manager: ", e);
//        } catch (TransactionRequiredException e){
//            throw new PaymentException ("flush: If there is no transaction or " +
//                    "if the entity manager has not been joined to the current transaction: ", e);
//        }catch (PersistenceException e) {
//            throw new PaymentException("flush or commit: If it fails: ", e);
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
//    public Payment getByIBAN(int IBAN) throws AccountException {
//        return null;
//    }
//
//    @Override
//    public List<Payment> getAll() throws AccountException {
//        return null;
//    }
//
//    @Override
//    public void update(Payment payment) throws AccountException {
//
//    }
//
//    @Override
//    public boolean delete(long id) throws AccountException {
//        return false;
//    }
//}
