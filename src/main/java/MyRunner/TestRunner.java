package MyRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"src/main/java/features"},
		glue= {"com.stepdefinitions"},
		
         plugin= {"json:target/cucumber.json",
         "rerun:target/rerun.txt"},
		monochrome=true,
		strict=true,
		dryRun=false
		)
public class TestRunner {

}
