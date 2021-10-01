package ru.otus.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.testApplication.TestApplication;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.otus.spring")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestApplication testApplication = (TestApplication) context.getBean("testApplication");
        testApplication.game();

        context.close();
    }
}
