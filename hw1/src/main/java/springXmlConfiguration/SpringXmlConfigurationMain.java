package springXmlConfiguration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import questions.Questions;
import test.Test;


public class SpringXmlConfigurationMain {
    public static void main( String[] args )
    {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Questions questions = (Questions) applicationContext.getBean("questions");
        Test test = new Test(questions);
        test.test();

        applicationContext.close();
    }
}
