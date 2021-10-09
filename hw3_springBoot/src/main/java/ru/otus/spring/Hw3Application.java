package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.spring.testApplication.Application;

import java.util.Locale;

@SpringBootApplication
public class Hw3Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Hw3Application.class, args);
        Application application = (Application) context.getBean("testApplication");
        application.start();
    }
}
