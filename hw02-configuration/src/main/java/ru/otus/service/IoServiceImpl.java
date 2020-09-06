package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IoServiceImpl implements IoService {

    @Value("#{ T(java.lang.System).out}")
    private PrintStream printStream;

    @Value("#{ T(java.lang.System).in}")
    private InputStream inputStream;

    private Scanner scanner;

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
