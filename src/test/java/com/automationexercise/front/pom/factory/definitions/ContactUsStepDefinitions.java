package com.automationexercise.front.pom.factory.definitions;

import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.AND_FILL_DATASET;
import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.AND_RETURN_HOME;
import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.AND_SUBMIT_ACCEPT;
import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.AND_UPLOAD_DATASET;
import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.GIVEN_HOME;
import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.THEN_SUCCESS_MATCH;
import static com.automationexercise.front.pom.factory.config.TestConstants.Steps.WHEN_OPEN_CONTACT;
import com.automationexercise.front.pom.factory.steps.ContactUsFlowSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class ContactUsStepDefinitions {

    @Steps
    ContactUsFlowSteps contactUsFlowSteps;

    @Given(GIVEN_HOME)
    public void theUserIsOnTheAutomationExerciseHomePage() {
        contactUsFlowSteps.openHome();
    }

    @When(WHEN_OPEN_CONTACT)
    public void theUserOpensContactUsForm() {
        contactUsFlowSteps.openContactForm();
    }

    @And(AND_FILL_DATASET)
    public void theUserFillsTheContactFormUsingDataset(String datasetKey) {
        contactUsFlowSteps.fillContactForm(datasetKey);
    }

    @And(AND_UPLOAD_DATASET)
    public void theUserUploadsFileUsingDataset(String datasetKey) {
        contactUsFlowSteps.uploadAttachment(datasetKey);
    }

    @And(AND_SUBMIT_ACCEPT)
    public void theUserSubmitsTheContactFormAndAcceptsTheConfirmationAlert() {
        contactUsFlowSteps.submitAndAcceptAlert();
    }

    @Then(THEN_SUCCESS_MATCH)
    public void theSuccessMessageShouldMatchDataset(String datasetKey) {
        contactUsFlowSteps.validateSuccessMessage(datasetKey);
    }

    @And(AND_RETURN_HOME)
    public void theUserShouldBeAbleToReturnToTheHomePage() {
        contactUsFlowSteps.returnHome();
    }
}
