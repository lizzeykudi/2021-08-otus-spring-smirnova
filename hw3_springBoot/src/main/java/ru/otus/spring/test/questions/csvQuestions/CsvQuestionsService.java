package ru.otus.spring.test.questions.csvQuestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.otus.spring.localization.LocalizationConfig;
import ru.otus.spring.test.questions.questions.Question;
import ru.otus.spring.test.questions.questions.QuestionsService;
import ru.otus.spring.util.reader.FileReader;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsvQuestionsService implements QuestionsService {
    LocalizationConfig localizationConfig;
    private List<Question> questions;
    private final FileReader fileReader;

    public CsvQuestionsService(LocalizationConfig localizationConfig, FileReader fileReader) {
        this.localizationConfig = localizationConfig;
        this.fileReader = fileReader;
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
        List<List<String>> records = fileReader.readFile(localizationConfig.getQuestionsFileName());
        for (List<String> record : records) {
            questions.add(new Question(record));
        }
    }

}
