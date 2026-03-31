package com.automationexercise.front.pom.factory.pages;

import org.openqa.selenium.support.FindBy;

import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.EMAIL_INPUT_CSS;
import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.GET_IN_TOUCH_TITLE_XPATH;
import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.MESSAGE_INPUT_CSS;
import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.NAME_INPUT_CSS;
import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.SUBJECT_INPUT_CSS;
import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.SUBMIT_BUTTON_CSS;
import static com.automationexercise.front.pom.factory.config.TestConstants.Selectors.ContactUs.UPLOAD_INPUT_CSS;
import com.automationexercise.front.pom.factory.model.ContactUsFormData;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ContactUsPage extends PageObject {

    @FindBy(xpath = GET_IN_TOUCH_TITLE_XPATH)
    private WebElementFacade getInTouchTitle;
    @FindBy(css = NAME_INPUT_CSS)
    private WebElementFacade nameInput;

    @FindBy(css = EMAIL_INPUT_CSS)
    private WebElementFacade emailInput;

    @FindBy(css = SUBJECT_INPUT_CSS)
    private WebElementFacade subjectInput;

    @FindBy(css = MESSAGE_INPUT_CSS)
    private WebElementFacade messageInput;

    @FindBy(css = UPLOAD_INPUT_CSS)
    private WebElementFacade uploadInput;

    @FindBy(css = SUBMIT_BUTTON_CSS)
    private WebElementFacade submitButton;

    public void shouldBeVisible() {
        getInTouchTitle.waitUntilVisible();
    }

    public void fillForm(ContactUsFormData formData) {
        nameInput.waitUntilEnabled().type(formData.getName());
        emailInput.waitUntilEnabled().type(formData.getEmail());
        subjectInput.waitUntilEnabled().type(formData.getSubject());
        messageInput.waitUntilEnabled().type(formData.getMessage());
    }

    public void uploadFile(String fileAbsolutePath) {
        uploadInput.waitUntilPresent().sendKeys(fileAbsolutePath);
    }

    public void submitForm() {
        submitButton.waitUntilClickable().click();
    }

    public void acceptConfirmationAlert() {
        getDriver().switchTo().alert().accept();
    }
}
