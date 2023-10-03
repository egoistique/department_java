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


}
