package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;

@RequiredArgsConstructor
@Service
public class IoStreamServiceImpl implements IoStreamService {

    private final PrintStream printStream;

    @Override
    public void outputString(String str) {
        printStream.print(str);
    }

}
