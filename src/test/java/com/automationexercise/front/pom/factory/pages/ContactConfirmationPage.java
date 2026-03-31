package com.automationexercise.front.pom.factory.pages;

import org.openqa.selenium.support.FindBy;

import com.automationexercise.front.pom.factory.config.TestConstants;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ContactConfirmationPage extends PageObject {

    @FindBy(css = TestConstants.Selectors.ContactConfirmation.SUCCESS_ALERT_CSS)
    private WebElementFacade successAlert;

    @FindBy(css = TestConstants.Selectors.ContactConfirmation.HOME_BUTTON_CSS)
    private WebElementFacade homeButton;

    @FindBy(css = TestConstants.Selectors.ContactConfirmation.LOGO_IMAGE_CSS)
    private WebElementFacade logoImage;

    public String successMessage() {
        return successAlert.waitUntilVisible().getText().trim();
    }

    public void returnToHome() {
        homeButton.waitUntilClickable().click();
    }

    public void homeIsVisible() {
        logoImage.waitUntilVisible();
    }
}
