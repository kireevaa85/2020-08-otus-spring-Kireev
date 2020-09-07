package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IoServiceImpl implements IoService {

    private final PrintStream printStream;
    private final InputStream inputStream;

    private Scanner scanner;

    public IoServiceImpl(@Value("#{ T(java.lang.System).out}") PrintStream printStream,
                         @Value("#{ T(java.lang.System).in}") InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
    }

    @Override
    public void outputString(String str) {
        printStream.print(str);
    }

    @Override
    public String inputString() {
        return getScanner().next();
    }

    private Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(inputStream);
        }
        return scanner;
    }

}
