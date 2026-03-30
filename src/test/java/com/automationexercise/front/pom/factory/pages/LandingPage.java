package com.automationexercise.front.pom.factory.pages;

import org.openqa.selenium.support.FindBy;

import com.automationexercise.front.pom.factory.config.TestConstants;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl(TestConstants.Url.BASE_URL)
public class LandingPage extends PageObject {

    private static final String CONTACT_US_BUTTON_CSS = "a[href='/contact_us']";
    private static final String LOGO_IMAGE_CSS = "div.logo a[href='/'] img";

    @FindBy(css = CONTACT_US_BUTTON_CSS)
    private WebElementFacade contactUsButton;

    @FindBy(css = LOGO_IMAGE_CSS)
    private WebElementFacade logoImage;

    public void openHomePage() {
        open();
    }

    public void shouldBeVisible() {
        logoImage.waitUntilVisible();
    }

    public void openContactUs() {
        contactUsButton.waitUntilClickable().click();
    }
}
