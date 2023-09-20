package org.cucumbertask.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "org/cucumbertask/resources/features/onliner.feature",
        glue = "org.cucumbertask",
        tags = "@all",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class CucumberRunnerTest {
}
