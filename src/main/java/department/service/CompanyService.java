package department.service;


import department.data.dao.DepartmentDAO;
import department.data.dao.EmployeeDAO;
import department.di.annotation.Inject;
import department.di.annotation.Injectable;
import department.data.model.Department;
import department.data.model.Employee;
import department.data.repository.DepartmentRepository;
import department.data.repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Класс бизнес логики
 */

@Injectable
public class CompanyService {
//    @Inject
//    private DepartmentRepository departmentRepository ;
//    @Inject
//    private EmployeeRepository employeeRepository ;


    @Inject
    private DepartmentDAO departmentRepository;
    @Inject
    private EmployeeDAO employeeRepository;

    public void addDepartment(Department department) throws SQLException {
        departmentRepository.createDepartment(department);
    }

    public void deleteDepartment(int departmentName) throws SQLException {
        departmentRepository.deleteDepartment(departmentName);
    }

    public void editDepartment(Department department) {

    }

    public void addEmployeeToDepartment(String departmentName, int depId, Employee employee) throws SQLException {
        Department department = departmentRepository.getDepartmentByName(departmentName);

        if (department == null) {
            departmentRepository.createDepartment(new Department(depId, departmentName));
        }

        employeeRepository.createEmployee(employee);
        departmentRepository.addEmployeeToDepartment(employee, departmentName);
    }

    public void removeEmployee(int empId) throws SQLException {
        employeeRepository.deleteEmployee(empId);
        //departmentRepository.deleteEmployee(empName, departmentName);
    }

    public void editEmployee(Employee employee) {

    }

    public List<Department> getAllDepartments() throws SQLException {
        return departmentRepository.getAllDepartments();
    }

    public List<Employee> getEmployeesInDepartment(String departmentName) {
        return null;
    }

    public double getTotalSalaryInDepartment(String departmentName) {
        return 0;
    }

}

