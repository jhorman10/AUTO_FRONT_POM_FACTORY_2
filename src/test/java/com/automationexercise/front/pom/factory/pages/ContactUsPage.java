package com.automationexercise.front.pom.factory.pages;

import org.openqa.selenium.support.FindBy;

import com.automationexercise.front.pom.factory.model.ContactUsFormData;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ContactUsPage extends PageObject {

    private static final String GET_IN_TOUCH_TITLE_XPATH = "//h2[contains(@class,'title') and contains(translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'GET IN TOUCH')]";
    private static final String NAME_INPUT_CSS = "input[data-qa='name']";
    private static final String EMAIL_INPUT_CSS = "input[data-qa='email']";
    private static final String SUBJECT_INPUT_CSS = "input[data-qa='subject']";
    private static final String MESSAGE_INPUT_CSS = "textarea[data-qa='message']";
    private static final String UPLOAD_INPUT_CSS = "input[name='upload_file']";
    private static final String SUBMIT_BUTTON_CSS = "input[data-qa='submit-button']";

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
