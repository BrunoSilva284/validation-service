package com.teste.challenge.validationservice.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/teste/challenge/validationservice/cucumber/validation.feature",
        plugin = {"pretty", "json:target/cucumber.json"})
public class CucumberIntegrationsTests {
}