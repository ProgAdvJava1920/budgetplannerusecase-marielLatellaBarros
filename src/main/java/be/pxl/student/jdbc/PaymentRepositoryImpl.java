package be.pxl.student.jdbc;

import be.pxl.student.entities.Payment;
import be.pxl.student.DAO;
import be.pxl.student.exceptions.PaymentException;

import java.util.List;

public class PaymentRepositoryImpl implements DAO<Payment, PaymentException> {

    @Override
    public Payment create(Payment payment) throws PaymentException {
        return null;
    }

    @Override
    public Payment getById(int id) throws PaymentException {
        return null;
    }

    @Override
    public List<Payment> getAll() throws PaymentException {
        return null;
    }

    @Override
    public Payment update(Payment payment) throws PaymentException {
        return null;
    }

    @Override
    public Payment delete(Payment payment) throws PaymentException {
        return null;
    }

    @Override
    public Payment getByNameOrIban(Payment payment) throws PaymentException {
        return null;
    }

    @Override
    public Payment getByIban(String iban) throws PaymentException {
        return null;
    }
}
