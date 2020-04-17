package be.pxl.student.dao;

import be.pxl.student.entity.Payment;

import java.util.List;

public class PaymentDAOImp implements PaymentDAO{
    @Override
    public Payment create(Payment payment) throws PaymentException {
        return null;
    }

    @Override
    public Payment getByIBAN(int IBAN) throws AccountException {
        return null;
    }

    @Override
    public List<Payment> getAll() throws AccountException {
        return null;
    }

    @Override
    public void update(Payment payment) throws AccountException {

    }

    @Override
    public boolean delete(long id) throws AccountException {
        return false;
    }
}
