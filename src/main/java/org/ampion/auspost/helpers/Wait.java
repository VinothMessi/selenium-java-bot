package org.ampion.auspost.helpers;

import org.ampion.auspost.enums.WaitConditions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.EnumMap;
import java.util.function.BiConsumer;

public class Wait {
    private final WebDriver lDriver;
    private final WebDriverWait lWait;

    public Wait(WebDriver driver) {
        this.lDriver = driver;
        this.lWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private static final EnumMap<WaitConditions, BiConsumer<Integer, WebDriver>> map1 = new EnumMap<>(WaitConditions.class);
    private static final BiConsumer<Integer, WebDriver> implicitly = (secs, d) -> d.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(secs));
    private static final BiConsumer<Integer, WebDriver> pageLoadTimeout = (secs, d) -> d.manage().timeouts()
            .pageLoadTimeout(Duration.ofSeconds(secs));
    private static final BiConsumer<Integer, WebDriver> scriptTimeout = (secs, d) -> d.manage().timeouts()
            .scriptTimeout(Duration.ofSeconds(secs));

    static {
        map1.put(WaitConditions.IMPLICITYLY_FOR, implicitly);
        map1.put(WaitConditions.PAGE_LOAD_TIMEOUT, pageLoadTimeout);
        map1.put(WaitConditions.SCRIPT_TIMEOUT, scriptTimeout);
    }

    public void until(@NotNull String action, int seconds) {
        map1.get(WaitConditions.valueOf(action.toUpperCase())).accept(seconds, lDriver);
    }

    public void until(@NotNull WaitConditions condition) {
        if (condition.equals(WaitConditions.ALERT_IS_PRESENT)) {
            lWait.until(ExpectedConditions.alertIsPresent());
        }
    }

    private static final EnumMap<WaitConditions, BiConsumer<WebElement, WebDriverWait>> map2 = new EnumMap<>(WaitConditions.class);
    private static final BiConsumer<WebElement, WebDriverWait> is_visible = (e, w) -> w
            .until(ExpectedConditions.visibilityOf(e));
    private static final BiConsumer<WebElement, WebDriverWait> is_invisible = (e, w) -> w
            .until(ExpectedConditions.invisibilityOf(e));
    private static final BiConsumer<WebElement, WebDriverWait> is_clickable = (e, w) -> w
            .until(ExpectedConditions.elementToBeClickable(e));
    private static final BiConsumer<WebElement, WebDriverWait> is_selectable = (e, w) -> w
            .until(ExpectedConditions.elementToBeSelected(e));
    private static final BiConsumer<WebElement, WebDriverWait> is_stale = (e, w) -> w
            .until(ExpectedConditions.stalenessOf(e));
    private static final BiConsumer<WebElement, WebDriverWait> frame = (e, w) -> w
            .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(e));

    static {
        map2.put(WaitConditions.VISIBILITY_OF, is_visible);
        map2.put(WaitConditions.INVISIBILITY_OF, is_invisible);
        map2.put(WaitConditions.CLICKABLE, is_clickable);
        map2.put(WaitConditions.SELECTABLE, is_selectable);
        map2.put(WaitConditions.STALENESS, is_stale);
        map2.put(WaitConditions.FRAME, frame);
    }

    public void until(WebElement element, @NotNull String action) {
        map2.get(WaitConditions.valueOf(action.toUpperCase())).accept(element, this.lWait);
    }

    private static final EnumMap<WaitConditions, BiConsumer<By, WebDriverWait>> map3 = new EnumMap<>(WaitConditions.class);
    private static final BiConsumer<By, WebDriverWait> presence_of = (by, w) -> w
            .until(ExpectedConditions.presenceOfElementLocated(by));
    private static final BiConsumer<By, WebDriverWait> visibility_of = (by, w) -> w
            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    private static final BiConsumer<By, WebDriverWait> invisibility_of = (by, w) -> w
            .until(ExpectedConditions.invisibilityOfElementLocated(by));
    private static final BiConsumer<By, WebDriverWait> clickable = (by, w) -> w
            .until(ExpectedConditions.elementToBeClickable(by));
    private static final BiConsumer<By, WebDriverWait> selectable = (by, w) -> w
            .until(ExpectedConditions.elementToBeSelected(by));
    private static final BiConsumer<By, WebDriverWait> availability_of_frame = (by, w) -> w
            .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));

    static {
        map3.put(WaitConditions.PRESENCE_OF, presence_of);
        map3.put(WaitConditions.VISIBILITY_OF, visibility_of);
        map3.put(WaitConditions.INVISIBILITY_OF, invisibility_of);
        map3.put(WaitConditions.CLICKABLE, clickable);
        map3.put(WaitConditions.STALENESS, selectable);
        map3.put(WaitConditions.FRAME, availability_of_frame);
    }

    public void until(@NotNull String action, By locator) {
        map3.get(WaitConditions.valueOf(action.toUpperCase())).accept(locator, this.lWait);
    }

    private static final EnumMap<WaitConditions, BiConsumer<String, WebDriverWait>> map4 = new EnumMap<>(WaitConditions.class);
    private static final BiConsumer<String, WebDriverWait> title_is = (s, w) -> w.until(ExpectedConditions.titleIs(s));
    private static final BiConsumer<String, WebDriverWait> title_contains = (s, w) -> w
            .until(ExpectedConditions.titleContains(s));
    private static final BiConsumer<String, WebDriverWait> url_to_be = (s, w) -> w.until(ExpectedConditions.urlToBe(s));
    private static final BiConsumer<String, WebDriverWait> url_matches = (s, w) -> w
            .until(ExpectedConditions.urlMatches(s));
    private static final BiConsumer<String, WebDriverWait> url_contains = (s, w) -> w
            .until(ExpectedConditions.urlContains(s));

    static {
        map4.put(WaitConditions.TITLE_IS, title_is);
        map4.put(WaitConditions.TITLE_CONTAINS, title_contains);
        map4.put(WaitConditions.URL_TO_BE, url_to_be);
        map4.put(WaitConditions.URL_MATCHES, url_matches);
        map4.put(WaitConditions.URL_CONTAINS, url_contains);
    }

    public void until(@NotNull String action, String text) {
        map4.get(WaitConditions.valueOf(action.toUpperCase())).accept(text, this.lWait);
    }
}