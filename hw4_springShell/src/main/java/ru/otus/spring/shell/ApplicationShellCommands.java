package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.testApplication.Application;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    @Autowired
    ApplicationContext context;

    @ShellMethod(value = "run test", key = {"run"})
    public void run() {
        Application application = (Application) context.getBean("testApplication");
        application.start();
    }
}
