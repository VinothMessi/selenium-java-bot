package org.ampion.auspost.browserfactory.suppliers;

import org.ampion.auspost.browserfactory.suppliers.remoteplatforms.BrowserStackBrowsers;
import org.ampion.auspost.browserfactory.suppliers.remoteplatforms.SeleniumGridBrowsers;
import org.ampion.auspost.browserfactory.suppliers.remoteplatforms.SelenoidBrowsers;
import org.ampion.auspost.enums.RemotePlatforms;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

public final class RemoteBrowsers {
    private RemoteBrowsers() {
    }

    private static final EnumMap<RemotePlatforms, BiFunction<String, URL, WebDriver>> map1 = new EnumMap<>(RemotePlatforms.class);

    static {
        map1.put(RemotePlatforms.SELENIUM_GRID, SeleniumGridBrowsers::launch);
    }

    public static WebDriver launch(@NotNull String remotePlatform, String browserName, URL hubUrl) {
        return map1.get(RemotePlatforms.valueOf(remotePlatform.toUpperCase())).apply(browserName, hubUrl);
    }

    private static final EnumMap<RemotePlatforms, TriFunction<String, String, URL, WebDriver>> map2 = new EnumMap<>(RemotePlatforms.class);

    static {
        map2.put(RemotePlatforms.SELENOID, SelenoidBrowsers::launch);
    }

    public static WebDriver launch(@NotNull String remotePlatform, String browserName, String browserVersion, URL hubUrl) {
        return map2.get(RemotePlatforms.valueOf(remotePlatform.toUpperCase())).apply(browserName, browserVersion, hubUrl);
    }

    private static final EnumMap<RemotePlatforms, TriFunction<String, Map<String, String>, URL, WebDriver>> map3 = new EnumMap<>(RemotePlatforms.class);

    static {
        map3.put(RemotePlatforms.BROWSER_STACK, BrowserStackBrowsers::launch);
    }

    public static WebDriver launch(@NotNull String remotePlatform, String browserName, Map<String, String> capabilities, URL hubUrl) {
        return map3.get(RemotePlatforms.valueOf(remotePlatform.toUpperCase())).apply(browserName, capabilities, hubUrl);
    }
}