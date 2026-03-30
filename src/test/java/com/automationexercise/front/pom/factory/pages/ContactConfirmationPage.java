package com.automationexercise.front.pom.factory.pages;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ContactConfirmationPage extends PageObject {

    private static final String SUCCESS_ALERT_CSS = "div.status.alert.alert-success";
    private static final String HOME_BUTTON_CSS = "#form-section a[href='/']";
    private static final String LOGO_IMAGE_CSS = "div.logo a[href='/'] img";

    @FindBy(css = SUCCESS_ALERT_CSS)
    private WebElementFacade successAlert;

    @FindBy(css = HOME_BUTTON_CSS)
    private WebElementFacade homeButton;

    @FindBy(css = LOGO_IMAGE_CSS)
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
