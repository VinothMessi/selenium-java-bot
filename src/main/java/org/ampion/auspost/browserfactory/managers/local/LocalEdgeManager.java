package org.ampion.auspost.browserfactory.managers.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public final class LocalEdgeManager {
    public static @NotNull WebDriver getDriver() {
        setEdge();
        return getEdge();
    }

    private static void setEdge() {
        WebDriverManager.edgedriver().setup();
    }

    @Contract(" -> new")
    private static @NotNull WebDriver getEdge() {
        return new EdgeDriver();
    }
}