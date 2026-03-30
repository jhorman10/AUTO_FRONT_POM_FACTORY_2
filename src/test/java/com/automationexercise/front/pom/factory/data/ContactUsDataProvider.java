package com.automationexercise.front.pom.factory.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.automationexercise.front.pom.factory.config.TestConstants.Data.DATA_FILE;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_EMAIL;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_EXPECTED_SUCCESS_MESSAGE;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_FILE_PATH;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_MESSAGE;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_NAME;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_SEPARATOR;
import static com.automationexercise.front.pom.factory.config.TestConstants.Data.KEY_SUBJECT;
import static com.automationexercise.front.pom.factory.config.TestConstants.Errors.CANNOT_LOAD_DATA_FILE;
import static com.automationexercise.front.pom.factory.config.TestConstants.Errors.DATA_FILE_NOT_FOUND;
import static com.automationexercise.front.pom.factory.config.TestConstants.Errors.MISSING_VALUE_FOR_KEY;
import com.automationexercise.front.pom.factory.model.ContactUsFormData;

public class ContactUsDataProvider {
    private final Properties properties = new Properties();

    public ContactUsDataProvider() {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(DATA_FILE)) {
            if (input == null) {
                throw new IllegalStateException(DATA_FILE_NOT_FOUND + DATA_FILE);
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException(CANNOT_LOAD_DATA_FILE + DATA_FILE, exception);
        }
    }

    public ContactUsFormData getFormData(String datasetKey) {
        return new ContactUsFormData(
                getValue(datasetKey, KEY_NAME),
                getValue(datasetKey, KEY_EMAIL),
                getValue(datasetKey, KEY_SUBJECT),
                getValue(datasetKey, KEY_MESSAGE),
                getValue(datasetKey, KEY_FILE_PATH)
        );
    }

    public String expectedSuccessMessage(String datasetKey) {
        return getValue(datasetKey, KEY_EXPECTED_SUCCESS_MESSAGE);
    }

    private String getValue(String datasetKey, String key) {
        String fullKey = datasetKey + KEY_SEPARATOR + key;
        String result = properties.getProperty(fullKey);
        if (result == null || result.trim().isEmpty()) {
            throw new IllegalArgumentException(MISSING_VALUE_FOR_KEY + fullKey);
        }
        return result.trim();
    }
}
