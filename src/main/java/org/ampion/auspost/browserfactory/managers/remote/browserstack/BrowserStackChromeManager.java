package org.ampion.auspost.browserfactory.managers.remote.browserstack;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

public final class BrowserStackChromeManager {
    private BrowserStackChromeManager() {
    }

    public static @NotNull WebDriver getDriver(Map<String, String> capabilities, URL hubUrl) {
        return getChrome(capabilities, hubUrl);
    }

    private static @NotNull WebDriver getChrome(@NotNull Map<String, String> capabilities, URL url) {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browser", "chrome");
        options.setCapability("browser_version", capabilities.get("browser_version"));
        options.setCapability("os", capabilities.get("os"));
        options.setCapability("os_version", capabilities.get("os_version"));
        return new RemoteWebDriver(url, options);
    }
}