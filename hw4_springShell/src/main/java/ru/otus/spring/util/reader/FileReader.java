package ru.otus.spring.util.reader;

import java.util.List;

public interface FileReader {

    public List<List<String>> readFile(String csvFilePath);

}
