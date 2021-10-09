package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileReaderTest {
    String testCsvFilePath;

    @BeforeEach
     void beforeAll() {
        testCsvFilePath = "src\\test\\resources\\CsvTest.csv";
    }

    @Test
    void test() {
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList(Arrays.asList("1", "1")));
        result.add(new ArrayList(Arrays.asList("2", "3")));
        assertEquals(result, new CsvFileReader().readCsvFile(testCsvFilePath));
    }
}