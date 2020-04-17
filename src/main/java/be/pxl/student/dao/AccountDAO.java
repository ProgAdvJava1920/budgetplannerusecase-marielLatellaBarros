package be.pxl.student.dao;

import be.pxl.student.entity.Account;

import java.util.List;

public interface AccountDAO {

    Account create(Account account) throws AccountException;

    Account getById(int id) throws AccountException;

    Account getByName(String name) throws AccountException;

    List<Account> getAll() throws AccountException;

    boolean update(Account account) throws AccountException;

    boolean delete(long id) throws AccountException;
}
