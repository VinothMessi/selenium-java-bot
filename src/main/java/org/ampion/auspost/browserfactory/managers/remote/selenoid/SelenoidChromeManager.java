package org.ampion.auspost.browserfactory.managers.remote.selenoid;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class SelenoidChromeManager {
    private SelenoidChromeManager() {
    }

    public static @NotNull WebDriver getDriver(@NotNull String version, URL hubUrl) {
        return getChrome(version, hubUrl);
    }

    private static @NotNull WebDriver getChrome(@NotNull String browserVersion, URL url) {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "chrome");
        options.setCapability("version", browserVersion);
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        return new RemoteWebDriver(url, options);
    }
}