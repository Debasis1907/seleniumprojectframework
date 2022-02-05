package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {
}
