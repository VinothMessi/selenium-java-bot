package org.ampion.auspost.browserfactory.managers.remote.browserstack;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

public class BrowserStackFirefoxManager {
    private BrowserStackFirefoxManager() {
    }

    public static @NotNull WebDriver getDriver(@NotNull Map<String, String> capabilities, URL hubUrl) {
        return getFirefox(capabilities, hubUrl);
    }

    private static @NotNull WebDriver getFirefox(@NotNull Map<String, String> capabilities, URL url) {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browser", "firefox");
        options.setCapability("browser_version", capabilities.get("browser_version"));
        options.setCapability("os", capabilities.get("os"));
        options.setCapability("os_version", capabilities.get("os_version"));
        return new RemoteWebDriver(url, options);
    }
}