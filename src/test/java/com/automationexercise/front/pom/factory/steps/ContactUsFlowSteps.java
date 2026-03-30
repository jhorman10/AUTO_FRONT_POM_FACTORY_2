package com.automationexercise.front.pom.factory.steps;

import java.io.File;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

import static com.automationexercise.front.pom.factory.config.TestConstants.Errors.RESOURCE_NOT_FOUND;
import com.automationexercise.front.pom.factory.data.ContactUsDataProvider;
import com.automationexercise.front.pom.factory.model.ContactUsFormData;
import com.automationexercise.front.pom.factory.pages.ContactConfirmationPage;
import com.automationexercise.front.pom.factory.pages.ContactUsPage;
import com.automationexercise.front.pom.factory.pages.LandingPage;

import net.serenitybdd.annotations.Step;

public class ContactUsFlowSteps {

    private LandingPage landingPage;
    private ContactUsPage contactUsPage;
    private ContactConfirmationPage contactConfirmationPage;
    private final ContactUsDataProvider dataProvider = new ContactUsDataProvider();

    @Step
    public void openHome() {
        landingPage.openHomePage();
        landingPage.shouldBeVisible();
    }

    @Step
    public void openContactForm() {
        landingPage.openContactUs();
        contactUsPage.shouldBeVisible();
    }

    @Step
    public void fillContactForm(String datasetKey) {
        ContactUsFormData formData = dataProvider.getFormData(datasetKey);
        contactUsPage.fillForm(formData);
    }

    @Step
    public void uploadAttachment(String datasetKey) {
        ContactUsFormData formData = dataProvider.getFormData(datasetKey);
        contactUsPage.uploadFile(resolveResourcePath(formData.getFileName()));
    }

    @Step
    public void submitAndAcceptAlert() {
        contactUsPage.submitForm();
        contactUsPage.acceptConfirmationAlert();
    }

    @Step
    public void validateSuccessMessage(String datasetKey) {
        String expectedMessage = dataProvider.expectedSuccessMessage(datasetKey);
        assertThat(contactConfirmationPage.successMessage()).isEqualTo(expectedMessage);
    }

    @Step
    public void returnHome() {
        contactConfirmationPage.returnToHome();
        contactConfirmationPage.homeIsVisible();
    }

    private String resolveResourcePath(String resourcePath) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(resourcePath);
        if (resource == null) {
            throw new IllegalStateException(RESOURCE_NOT_FOUND + resourcePath);
        }
        return new File(resource.getFile()).getAbsolutePath();
    }
}
