package com.rest.utils;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class ReportRuntimeListener {
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest feature;
	public static ExtentTest scenario, step;
	public static String testDetail;
	private static ReportRuntimeListener reportRuntimeListener;



	public ReportRuntimeListener getReportInstance() throws Throwable {
		if (reportRuntimeListener == null) {
			reportEngine();
			reportRuntimeListener = new ReportRuntimeListener();
			return reportRuntimeListener;
		}
		return reportRuntimeListener;
	}



	public ExtentReports reportEngine() throws Throwable {
		extent = new ExtentReports();
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String timestampString = formatter.format(timestamp);
		String ReportName = System.getProperty("user.dir") + "//Extentreport//traveltax_"+timestampString+".html" ;
		reporter = new ExtentHtmlReporter(ReportName);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("ReportName","Traveltotax API" );
		return extent;
	}

	public void reportCreateFeature(String title) {
		feature = extent.createTest(Feature.class, title);
	}

	public void reportCreateScenario(String Scenario) {
		scenario = feature.createNode(Scenario.class, Scenario);
	}


	public void createStep(String keyword, String stepName) throws ClassNotFoundException {
		switch (keyword) {
		case "Given":
			step = scenario.createNode(new GherkinKeyword("Given"), stepName);
			break;
		case "And":
			step = scenario.createNode(new GherkinKeyword("And"), stepName);
			break;
		case "When":
			step = scenario.createNode(new GherkinKeyword("When"), stepName);
			break;
		case "Then":
			step = scenario.createNode(new GherkinKeyword("Then"), stepName);
			break;
		default:
			step = scenario.createNode(new GherkinKeyword("And"), stepName);
		}
	}


	public void reportStepPass(String Details) {
		try {
			step.pass(Details);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void reportStepFail(String Details){
		try {
			step.fail(Details);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void reportCooldown() {
		extent.flush();
	}

	public void reportStepExpection(Exception e) {
		step.fail(e);
	}

	public void reportFeatureLog(String info) {
		feature.log(Status.INFO, info);
	}

	public void reportFeatureLogPass(String info) {
		feature.log(Status.PASS, info);
	}

	public void reportFeatureLogFail(String info) {
		feature.log(Status.FAIL, info);
	}

	public void reportFeatureLogFatal(String Info) {
		feature.log(Status.WARNING, Info);
	}

}

