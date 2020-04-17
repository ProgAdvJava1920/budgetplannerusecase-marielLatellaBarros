package be.pxl.student.dao;

import be.pxl.student.entity.Payment;
import java.util.List;

public interface PaymentDAO {
    Payment create(Payment payment) throws PaymentException;

    Payment getByIBAN(int IBAN) throws AccountException;

    List<Payment> getAll() throws AccountException;

    void update(Payment payment) throws AccountException;

    boolean delete(long id) throws AccountException;
}

