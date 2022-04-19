package StepsDefinition;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features="src/test/resources/FeatureFiles/UsersSkills/",
glue={"StepsDefinition"},
plugin = {"pretty", "html:target/htmlReport.html",
   			         "json:target/jsonReport.json",
    	              "junit:target/xmlReport.xml"},
monochrome=true
)


public class TestRunner{
	

}
