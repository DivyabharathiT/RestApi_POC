package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags= "@check",
		features = {"Features"}, 
		glue = {"com\\rest\\steps"},
		monochrome = true,	dryRun= false)
public class Runner extends AbstractTestNGCucumberTests{

}
