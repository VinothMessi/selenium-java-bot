package org.ampion.auspost.browserfactory.managers.remote.seleniumgrid;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SeleniumGridFirefoxManager {
    private SeleniumGridFirefoxManager() {
    }

    @Contract("_ -> new")
    public static @NotNull WebDriver getDriver(URL hubUrl) {
        return getFirefox(hubUrl);
    }

    @Contract("_ -> new")
    private static @NotNull WebDriver getFirefox(URL url) {
        return new RemoteWebDriver(url, new FirefoxOptions());
    }
}