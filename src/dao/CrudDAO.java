package dao;

import entity.Program;

public interface CrudDAO<T,ID> extends SuperDAO {
    boolean add(T t);
    T get(ID id);
    boolean update(T t);
    boolean delete(ID id);
}
