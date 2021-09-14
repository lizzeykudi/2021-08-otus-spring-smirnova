package questions;

import util.CsvFileReader;

import java.util.ArrayList;
import java.util.List;

public class Questions {

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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCsvFileReader(CsvFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    public List<Question> getQuestions() {
        if (questions == null) {
            generateQuestions();
        }
        return questions;
    }

}
