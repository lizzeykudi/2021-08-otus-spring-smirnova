package springXmlConfiguration;

import csvQuestions.CsvQuestions;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import questions.Questions;
import test.Test;


public class SpringXmlConfigurationMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Test test = (Test) applicationContext.getBean("test");
        test.test();

        applicationContext.close();
    }
}
