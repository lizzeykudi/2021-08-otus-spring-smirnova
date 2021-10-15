package csvQuestions;

import questions.Question;
import questions.Questions;
import util.CsvFileReader;

import java.util.ArrayList;
import java.util.List;

public class CsvQuestions implements Questions {

    private String fileName;
    private List<Question> questions;
    private CsvFileReader csvFileReader;

    private void generateQuestions() {
        questions = new ArrayList<>();
        List<List<String>> records = csvFileReader.readCsvFile(fileName);
        for (List<String> record : records) {
            questions.add(new Question(record));
        }
    }


    @Override
    public List<Question> getQuestions() {
        if (questions == null) {
            generateQuestions();
        }
        return questions;
    }

}
