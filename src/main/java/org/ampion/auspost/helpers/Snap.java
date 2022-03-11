package org.ampion.auspost.helpers;

import org.ampion.auspost.enums.Capture;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.function.BiConsumer;

import static org.ampion.auspost.Constants.*;

public class Snap {
    private final WebDriver lDriver;

    public Snap(WebDriver driver) {
        this.lDriver = driver;
    }

    private static File sourceImage;
    private static File destinationImage;

    private static final EnumMap<Capture, BiConsumer<String, WebDriver>> map1 = new EnumMap<>(Capture.class);
    private static final BiConsumer<String, WebDriver> viewable_area = (fileName, d) -> {
        sourceImage = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
        destinationImage = new File(SNAPS + fileName);
        try {
            FileHandler.copy(sourceImage, destinationImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    private static final BiConsumer<String, WebDriver> full_page = (fileName, d) -> {
        sourceImage = ((FirefoxDriver) d).getFullPageScreenshotAs(OutputType.FILE);
        destinationImage = new File(SNAPS + fileName);
        try {
            FileHandler.copy(sourceImage, destinationImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    static {
        map1.put(Capture.VIEWABLE_AREA, viewable_area);
        map1.put(Capture.FULL_PAGE, full_page);
    }

    public void shot(@NotNull String action, String name) {
        map1.get(Capture.valueOf(action.toUpperCase())).accept(name, this.lDriver);
    }

    private static final EnumMap<Capture, BiConsumer<WebElement, String>> map2 = new EnumMap<>(Capture.class);
    private static final BiConsumer<WebElement, String> element = (ele, fileName) -> {
        sourceImage = ele.getScreenshotAs(OutputType.FILE);
        destinationImage = new File(SNAPS + fileName);
        try {
            FileHandler.copy(sourceImage, destinationImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    static {
        map2.put(Capture.ELEMENT, element);
    }

    public void shot(@NotNull String action, String name, WebElement element) {
        map2.get(Capture.valueOf(action.toUpperCase())).accept(element, name);
    }
}