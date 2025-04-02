package com.nob.app.core.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Utility class for accessing the Spring application context.
 * <p>
 * This class implements {@link ApplicationContextAware} to store the application context
 * statically, allowing retrieval of Spring-managed beans from anywhere in the application.
 * </p>
 * @author Your Name
 * @version 1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Sets the application context. This method is automatically called by Spring.
     *
     * @param applicationContext the application context to be stored
     * @throws BeansException if an error occurs during context initialization
     */
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextHolder.applicationContext == null) {
            ApplicationContextHolder.applicationContext = applicationContext;
        }

    }


    /**
     * Retrieves a bean by its type.
     *
     * @param clazz the class type of the bean
     * @param <T>   the type of the bean
     * @return an instance of the requested bean
     * @throws NoSuchBeanDefinitionException if no bean of the given type is found
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }


    /**
     * Retrieves a bean by its qualifier and type.
     *
     * @param qualifier the name of the bean
     * @param clazz     the class type of the bean
     * @param <T>       the type of the bean
     * @return an instance of the requested bean
     * @throws NoSuchBeanDefinitionException if no bean with the given name and type is found
     */
    public static <T> T getBean(String qualifier, Class<T> clazz) {
        return applicationContext.getBean(qualifier, clazz);
    }
}
