package org.ampion.auspost.helpers.element;

import org.ampion.auspost.enums.LocateBy;
import org.ampion.auspost.enums.LocateRelativelyBy;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class FindAllChild {
    private static final EnumMap<LocateBy, BiFunction<String, WebElement, List<WebElement>>> map1 = new EnumMap<>(LocateBy.class);
    private static final BiFunction<String, WebElement, List<WebElement>> id = (locator, e) -> e.findElements(By.id(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> name = (locator, e) -> e.findElements(By.name(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> class_name = (locator, e) -> e.findElements(By.className(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> link_text = (locator, e) -> e.findElements(By.linkText(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> partial_link_text = (locator, e) -> e.findElements(By.partialLinkText(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> tag_name = (locator, e) -> e.findElements(By.tagName(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> xpath = (locator, e) -> e.findElements(By.xpath(locator));
    private static final BiFunction<String, WebElement, List<WebElement>> css = (locator, e) -> e.findElements(By.cssSelector(locator));

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

    public List<WebElement> findElementsBy(@NotNull LocateBy locateBy, String locatorValue, WebElement parent) {
        return map1.get(locateBy).apply(locatorValue, parent);
    }

    private static final EnumMap<LocateRelativelyBy, TriFunction<By, By, WebElement, List<WebElement>>> map2 = new EnumMap<>(LocateRelativelyBy.class);
    private static final TriFunction<By, By, WebElement, List<WebElement>> above = (e, locator, p) -> p.findElements(with(locator).above(e));
    private static final TriFunction<By, By, WebElement, List<WebElement>> below = (e, locator, p) -> p.findElements(with(locator).below(e));
    private static final TriFunction<By, By, WebElement, List<WebElement>> near = (e, locator, p) -> p.findElements(with(locator).near(e));
    private static final TriFunction<By, By, WebElement, List<WebElement>> right_of = (e, locator, p) -> p.findElements(with(locator).toRightOf(e));
    private static final TriFunction<By, By, WebElement, List<WebElement>> left_of = (e, locator, p) -> p.findElements(with(locator).toLeftOf(e));

    static {
        map2.put(LocateRelativelyBy.ABOVE, above);
        map2.put(LocateRelativelyBy.BELOW, below);
        map2.put(LocateRelativelyBy.NEAR, near);
        map2.put(LocateRelativelyBy.RIGHT_OF, right_of);
        map2.put(LocateRelativelyBy.LEFT_OF, left_of);
    }

    public List<WebElement> findElementsWith(By locator, @NotNull LocateRelativelyBy locateRelativelyBy, By element, WebElement parent) {
        return map2.get(locateRelativelyBy).apply(element, locator, parent);
    }
}