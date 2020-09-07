package ru.otus.domain;

import java.util.HashMap;
import java.util.Map;

public class StudentAnswers {

    private final Map<Question, String> answers = new HashMap<>();

    public String getAnswer(Question question) {
        return answers.get(question);
    }

    public void putAnswer(Question question, String answer) {
        answers.put(question, answer);
    }

}
