package com.automationexercise.front.pom.factory.pages;

import org.openqa.selenium.support.FindBy;

import com.automationexercise.front.pom.factory.config.TestConstants;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LandingPage extends PageObject {

    @FindBy(css = TestConstants.Selectors.Landing.CONTACT_US_BUTTON_CSS)
    private WebElementFacade contactUsButton;

    @FindBy(css = TestConstants.Selectors.Landing.LOGO_IMAGE_CSS)
    private WebElementFacade logoImage;

    public void openHomePage() {
        getDriver().get(TestConstants.Url.BASE_URL);
    }

    public void shouldBeVisible() {
        logoImage.waitUntilVisible();
    }

    public void openContactUs() {
        contactUsButton.waitUntilClickable().click();
    }
}
