package org.ampion.auspost.browserfactory.managers.remote.seleniumgrid;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class SeleniumGridChromeManager {
    private SeleniumGridChromeManager() {
    }

    @Contract("_ -> new")
    public static @NotNull WebDriver getDriver(URL hubUrl) {
        return getChrome(hubUrl);
    }

    @Contract("_ -> new")
    private static @NotNull WebDriver getChrome(URL url) {
        return new RemoteWebDriver(url, new ChromeOptions());
    }
}