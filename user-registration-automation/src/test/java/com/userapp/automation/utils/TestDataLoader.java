package com.userapp.automation.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TestDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);
    private static final String TEST_DATA_FILE = "src/test/resources/testdata/testdata.csv";

    public static Map<String, String> loadTestData(String testCaseId) {
        Map<String, String> testData = new HashMap<>();

        try (FileReader reader = new FileReader(TEST_DATA_FILE)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            Iterable<CSVRecord> records = csvFormat.parse(reader);

            for (CSVRecord record : records) {
                if (record.get("TestCaseId").equals(testCaseId)) {
                    for (String header : record.getHeader().values()) {
                        testData.put(header, record.get(header));
                    }
                    logger.info("Test data loaded for test case: {}", testCaseId);
                    return testData;
                }
            }
            logger.warn("Test data not found for test case: {}", testCaseId);
        } catch (IOException e) {
            logger.error("Failed to load test data from CSV", e);
        }

        return testData;
    }

    public static List<Map<String, String>> loadAllTestData() {
        List<Map<String, String>> allTestData = new ArrayList<>();

        try (FileReader reader = new FileReader(TEST_DATA_FILE)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            Iterable<CSVRecord> records = csvFormat.parse(reader);

            for (CSVRecord record : records) {
                Map<String, String> testData = new HashMap<>();
                for (String header : record.getHeader().values()) {
                    testData.put(header, record.get(header));
                }
                allTestData.add(testData);
            }
            logger.info("Loaded {} test data records from CSV", allTestData.size());
        } catch (IOException e) {
            logger.error("Failed to load test data from CSV", e);
        }

        return allTestData;
    }

}
