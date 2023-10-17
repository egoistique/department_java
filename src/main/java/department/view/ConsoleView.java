package department.view;

import department.di.annotation.Inject;
import department.di.annotation.Injectable;
import department.data.model.Department;
import department.data.model.Employee;
import department.di.factory.BeanFactory;
import department.service.CompanyService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Консольный интерфейс
 */

@Injectable
public class ConsoleView implements View {
//
//    //@Inject
//    private final CompanyService service = BeanFactory.getInstance().getBean(CompanyService.class);
    @Inject
    private  CompanyService service ;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() throws SQLException {
        while (true) {
            System.out.println("что вы хотите сделать? выберите цифру:");
            System.out.println("1. добавить сотрудника");
            System.out.println("2. удалить сотрудника");
            System.out.println("3. добавить отдел");
            System.out.println("4. удалить отдел");
            System.out.println("5. посмотреть все отделы");
            System.out.println("6. выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    addDepartment();
                    break;
                case 4:
                    deleteDepartment();
                    break;
                case 5:
                    viewDepartments();
                    break;
                case 6:
                    System.out.println("выход");
                    System.exit(0);
                default:
                    System.out.println("неправильный ввод");
            }
        }
    }
    @Override
    public void addEmployee() throws SQLException {
        System.out.println("Новый сотрудник.");
        System.out.print("id: ");
        int id = scanner.nextInt();
        System.out.print("имя: ");
        String name = scanner.next();
        System.out.print("возраст: ");
        int age = scanner.nextInt();
        System.out.print("зарплата: ");
        double salary = scanner.nextDouble();
        System.out.print("Название отедела: ");
        String depName = scanner.next();
        System.out.print("id отедела: ");
        int depId = scanner.nextInt();

        service.addEmployeeToDepartment(depName, depId, new Employee(id, name, age, salary, depId));
    }


    @Override
    public void deleteEmployee() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите id удаляемого сотрудника");
        int name = scanner.nextInt();
//        System.out.println("введите название отедела, в котором работает этот сотрудник:");
//        String nameDep = scanner.nextLine();
        service.removeEmployee(name);
    }

    @Override
    public void addDepartment() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("id: ");
        int id = scanner.nextInt();
        System.out.println("введите название отедела:");
        String departmentName = scanner.nextLine();
        service.addDepartment(new Department(id, departmentName));

    }

    @Override
    public void deleteDepartment() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите индекс удаляемого отедела:");
        int departmentInd = scanner.nextInt();
        service.deleteDepartment(departmentInd);
    }

    @Override
    public void viewDepartments() throws SQLException {
        List<Department> departments = service.getAllDepartments();

        for (Department department : departments) {
            String departmentName = department.getName();
            List<Employee> employees = department.getEmployees();
            int numberOfEmployees = department.getNumberOfEmployees();

            System.out.println("Название: " + departmentName + ", число сотрудников: " + numberOfEmployees);
            System.out.println("Сотрудники: ");
            for (Employee e : employees){
                System.out.println("Имя: " + e.getName() + "; Возраст: " + e.getAge() + "; Зарплата: " + e.getSalary());
            }
            System.out.println();
        }
    }
}
