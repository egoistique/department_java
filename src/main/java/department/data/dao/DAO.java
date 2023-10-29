package department.data.dao;

import department.data.model.Department;
import department.data.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void create(T entity) throws SQLException;

    T getById(int entityId) throws SQLException;

    T getByName(String entityName) throws SQLException;

    void delete(int entityId) throws SQLException;

    List<T> getAll() throws SQLException;
}

