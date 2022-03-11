package org.ampion.auspost.browserfactory.suppliers;

import org.ampion.auspost.browserfactory.managers.local.LocalChromeManager;
import org.ampion.auspost.browserfactory.managers.local.LocalEdgeManager;
import org.ampion.auspost.browserfactory.managers.local.LocalFirefoxManager;
import org.ampion.auspost.enums.BrowserTypes;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.function.Supplier;

public final class LocalBrowsers {
    private LocalBrowsers() {
    }

    private static final EnumMap<BrowserTypes, Supplier<WebDriver>> map = new EnumMap<>(BrowserTypes.class);

    static {
        map.put(BrowserTypes.CHROME, LocalChromeManager::getDriver);
        map.put(BrowserTypes.EDGE, LocalEdgeManager::getDriver);
        map.put(BrowserTypes.FIREFOX, LocalFirefoxManager::getDriver);
    }

    public static WebDriver launch(@NotNull String browserName) {
        return map.get(BrowserTypes.valueOf(browserName.toUpperCase())).get();
    }
}