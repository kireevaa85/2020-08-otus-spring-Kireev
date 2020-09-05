package ru.otus.service;

import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

@RequiredArgsConstructor
public class IoStreamServiceImpl implements IoStreamService {

    private final PrintStream printStream;

    @Override
    public void outputString(String str) {
        printStream.print(str);
    }

}
