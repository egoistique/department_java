package department.data.repository;


import department.annotation.Inject;
import department.annotation.Injectable;
import department.data.datastore.CompanyDataStore;
import department.data.model.Department;
import department.data.model.Employee;

import java.util.List;

/**
 * Класс репозитория, отвечает за добавление, поиск и тд
 */
@Injectable
public class EmployeeRepository implements Repository{

    @Inject
    CompanyDataStore store;

    public void save(Employee employee){
        store.employees.add(employee);
    }

    @Override
    public void save(Department department) {

    }

    public void delete(String empName) {
        store.employees.removeIf(employee -> employee.getName().equals(empName));
    }

    @Override
    public Department findByName(String departmentName) {
        return null;
    }

    public Employee findById(int employeeId){
        return store.employees.get(employeeId);
    }

    public List<Employee> findAll(){
        return store.employees;
    }
}

