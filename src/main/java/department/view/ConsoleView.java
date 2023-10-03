package department.view;

import department.data.model.Department;
import department.data.model.Employee;
import department.factory.BeanFactory;
import department.service.CompanyService;

import java.util.List;
import java.util.Scanner;

/**
 * Консольный интерфейс
 */

public class ConsoleView implements View {

    private CompanyService service = BeanFactory.getInstance().getBean(CompanyService.class);

    private Scanner scanner = new Scanner(System.in);

//    public ConsoleView(DIContainer diContainer) {
//        diContainer.injectDependencies(this);
//    }
    @Override
    public void displayMenu() {
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
    public void addEmployee() {
        System.out.println("Новый сотрудник.");
        System.out.print("имя: ");
        String name = scanner.next();
        System.out.print("возраст: ");
        int age = scanner.nextInt();
        System.out.print("зарплата: ");
        double salary = scanner.nextDouble();
        System.out.print("название отедела: ");
        String depName = scanner.next();

        service.addEmployeeToDepartment(depName, new Employee(name, age, salary));
    }


    @Override
    public void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите имя удаляемого сотрудника");
        String name = scanner.nextLine();
        System.out.println("введите название отедела, в котором работает этот сотрудник:");
        String nameDep = scanner.nextLine();
        service.removeEmployee(nameDep, name);
    }

    @Override
    public void addDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите название отедела:");
        String departmentName = scanner.nextLine();
        service.addDepartment(new Department(departmentName));

    }

    @Override
    public void deleteDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите название удаляемого отедела:");
        String departmentName = scanner.nextLine();
        service.deleteDepartment(departmentName);
    }

    @Override
    public void viewDepartments() {
        List<Department> departments = service.getAllDepartments();

        for (Department department : departments) {
            String departmentName = department.getName();
            int numberOfEmployees = department.getNumberOfEmployees();

            System.out.println("Название: " + departmentName + ", число сотрудников: " + numberOfEmployees);
        }
    }
}
