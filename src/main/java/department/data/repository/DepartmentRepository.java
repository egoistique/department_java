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
public class DepartmentRepository implements Repository {

    @Inject
    CompanyDataStore store ;

    @Override
    public void save(Department department){
        store.departments.add(department);
    }
    @Override
    public void delete(String departmentName){
        store.departments.removeIf(department -> department.getName().equals(departmentName));
    }
    @Override
    public Department findByName(String departmentName){
        for (Department department : store.departments) {
            if (department.getName().equals(departmentName)) {
                return department;
            }
        }
        return null;
    }
    public List<Department> findAll(){
        return store.departments;
    }

    public void addEmployee(Employee employee, String depName){
        Department department = findByName(depName);

        if (department != null) {
            department.addEmployee(employee);
        } else {
            System.out.println("Отдел с именем " + depName + " не найден.");
        }
    }

    public void deleteEmployee(String employeeName, String dep){
        for (Department d : store.departments) {
            if(d.getName().equals(dep)) {
                for (Employee e : d.getEmployees()) {
                    if (e.getName().equals(employeeName)) {
                        d.removeEmp(e);
                        return;
                    }
                }
            }
        }
    }
}

