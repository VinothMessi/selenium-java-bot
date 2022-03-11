package org.ampion.auspost.browserfactory.suppliers.remoteplatforms;

import org.ampion.auspost.browserfactory.managers.remote.selenoid.SelenoidChromeManager;
import org.ampion.auspost.browserfactory.managers.remote.selenoid.SelenoidEdgeManager;
import org.ampion.auspost.browserfactory.managers.remote.selenoid.SelenoidFirefoxManager;
import org.ampion.auspost.enums.BrowserTypes;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

public final class SelenoidBrowsers {
    private SelenoidBrowsers() {
    }

    private static final EnumMap<BrowserTypes, BiFunction<String, URL, WebDriver>> map = new EnumMap<>(BrowserTypes.class);

    static {
        map.put(BrowserTypes.CHROME, SelenoidChromeManager::getDriver);
        map.put(BrowserTypes.EDGE, SelenoidEdgeManager::getDriver);
        map.put(BrowserTypes.FIREFOX, SelenoidFirefoxManager::getDriver);
    }

    public static WebDriver launch(@NotNull String browserName, String browserVersion, URL hubUrl) {
        return map.get(BrowserTypes.valueOf(browserName.toUpperCase())).apply(browserVersion, hubUrl);
    }
}