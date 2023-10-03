package department;


import department.data.repository.DepartmentRepository;
import department.data.repository.EmployeeRepository;
import department.di.DIContainer;
import department.service.CompanyService;
import department.view.ConsoleView;

public class Main {

    public static void main(String[] args) {

//        DIContainer container = new DIContainer();
//
//        DepartmentRepository departmentRepository = new DepartmentRepository();
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        CompanyService companyService = new CompanyService(departmentRepository, employeeRepository);
//
//        container.register(DepartmentRepository.class, departmentRepository);
//        container.register(EmployeeRepository.class, employeeRepository);
//        container.register(CompanyService.class, companyService);

        ConsoleView view = new ConsoleView();

        view.displayMenu();
    }
}

