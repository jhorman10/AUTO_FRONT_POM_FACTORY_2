package com.automationexercise.front.pom.factory.config;

public final class TestConstants {

    private TestConstants() {
    }

    public static final class Url {
        public static final String BASE_URL = "https://automationexercise.com";

        private Url() {
        }
    }

    public static final class Data {
        public static final String DATA_FILE = "test-data/contact-us-data.properties";
        public static final String KEY_NAME = "name";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_SUBJECT = "subject";
        public static final String KEY_MESSAGE = "message";
        public static final String KEY_FILE_PATH = "filePath";
        public static final String KEY_EXPECTED_SUCCESS_MESSAGE = "expectedSuccessMessage";
        public static final String KEY_SEPARATOR = ".";

        private Data() {
        }
    }

    public static final class Paths {
        public static final String FEATURE_CONTACT_US = "src/test/resources/features/contact_us/contact_us_form.feature";
        public static final String GLUE_PACKAGE = "com.automationexercise.front.pom.factory.definitions";

        private Paths() {
        }
    }

    public static final class Errors {
        public static final String DATA_FILE_NOT_FOUND = "Data file not found: ";
        public static final String CANNOT_LOAD_DATA_FILE = "Cannot load data file: ";
        public static final String MISSING_VALUE_FOR_KEY = "Missing value for key: ";
        public static final String RESOURCE_NOT_FOUND = "Resource not found: ";

        private Errors() {
        }
    }

    public static final class Steps {
        public static final String GIVEN_HOME = "el usuario esta en la pagina de inicio de Automation Exercise";
        public static final String WHEN_OPEN_CONTACT = "el usuario abre el formulario de contacto";
        public static final String AND_FILL_DATASET = "el usuario completa el formulario de contacto usando dataset {string}";
        public static final String AND_UPLOAD_DATASET = "el usuario adjunta archivo usando dataset {string}";
        public static final String AND_SUBMIT_ACCEPT = "el usuario envia el formulario de contacto y acepta la alerta de confirmacion";
        public static final String THEN_SUCCESS_MATCH = "el mensaje de exito debe coincidir con el dataset {string}";
        public static final String AND_RETURN_HOME = "el usuario debe poder volver a la pagina de inicio";

        private Steps() {
        }
    }
}