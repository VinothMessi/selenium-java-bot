package org.ampion.auspost.browserfactory.managers.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class LocalChromeManager {
    public static @NotNull WebDriver getDriver() {
        setChrome();
        return getChrome();
    }

    private static void setChrome() {
        WebDriverManager.chromedriver().setup();
    }

    @Contract(" -> new")
    private static @NotNull WebDriver getChrome() {
        return new ChromeDriver();
    }
}