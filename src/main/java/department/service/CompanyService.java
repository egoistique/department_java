package department.service;


import department.data.model.Department;
import department.data.model.Employee;
import department.data.repository.DepartmentRepository;
import department.data.repository.EmployeeRepository;
import department.di.Inject;

import java.util.List;

/**
 * Класс бизнес логики
 */

public class CompanyService {
    @Inject
    private DepartmentRepository departmentRepository;
    @Inject
    private EmployeeRepository employeeRepository;

    public CompanyService() {

    }
//    public CompanyService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
//        this.departmentRepository = departmentRepository;
//        this.employeeRepository = employeeRepository;
//    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> getAllDeps(){
        return departmentRepository.findAll();
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

