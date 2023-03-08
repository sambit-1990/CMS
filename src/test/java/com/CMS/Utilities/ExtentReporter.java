package com.CMS.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports getReportObject()
	{
	String filepath = System.getProperty("user.dir") + "\\ExtentReports\\index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
	reporter.config().setDocumentTitle("CMS Test Report");
	reporter.config().setReportName("Automation Test Report");
	reporter.config().setTheme(Theme.STANDARD);
	
	ExtentReports rep = new ExtentReports();
	rep.attachReporter(reporter);
	rep.setSystemInfo("Tester's Name","Sambit Bhattacharyya");
	rep.setSystemInfo("Sprint", "PI1.IT1");
	return rep;
	
	}
}
