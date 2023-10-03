package department.factory;

import department.config.Configuration;
import department.config.JavaConfiguration;
import department.configurator.BeanConfigurator;
import department.configurator.JavaBeanConfigurator;
import lombok.SneakyThrows;

public class BeanFactory {

    private static final BeanFactory BEAN_FACTORY = new BeanFactory();
    private final Configuration configuration;
    private final BeanConfigurator beanConfigurator;

    private BeanFactory(){
        this.configuration = new JavaConfiguration();
        this.beanConfigurator = new JavaBeanConfigurator(configuration.getPackageToScan());
    }

    public static BeanFactory getInstance(){
        return BEAN_FACTORY;
    }

    @SneakyThrows
    public <T> T getBean(Class<T> clazz){
        Class<? extends T> implementationClass = clazz;
        if(implementationClass.isInterface()){
            implementationClass = beanConfigurator.getImplementationClass(implementationClass);
        }
        return implementationClass.getDeclaredConstructor().newInstance();
    }
}
