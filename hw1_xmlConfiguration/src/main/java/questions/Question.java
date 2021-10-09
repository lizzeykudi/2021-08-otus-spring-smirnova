package questions;

import java.util.List;

public class Question {
    String question;
    List<String> answers;
    String correctAnswer;

    public Question(List<String> record) {
        this.question = record.get(0);
        this.answers = record.subList(1, record.size() - 1);
        this.correctAnswer = record.get(record.size() - 1);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(String answer) {
        if (correctAnswer.equals(answer)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String questionString = question + "\n";
        for (int i = 0; i < answers.size(); i++) {
            questionString += (i + 1 + ")" + answers.get(i) + "\n");
        }
        return questionString;
    }
}
