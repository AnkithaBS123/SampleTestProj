import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:SearchFlight/SearchFlights.feature", glue="stepDefinition",format = { "pretty",
        "html:target/site/cucumber-pretty",
        "json:target/cucumber.json" })
public class TestRunner {

}