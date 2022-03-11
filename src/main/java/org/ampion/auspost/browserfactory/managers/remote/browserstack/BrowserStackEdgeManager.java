package org.ampion.auspost.browserfactory.managers.remote.browserstack;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

public final class BrowserStackEdgeManager {
    private BrowserStackEdgeManager() {
    }

    public static @NotNull WebDriver getDriver(@NotNull Map<String, String> capabilities, URL hubUrl) {
        return getEdge(capabilities, hubUrl);
    }

    private static @NotNull WebDriver getEdge(@NotNull Map<String, String> capabilities, URL url) {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("browser", "edge");
        options.setCapability("browser_version", capabilities.get("browser_version"));
        options.setCapability("os", capabilities.get("os"));
        options.setCapability("os_version", capabilities.get("os_version"));
        return new RemoteWebDriver(url, options);
    }
}