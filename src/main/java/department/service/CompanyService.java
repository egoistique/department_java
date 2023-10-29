package department.service;


import department.data.dao.DepartmentDAO;
import department.data.dao.EmployeeDAO;
import department.di.annotation.Inject;
import department.di.annotation.Injectable;
import department.data.model.Department;
import department.data.model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


   // @Inject
    private DepartmentDAO departmentRepository = new DepartmentDAO();
    //@Inject
    private EmployeeDAO employeeRepository = new EmployeeDAO();

    public CompanyService() throws SQLException {
    }

    public void addDepartment(String name) throws SQLException {
        departmentRepository.create(name);
    }

    public void deleteDepartment(int departmentName) throws SQLException {
        departmentRepository.delete(departmentName);
    }

    public void editDepartment(Department department) {

    }

    public void addEmployeeToDepartment(String departmentName, String name, int age, double salary) throws SQLException {
        Department department = departmentRepository.getByName(departmentName);
        int depId;
        if (department == null) {
            departmentRepository.create(departmentName);
            department = departmentRepository.getByName(departmentName);
        }
        depId = department.getId();


        employeeRepository.create(depId, name, age, salary);
    }

    public void removeEmployee(int empId) throws SQLException {
        employeeRepository.delete(empId);
        //departmentRepository.deleteEmployee(empName, departmentName);
    }

    public void editEmployee(Employee employee) {

    }

    public List<Department> getAllDepartments() throws SQLException {
        return departmentRepository.getAll();
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeRepository.getAll();
    }

    public List<Employee> getEmployeesFromDepartment(int depId) throws SQLException {
        return departmentRepository.getEmployeesFromDepartment(depId);
    }

    public List<Employee> getEmployeesInDepartment(String departmentName) {
        return null;
    }

    public double getTotalSalaryInDepartment(String departmentName) {
        return 0;
    }

}

