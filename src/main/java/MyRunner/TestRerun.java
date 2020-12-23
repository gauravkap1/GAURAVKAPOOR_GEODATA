package MyRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"@target/rerun.txt"},
		glue={"com.stepdefinitions"},
		
		plugin= {"pretty","html:target1/cucumber-html-report",
				"json:target1/cucumber.json"},
		monochrome=true
		
		
		)
public class TestRerun {
	
}
