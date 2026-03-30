package com.automationexercise.front.pom.factory.runners;

import org.junit.runner.RunWith;

import static com.automationexercise.front.pom.factory.config.TestConstants.Paths.FEATURE_CONTACT_US;
import static com.automationexercise.front.pom.factory.config.TestConstants.Paths.GLUE_PACKAGE;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = FEATURE_CONTACT_US,
        glue = GLUE_PACKAGE,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ContactUsCucumberTest {
}
