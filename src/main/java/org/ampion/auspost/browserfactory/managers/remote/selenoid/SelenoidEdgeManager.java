package org.ampion.auspost.browserfactory.managers.remote.selenoid;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class SelenoidEdgeManager {
    private SelenoidEdgeManager() {
    }

    public static @NotNull WebDriver getDriver(@NotNull String version, URL hubUrl) {
        return getEdge(version, hubUrl);
    }

    private static @NotNull WebDriver getEdge(@NotNull String browserVersion, URL url) {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("browserName", "edge");
        options.setCapability("version", browserVersion);
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        return new RemoteWebDriver(url, options);
    }
}