package ru.otus.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Question {

    private final String correctAnswer;
    private final String question;
    private final List<String> answers;

}
