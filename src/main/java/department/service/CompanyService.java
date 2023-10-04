package department.service;


import department.annotation.Inject;
import department.annotation.Injectable;
import department.data.model.Department;
import department.data.model.Employee;
import department.data.repository.DepartmentRepository;
import department.data.repository.EmployeeRepository;
import department.factory.BeanFactory;

import java.util.List;

/**
 * Класс бизнес логики
 */

@Injectable
public class CompanyService {
    @Inject
    private DepartmentRepository departmentRepository ;
    @Inject
    private EmployeeRepository employeeRepository ;


    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(String departmentName) {
        departmentRepository.delete(departmentName);
    }

    public void editDepartment(Department department) {

    }

    public void addEmployeeToDepartment(String departmentName, Employee employee) {
        Department department = departmentRepository.findByName(departmentName);

        if (department == null) {
            departmentRepository.save(new Department(departmentName));
        }

        employeeRepository.save(employee);
        departmentRepository.addEmployee(employee, departmentName);
    }

    public void removeEmployee(String departmentName, String empName) {
        employeeRepository.delete(empName);
        departmentRepository.deleteEmployee(empName, departmentName);
    }

    public void editEmployee(Employee employee) {

    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Employee> getEmployeesInDepartment(String departmentName) {
        return null;
    }

    public double getTotalSalaryInDepartment(String departmentName) {
        return 0;
    }

}

