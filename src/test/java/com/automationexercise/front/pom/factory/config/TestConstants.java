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

        public static final String GIVEN_HOME = "la página de inicio de Automation Exercise está abierta";
        public static final String WHEN_OPEN_CONTACT = "el formulario de contacto está visible";
        public static final String AND_FILL_DATASET = "el formulario contiene los datos del dataset {string}";
        public static final String AND_UPLOAD_DATASET = "el formulario incluye un archivo adjunto definido en el dataset {string}";
        public static final String AND_SUBMIT_ACCEPT = "se procesa el envío del formulario de contacto";
        public static final String THEN_SUCCESS_MATCH = "se mostrará el mensaje de éxito definido en el dataset {string}";
        public static final String AND_RETURN_HOME = "la navegación regresa a la página de inicio";

        private Steps() {
        }
    }

    public static final class Selectors {

        public static final class ContactUs {

            public static final String GET_IN_TOUCH_TITLE_XPATH = "//h2[contains(@class,'title') and contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'GET IN TOUCH')]";
            public static final String NAME_INPUT_CSS = "input[data-qa='name']";
            public static final String EMAIL_INPUT_CSS = "input[data-qa='email']";
            public static final String SUBJECT_INPUT_CSS = "input[data-qa='subject']";
            public static final String MESSAGE_INPUT_CSS = "textarea[data-qa='message']";
            public static final String UPLOAD_INPUT_CSS = "input[name='upload_file']";
            public static final String SUBMIT_BUTTON_CSS = "input[data-qa='submit-button']";

            private ContactUs() {
            }
        }

        public static final class Landing {

            public static final String CONTACT_US_BUTTON_CSS = "a[href='/contact_us']";
            public static final String LOGO_IMAGE_CSS = "div.logo a[href='/'] img";

            private Landing() {
            }
        }

        public static final class ContactConfirmation {

            public static final String SUCCESS_ALERT_CSS = "div.status.alert.alert-success";
            public static final String HOME_BUTTON_CSS = "#form-section a[href='/']";
            public static final String LOGO_IMAGE_CSS = "div.logo a[href='/'] img";

            private ContactConfirmation() {
            }
        }

        private Selectors() {
        }
    }
}
