package org.ampion.auspost.browserfactory.managers.remote.seleniumgrid;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class SeleniumGridEdgeManager {
    private SeleniumGridEdgeManager() {
    }

    @Contract("_ -> new")
    public static @NotNull WebDriver getDriver(URL hubUrl) {
        return getEdge(hubUrl);
    }

    @Contract("_ -> new")
    private static @NotNull WebDriver getEdge(URL url) {
        return new RemoteWebDriver(url, new EdgeOptions());
    }
}