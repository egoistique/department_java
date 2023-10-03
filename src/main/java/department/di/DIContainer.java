package department.di;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DIContainer {
    private final Map<Class<?>, Object> dependencies = new HashMap<>();

    public void register(Class<?> clazz, Object instance) {
        dependencies.put(clazz, instance);
    }

    public void injectDependencies(Object target) {
        Class<?> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> fieldType = field.getType();
                Object dependency = dependencies.get(fieldType);

                if (dependency == null) {
                    throw new RuntimeException("Dependency not found for " + fieldType.getName());
                }

                field.setAccessible(true);

                try {
                    field.set(target, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency into " + targetClass.getName(), e);
                }
            }
        }
    }
}