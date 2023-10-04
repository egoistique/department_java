package department.data.datastore;


import department.data.model.Department;
import department.data.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * класс хранилище всех отедлов и сотрудников
 */
public class CompanyDataStore {
    public List<Employee> employees = new ArrayList<>();
    public List<Department> departments = new ArrayList<>();

    public CompanyDataStore(){
        Employee e1 = new Employee("Вася", 20, 90000);
        Employee e2 = new Employee("Петя", 21, 60000);
        Employee e3 = new Employee("Маша", 22, 80000);
        Employee e4 = new Employee("Оля", 23, 95000);
        Employee e5 = new Employee("Вика", 24, 100000);


        Department d1 = new Department("Dep1" );
        Department d2 = new Department("Dep2" );
        Department d3 = new Department("Dep3" );

        d1.addEmployee(e1);
        d1.addEmployee(e4);
        d2.addEmployee(e2);
        d2.addEmployee(e5);
        d3.addEmployee(e3);

        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        departments.add(d1);
        departments.add(d2);
        departments.add(d3);
    }
}
