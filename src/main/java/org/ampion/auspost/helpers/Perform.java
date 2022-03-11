package org.ampion.auspost.helpers;

import org.ampion.auspost.enums.PerformActions;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Perform {
    private final WebDriver lDriver;

    public Perform(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final EnumMap<PerformActions, BiConsumer<WebElement, WebDriver>> map1 = new EnumMap<>(PerformActions.class);
    private static final BiConsumer<WebElement, WebDriver> click = (e, d) -> new Actions(d).click(e).perform();
    private static final BiConsumer<WebElement, WebDriver> click_and_hold = (e, d) -> new Actions(d).clickAndHold(e).perform();
    private static final BiConsumer<WebElement, WebDriver> release = (e, d) -> new Actions(d).release(e).perform();
    private static final BiConsumer<WebElement, WebDriver> right_click = (e, d) -> new Actions(d).contextClick(e).perform();
    private static final BiConsumer<WebElement, WebDriver> double_click = (e, d) -> new Actions(d).doubleClick(e).perform();
    private static final BiConsumer<WebElement, WebDriver> move_over = (e, d) -> new Actions(d).moveToElement(e).perform();

    static {
        map1.put(PerformActions.CLICK, click);
        map1.put(PerformActions.CLICK_AND_HOLD, click_and_hold);
        map1.put(PerformActions.RELEASE, release);
        map1.put(PerformActions.RIGHT_CLICK, right_click);
        map1.put(PerformActions.DOUBLE_CLICK, double_click);
        map1.put(PerformActions.MOVE_OVER, move_over);
    }

    public void actions(@NotNull String action, WebElement element) {
        map1.get(PerformActions.valueOf(action.toUpperCase())).accept(element, this.lDriver);
    }

    private static final EnumMap<PerformActions, Consumer<WebDriver>> map2 = new EnumMap<>(PerformActions.class);
    private static final Consumer<WebDriver> select_all = d -> new Actions(d).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    private static final Consumer<WebDriver> cut = d -> new Actions(d).keyDown(Keys.CONTROL).sendKeys("x").keyUp(Keys.CONTROL).build().perform();
    private static final Consumer<WebDriver> copy = d -> new Actions(d).clickAndHold().keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
    private static final Consumer<WebDriver> paste = d -> new Actions(d).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

    static {
        map2.put(PerformActions.SELECT_ALL, select_all);
        map2.put(PerformActions.CUT, cut);
        map2.put(PerformActions.COPY, copy);
        map2.put(PerformActions.PASTE, paste);
    }

    public void actions(@NotNull String action) {
        map2.get(PerformActions.valueOf(action.toUpperCase())).accept(this.lDriver);
    }

    private static final EnumMap<PerformActions, BiConsumer<String, WebDriver>> map3 = new EnumMap<>(PerformActions.class);
    private static final BiConsumer<String, WebDriver> type_keys = (s, d) -> new Actions(d).sendKeys(s).perform();
    private static final BiConsumer<String, WebDriver> upper_case = (s, d) -> new Actions(d).keyDown(Keys.SHIFT).keyDown(Keys.chord(s)).build().perform();

    static {
        map3.put(PerformActions.TYPE_KEYS, type_keys);
        map3.put(PerformActions.UPPER_CASE, upper_case);
    }

    public void actions(@NotNull String action, String value) {
        map3.get(PerformActions.valueOf(action.toUpperCase())).accept(value, this.lDriver);
    }

    private static final EnumMap<PerformActions, TriConsumer<String, WebElement, WebDriver>> map4 = new EnumMap<>(PerformActions.class);
    private static final TriConsumer<String, WebElement, WebDriver> type_text = (s, e, d) -> new Actions(d).sendKeys(e, s).perform();

    static {
        map4.put(PerformActions.TYPE_TEXT, type_text);
    }

    public void actions(@NotNull String action, String value, WebElement element) {
        map4.get(PerformActions.valueOf(action.toUpperCase())).accept(value, element, this.lDriver);
    }

    private static final EnumMap<PerformActions, TriConsumer<WebElement, WebElement, WebDriver>> map5 = new EnumMap<>(PerformActions.class);
    private static final TriConsumer<WebElement, WebElement, WebDriver> drag_and_drop = (e1, e2, d) -> new Actions(d).dragAndDrop(e1, e2).perform();

    static {
        map5.put(PerformActions.DRAG_AND_DROP, drag_and_drop);
    }

    public void actions(@NotNull String action, WebElement source, WebElement destination) {
        map5.get(PerformActions.valueOf(action.toUpperCase())).accept(source, destination, this.lDriver);
    }
}