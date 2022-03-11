package org.ampion.auspost.helpers.element;

import org.ampion.auspost.enums.LocateBy;
import org.ampion.auspost.enums.LocateRelativelyBy;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.EnumMap;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FindChild {
    private static final EnumMap<LocateBy, BiFunction<String, WebElement, WebElement>> map1 = new EnumMap<>(LocateBy.class);
    private static final BiFunction<String, WebElement, WebElement> id = (locator, e) -> e.findElement(By.id(locator));
    private static final BiFunction<String, WebElement, WebElement> name = (locator, e) -> e.findElement(By.name(locator));
    private static final BiFunction<String, WebElement, WebElement> class_name = (locator, e) -> e.findElement(By.className(locator));
    private static final BiFunction<String, WebElement, WebElement> link_text = (locator, e) -> e.findElement(By.linkText(locator));
    private static final BiFunction<String, WebElement, WebElement> partial_link_text = (locator, e) -> e.findElement(By.partialLinkText(locator));
    private static final BiFunction<String, WebElement, WebElement> tag_name = (locator, e) -> e.findElement(By.tagName(locator));
    private static final BiFunction<String, WebElement, WebElement> xpath = (locator, e) -> e.findElement(By.xpath(locator));
    private static final BiFunction<String, WebElement, WebElement> css = (locator, e) -> e.findElement(By.cssSelector(locator));

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

    public WebElement elementBy(@NotNull LocateBy locateBy, String locatorValue, WebElement parent) {
        return map1.get(locateBy).apply(locatorValue, parent);
    }

    private static final EnumMap<LocateRelativelyBy, TriFunction<By, By, WebElement, WebElement>> map2 = new EnumMap<>(LocateRelativelyBy.class);
    private static final TriFunction<By, By, WebElement, WebElement> above = (e, locator, p) -> p.findElement(with(locator).above(e));
    private static final TriFunction<By, By, WebElement, WebElement> below = (e, locator, p) -> p.findElement(with(locator).below(e));
    private static final TriFunction<By, By, WebElement, WebElement> near = (e, locator, p) -> p.findElement(with(locator).near(e));
    private static final TriFunction<By, By, WebElement, WebElement> right_of = (e, locator, p) -> p.findElement(with(locator).toRightOf(e));
    private static final TriFunction<By, By, WebElement, WebElement> left_of = (e, locator, p) -> p.findElement(with(locator).toLeftOf(e));

    static {
        map2.put(LocateRelativelyBy.ABOVE, above);
        map2.put(LocateRelativelyBy.BELOW, below);
        map2.put(LocateRelativelyBy.NEAR, near);
        map2.put(LocateRelativelyBy.RIGHT_OF, right_of);
        map2.put(LocateRelativelyBy.LEFT_OF, left_of);
    }

    public WebElement elementWith(By locator, @NotNull LocateRelativelyBy locateRelativelyBy, By element, WebElement parent) {
        return map2.get(locateRelativelyBy).apply(element, locator, parent);
    }
}