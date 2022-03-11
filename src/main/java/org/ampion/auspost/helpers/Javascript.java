package org.ampion.auspost.helpers;

import org.ampion.auspost.enums.Scripts;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Javascript {
    private final WebDriver lDriver;

    public Javascript(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final EnumMap<Scripts, Consumer<WebDriver>> map1 = new EnumMap<>(Scripts.class);
    private static final Consumer<WebDriver> page_refresh = d -> ((JavascriptExecutor) d).executeScript("history.go(0)");
    private static final Consumer<WebDriver> scroll_to_bottom = d -> ((JavascriptExecutor) d).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    private static final Consumer<WebDriver> scroll_to_top = d -> ((JavascriptExecutor) d).executeScript("window.scroll(0,0)");

    static {
        map1.put(Scripts.PAGE_REFRESH, page_refresh);
        map1.put(Scripts.SCROLL_TO_BOTTOM, scroll_to_bottom);
        map1.put(Scripts.SCROLL_TO_TOP, scroll_to_top);
    }

    public void execute(@NotNull String action) {
        map1.get(Scripts.valueOf(action.toUpperCase())).accept(this.lDriver);
    }

    private static final EnumMap<Scripts, Function<WebDriver, String>> map2 = new EnumMap<>(Scripts.class);
    private static final Function<WebDriver, String> get_title = d -> ((JavascriptExecutor) d).executeScript("return document.title;").toString().trim();
    private static final Function<WebDriver, String> get_url = d -> ((JavascriptExecutor) d).executeScript("return document.URL;").toString().trim();
    private static final Function<WebDriver, String> get_domain = d -> ((JavascriptExecutor) d).executeScript("return document.domain;").toString().trim();
    private static final Function<WebDriver, String> get_inner_text = d -> ((JavascriptExecutor) d).executeScript("return document.documentElement.innerText;").toString().trim();

    static {
        map2.put(Scripts.TITLE, get_title);
        map2.put(Scripts.URL, get_url);
        map2.put(Scripts.DOMAIN, get_domain);
        map2.put(Scripts.INNER_TEXT, get_inner_text);
    }

    public String get(@NotNull String action) {
        return map2.get(Scripts.valueOf(action.toUpperCase())).apply(this.lDriver);
    }

    private static final EnumMap<Scripts, BiConsumer<WebElement, WebDriver>> map3 = new EnumMap<>(Scripts.class);
    private static final BiConsumer<WebElement, WebDriver> click = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].click()", e);
    private static final BiConsumer<WebElement, WebDriver> scroll_to = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", e);
    private static final BiConsumer<WebElement, WebDriver> highlight = (e, d) -> ((JavascriptExecutor) d).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", e);

    static {
        map3.put(Scripts.CLICK, click);
        map3.put(Scripts.SCROLL_TO, scroll_to);
        map3.put(Scripts.HIGHLIGHT, highlight);
    }

    public void execute(@NotNull String action, WebElement element) {
        map3.get(Scripts.valueOf(action.toUpperCase())).accept(element, this.lDriver);
    }

    private static final EnumMap<Scripts, BiConsumer<Integer, WebDriver>> map4 = new EnumMap<>(Scripts.class);
    private static final BiConsumer<Integer, WebDriver> scroll_up = (i, d) -> ((JavascriptExecutor) d).executeScript("window.scrollBy(0,-" + i + ")");
    private static final BiConsumer<Integer, WebDriver> scroll_down = (i, d) -> ((JavascriptExecutor) d).executeScript("window.scrollBy(0," + i + ")");
    private static final BiConsumer<Integer, WebDriver> sleep = (i, d) -> ((JavascriptExecutor) d).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], " + i + ");");

    static {
        map4.put(Scripts.SCROLL_UP, scroll_up);
        map4.put(Scripts.SCROLL_DOWN, scroll_down);
        map4.put(Scripts.SLEEP, sleep);
    }

    public void execute(@NotNull String action, int value) {
        map4.get(Scripts.valueOf(action.toUpperCase())).accept(value, this.lDriver);
    }

    private static final EnumMap<Scripts, BiConsumer<String, WebDriver>> map5 = new EnumMap<>(Scripts.class);
    private static final BiConsumer<String, WebDriver> open = (url, d) -> ((JavascriptExecutor) d).executeScript("window.location = '" + url + "'");

    static {
        map5.put(Scripts.OPEN, open);
    }

    public void execute(@NotNull String action, String url) {
        map5.get(Scripts.valueOf(action.toUpperCase())).accept(url, this.lDriver);
    }

    private static final EnumMap<Scripts, TriConsumer<String, WebDriver, WebElement>> map6 = new EnumMap<>(Scripts.class);
    private static final TriConsumer<String, WebDriver, WebElement> type = (text, d, e) -> ((JavascriptExecutor) d).executeScript("arguments[0].value = '" + text + "'", e);

    static {
        map6.put(Scripts.TYPE, type);
    }

    public void execute(@NotNull String action, String text, WebElement element) {
        map6.get(Scripts.valueOf(action.toUpperCase())).accept(text, this.lDriver, element);
    }
}