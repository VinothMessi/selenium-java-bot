package org.ampion.auspost.browserfactory.suppliers.remoteplatforms;

import org.ampion.auspost.browserfactory.managers.remote.browserstack.BrowserStackChromeManager;
import org.ampion.auspost.browserfactory.managers.remote.browserstack.BrowserStackEdgeManager;
import org.ampion.auspost.browserfactory.managers.remote.browserstack.BrowserStackFirefoxManager;
import org.ampion.auspost.enums.BrowserTypes;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

public final class BrowserStackBrowsers {
    private BrowserStackBrowsers() {
    }

    private static final EnumMap<BrowserTypes, BiFunction<Map<String, String>, URL, WebDriver>> map = new EnumMap<>(BrowserTypes.class);

    static {
        map.put(BrowserTypes.CHROME, BrowserStackChromeManager::getDriver);
        map.put(BrowserTypes.EDGE, BrowserStackEdgeManager::getDriver);
        map.put(BrowserTypes.FIREFOX, BrowserStackFirefoxManager::getDriver);
    }

    public static WebDriver launch(@NotNull String browserName, Map<String, String> capabilities, URL hubUrl) {
        return map.get(BrowserTypes.valueOf(browserName.toUpperCase())).apply(capabilities, hubUrl);
    }
}