package MavenTest.Cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "resources\\Features\\TeaTesting.feature")
public class TeaTestRunner {
	
}
