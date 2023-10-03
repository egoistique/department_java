package department.data.repository;


import department.data.datastore.CompanyDataStore;
import department.data.model.Employee;

import java.util.List;

/**
 * Класс репозитория, отвечает за добавление, поиск и тд
 */
public class EmployeeRepository {

    CompanyDataStore store = new CompanyDataStore();

    public void save(Employee employee){
        store.employees.add(employee);
    }
    public void delete(String empName) {
        store.employees.removeIf(employee -> employee.getName().equals(empName));
    }
    public Employee findById(int employeeId){
        return store.employees.get(employeeId);
    }
    public List<Employee> findAll(){
        return store.employees;
    }
}

