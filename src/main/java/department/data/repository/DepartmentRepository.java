package department.data.repository;

import department.data.datastore.CompanyDataStore;
import department.data.model.Department;
import department.data.model.Employee;

import java.util.List;

/**
 * Класс репозитория, отвечает за добавление, поиск и тд
 */

public class DepartmentRepository implements Repository {

    CompanyDataStore store = new CompanyDataStore();

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
            // Если отдел не найден, можно сгенерировать сообщение об ошибке или выполнить другие действия
            System.out.println("Отдел с именем " + depName + " не найден.");
        }
    }
}

