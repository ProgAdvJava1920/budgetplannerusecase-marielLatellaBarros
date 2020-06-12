package be.pxl.student;

import java.util.List;

public interface DAO<T, E extends Exception> {
    T create(T t) throws E;
    T getById(int id) throws E;
    List<T> getAll() throws E;
    boolean update(T t) throws E; //T update (T t)
    T delete(T t) throws E;
    T getByNameOrIban(T t) throws E;
    T getByIban(String iban) throws E;
}
