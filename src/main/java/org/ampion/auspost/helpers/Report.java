package org.ampion.auspost.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static org.ampion.auspost.Constants.*;

public class Report {
    private static ExtentReports extentReports;

    private Report() {
    }

    public static void initialize(String location, String name) {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(location + name);
        sparkReporter
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{
                        ViewName.DASHBOARD,
                        ViewName.TEST,
                        ViewName.AUTHOR,
                        ViewName.DEVICE,
                        ViewName.EXCEPTION,
                        ViewName.LOG
                })
                .apply();
        try {
            sparkReporter
                    .loadXMLConfig(new File(ROOT + RESOURCES + "extent-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load the configuration xml file for Extent Report");
        }
        extentReports.attachReporter(sparkReporter);
    }

    public static void write() {
        extentReports.flush();
    }

    public static ExtentTest createTestCase(String name) {
        return extentReports.createTest(name);
    }

    public static ExtentTest createTestCase(String name, String description) {
        return extentReports.createTest(name, description);
    }

    public static ExtentTest createTestCase(String name, @NotNull ExtentTest parentTestCase) {
        return parentTestCase.createNode(name);
    }

    public static ExtentTest createTestCase(String name, String description, @NotNull ExtentTest parentTestCase) {
        return parentTestCase.createNode(name, description);
    }

    public static void pass(@NotNull ExtentTest testCase, String message) {
        testCase.pass(message);
    }

    public static void pass(@NotNull ExtentTest testCase, String message, Media media) {
        testCase.pass(message, media);
    }

    public static void pass(@NotNull ExtentTest testCase, Markup markup) {
        testCase.pass(markup);
    }

    public static void fail(@NotNull ExtentTest testCase, String message) {
        testCase.fail(message);
    }

    public static void fail(@NotNull ExtentTest testCase, String message, Media media) {
        testCase.fail(message, media);
    }

    public static void fail(@NotNull ExtentTest testCase, Markup markup) {
        testCase.fail(markup);
    }

    public static void skip(@NotNull ExtentTest testCase, String message) {
        testCase.skip(message);
    }

    public static void skip(@NotNull ExtentTest testCase, String message, Media media) {
        testCase.skip(message, media);
    }

    public static void skip(@NotNull ExtentTest testCase, Markup markup) {
        testCase.skip(markup);
    }

    public static void info(@NotNull ExtentTest testCase, String message) {
        testCase.info(message);
    }

    public static void info(@NotNull ExtentTest testCase, String message, Media media) {
        testCase.info(message, media);
    }

    public static void info(@NotNull ExtentTest testCase, Markup markup) {
        testCase.info(markup);
    }
}