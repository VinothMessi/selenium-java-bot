package org.ampion.auspost.helpers.browser;

import org.ampion.auspost.enums.LocateBy;
import org.ampion.auspost.enums.LocateRelativelyBy;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.EnumMap;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Find {
    private final WebDriver lDriver;

    public Find(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final EnumMap<LocateBy, BiFunction<String, WebDriver, WebElement>> map1 = new EnumMap<>(LocateBy.class);
    private static final BiFunction<String, WebDriver, WebElement> id = (locator, d) -> d.findElement(By.id(locator));
    private static final BiFunction<String, WebDriver, WebElement> name = (locator, d) -> d.findElement(By.name(locator));
    private static final BiFunction<String, WebDriver, WebElement> class_name = (locator, d) -> d.findElement(By.className(locator));
    private static final BiFunction<String, WebDriver, WebElement> link_text = (locator, d) -> d.findElement(By.linkText(locator));
    private static final BiFunction<String, WebDriver, WebElement> partial_link_text = (locator, d) -> d.findElement(By.partialLinkText(locator));
    private static final BiFunction<String, WebDriver, WebElement> tag_name = (locator, d) -> d.findElement(By.tagName(locator));
    private static final BiFunction<String, WebDriver, WebElement> xpath = (locator, d) -> d.findElement(By.xpath(locator));
    private static final BiFunction<String, WebDriver, WebElement> css = (locator, d) -> d.findElement(By.cssSelector(locator));

    static {
        map1.put(LocateBy.ID, id);
        map1.put(LocateBy.NAME, name);
        map1.put(LocateBy.CLASS_NAME, class_name);
        map1.put(LocateBy.LINK_TEXT, link_text);
        map1.put(LocateBy.PARTIAL_LINK_TEXT, partial_link_text);
        map1.put(LocateBy.TAG_NAME, tag_name);
        map1.put(LocateBy.XPATH, xpath);
        map1.put(LocateBy.CSS, css);
    }

    public WebElement elementBy(@NotNull LocateBy locateBy, String locatorValue) {
        return map1.get(locateBy).apply(locatorValue, lDriver);
    }

    private static final EnumMap<LocateRelativelyBy, TriFunction<By, By, WebDriver, WebElement>> map2 = new EnumMap<>(LocateRelativelyBy.class);
    private static final TriFunction<By, By, WebDriver, WebElement> above = (e, locator, d) -> d.findElement(with(locator).above(e));
    private static final TriFunction<By, By, WebDriver, WebElement> below = (e, locator, d) -> d.findElement(with(locator).below(e));
    private static final TriFunction<By, By, WebDriver, WebElement> near = (e, locator, d) -> d.findElement(with(locator).near(e));
    private static final TriFunction<By, By, WebDriver, WebElement> right_of = (e, locator, d) -> d.findElement(with(locator).toRightOf(e));
    private static final TriFunction<By, By, WebDriver, WebElement> left_of = (e, locator, d) -> d.findElement(with(locator).toLeftOf(e));

    static {
        map2.put(LocateRelativelyBy.ABOVE, above);
        map2.put(LocateRelativelyBy.BELOW, below);
        map2.put(LocateRelativelyBy.NEAR, near);
        map2.put(LocateRelativelyBy.RIGHT_OF, right_of);
        map2.put(LocateRelativelyBy.LEFT_OF, left_of);
    }

    public WebElement elementWith(By locator, @NotNull LocateRelativelyBy locateRelativelyBy, By element) {
        return map2.get(locateRelativelyBy).apply(element, locator, lDriver);
    }
}