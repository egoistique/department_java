package department.data.repository;

import department.data.model.Department;

import java.util.List;

public interface Repository {
    void save(Department department);

    void delete(String departmentName);

    Department findByName(String departmentName);

    //List<Department> findAll();
}
