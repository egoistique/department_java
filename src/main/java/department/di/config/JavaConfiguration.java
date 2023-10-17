package department.di.config;

import department.data.repository.DepartmentRepository;
import department.data.repository.EmployeeRepository;

import java.util.Map;

public class JavaConfiguration implements Configuration{
    @Override
    public String getPackageToScan() {
        return "department";
    }

    @Override
    public Map<Class, Class> getInterfaceToImplementations() {
        return Map.of(DepartmentRepository.class, EmployeeRepository.class);
    }
}
