package ru.otus.spring.test.questions.csvQuestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.otus.spring.test.questions.questions.Question;
import ru.otus.spring.test.questions.questions.QuestionsService;
import ru.otus.spring.util.reader.CsvFileReader;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsvQuestionsService implements QuestionsService {

    @Value("${questionsFilePath}")
    private String fileName;
    private List<Question> questions;
    private final CsvFileReader csvFileReader;

    @Autowired
    public CsvQuestionsService(CsvFileReader csvFileReader, Environment environment) {
        this.csvFileReader = csvFileReader;
    }

    @Override
    public List<Question> getQuestions() {
        if (questions == null) {
            generateQuestions();
        }
        return questions;
    }

    private void generateQuestions() {
        questions = new ArrayList<>();
        List<List<String>> records = csvFileReader.readCsvFile(fileName);
        for (List<String> record : records) {
            questions.add(new Question(record));
        }
    }

}
