package org.ampion.auspost.browserfactory.managers.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class LocalFirefoxManager {
    public static @NotNull WebDriver getDriver() {
        setFirefox();
        return getFirefox();
    }

    private static void setFirefox() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Contract(" -> new")
    private static @NotNull WebDriver getFirefox() {
        return new FirefoxDriver();
    }
}