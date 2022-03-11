package org.ampion.auspost.helpers;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.ampion.auspost.enums.Capture;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.EnumMap;
import java.util.function.BiConsumer;

import static org.ampion.auspost.Constants.SNAPS;

public class Shutter {
    private final WebDriver lDriver;

    public Shutter(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final EnumMap<Capture, BiConsumer<String, WebDriver>> map1 = new EnumMap<>(Capture.class);
    private static final BiConsumer<String, WebDriver> viewable_area = (fileName, d) -> Shutterbug.shootPage(d, com.assertthat.selenium_shutterbug.core.Capture.VIEWPORT)
            .withTitle(new Date().toString())
            .withName(fileName)
            .save(SNAPS);
    private static final BiConsumer<String, WebDriver> full_page = (fileName, d) -> Shutterbug.shootPage(d, com.assertthat.selenium_shutterbug.core.Capture.FULL_SCROLL)
            .withTitle(new Date().toString())
            .withName(fileName)
            .save(SNAPS);

    static {
        map1.put(Capture.VIEWABLE_AREA, viewable_area);
        map1.put(Capture.FULL_PAGE, full_page);
    }

    public void shoot(@NotNull String action, String name) {
        map1.get(Capture.valueOf(action.toUpperCase())).accept(name, this.lDriver);
    }

    private static final EnumMap<Capture, TriConsumer<WebElement, String, WebDriver>> map2 = new EnumMap<>(Capture.class);
    private static final TriConsumer<WebElement, String, WebDriver> element = (e, fileName, d) -> Shutterbug.shootElement(d, e)
            .withTitle(new Date().toString())
            .withName(fileName)
            .save(SNAPS);

    static {
        map2.put(Capture.ELEMENT, element);
    }

    public void shoot(@NotNull String action, String name, WebElement element) {
        map2.get(Capture.valueOf(action.toUpperCase())).accept(element, name, this.lDriver);
    }
}