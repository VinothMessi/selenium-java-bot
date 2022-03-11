package org.ampion.auspost.browserfactory.suppliers.remoteplatforms;

import org.ampion.auspost.browserfactory.managers.remote.seleniumgrid.SeleniumGridChromeManager;
import org.ampion.auspost.browserfactory.managers.remote.seleniumgrid.SeleniumGridEdgeManager;
import org.ampion.auspost.browserfactory.managers.remote.seleniumgrid.SeleniumGridFirefoxManager;
import org.ampion.auspost.enums.BrowserTypes;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.EnumMap;
import java.util.function.Function;

public final class SeleniumGridBrowsers {
    private SeleniumGridBrowsers() {
    }

    private static final EnumMap<BrowserTypes, Function<URL, WebDriver>> map = new EnumMap<>(BrowserTypes.class);

    static {
        map.put(BrowserTypes.CHROME, SeleniumGridChromeManager::getDriver);
        map.put(BrowserTypes.EDGE, SeleniumGridEdgeManager::getDriver);
        map.put(BrowserTypes.FIREFOX, SeleniumGridFirefoxManager::getDriver);
    }

    public static WebDriver launch(@NotNull String browserName, URL hubUrl) {
        return map.get(BrowserTypes.valueOf(browserName.toUpperCase())).apply(hubUrl);
    }
}