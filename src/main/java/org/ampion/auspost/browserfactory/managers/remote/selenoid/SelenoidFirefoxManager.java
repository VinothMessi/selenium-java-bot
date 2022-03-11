package org.ampion.auspost.browserfactory.managers.remote.selenoid;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SelenoidFirefoxManager {
    private SelenoidFirefoxManager() {
    }

    public static @NotNull WebDriver getDriver(@NotNull String version, URL hubUrl) {
        return getFirefox(version, hubUrl);
    }

    private static @NotNull WebDriver getFirefox(@NotNull String browserVersion, URL url) {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browserName", "firefox");
        options.setCapability("version", browserVersion);
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        return new RemoteWebDriver(url, options);
    }
}