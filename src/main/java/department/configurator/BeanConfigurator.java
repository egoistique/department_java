package department.configurator;

public interface BeanConfigurator {

    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);
}
