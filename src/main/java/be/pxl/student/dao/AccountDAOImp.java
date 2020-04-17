package be.pxl.student.dao;

import be.pxl.student.entity.Account;

import java.util.List;

public class AccountDAOImp implements AccountDAO{
    @Override
    public Account create(Account account) throws AccountException {
        return null;
    }

    @Override
    public Account getById(int id) throws AccountException {
        return null;
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
