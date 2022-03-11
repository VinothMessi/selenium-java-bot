package org.ampion.auspost.helpers.browser;

import org.ampion.auspost.enums.BrowserWindow;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.EnumMap;
import java.util.function.*;


public class Browser {
    private final WebDriver lDriver;

    public Browser(WebDriver driver) {
        this.lDriver = driver;
    }

    private static final EnumMap<BrowserWindow, Consumer<WebDriver>> map1 = new EnumMap<>(BrowserWindow.class);
    private static final Consumer<WebDriver> maximize = d -> d.manage().window().maximize();
    private static final Consumer<WebDriver> minimize = d -> d.manage().window().minimize();
    private static final Consumer<WebDriver> fullscreen = d -> d.manage().window().fullscreen();
    private static final Consumer<WebDriver> close = WebDriver::close;
    private static final Consumer<WebDriver> quit = WebDriver::quit;
    private static final Consumer<WebDriver> navigate_forward = d -> d.navigate().forward();
    private static final Consumer<WebDriver> navigate_back = d -> d.navigate().back();
    private static final Consumer<WebDriver> refresh = d -> d.navigate().refresh();
    private static final Consumer<WebDriver> accept_alert = d -> d.switchTo().alert().accept();
    private static final Consumer<WebDriver> dismiss_alert = d -> d.switchTo().alert().dismiss();

    static {
        map1.put(BrowserWindow.MAXIMIZE, maximize);
        map1.put(BrowserWindow.MINIMIZE, minimize);
        map1.put(BrowserWindow.FULL_SCREEN, fullscreen);
        map1.put(BrowserWindow.CLOSE, close);
        map1.put(BrowserWindow.QUIT, quit);
        map1.put(BrowserWindow.NAVIGATE_FORWARD, navigate_forward);
        map1.put(BrowserWindow.NAVIGATE_BACK, navigate_back);
        map1.put(BrowserWindow.REFRESH, refresh);
        map1.put(BrowserWindow.ACCEPT_ALERT, accept_alert);
        map1.put(BrowserWindow.DISMISS_ALERT, dismiss_alert);
    }

    public void window(@NotNull String action) {
        map1.get(BrowserWindow.valueOf(action.toUpperCase())).accept(this.lDriver);
    }

    private static final EnumMap<BrowserWindow, BiConsumer<String, WebDriver>> map2 = new EnumMap<>(BrowserWindow.class);
    private static final BiConsumer<String, WebDriver> get_to = (url, d) -> d.get(url);
    private static final BiConsumer<String, WebDriver> navigate_to = (url, d) -> d.navigate().to(url);
    private static final BiConsumer<String, WebDriver> type_text_in_alert = (text, d) -> d.switchTo().alert().sendKeys(text);

    static {
        map2.put(BrowserWindow.GET_TO, get_to);
        map2.put(BrowserWindow.NAVIGATE_TO, navigate_to);
        map2.put(BrowserWindow.TYPE_ALERT_TEXT, type_text_in_alert);
    }

    public void window(@NotNull String action, String url) {
        map2.get(BrowserWindow.valueOf(action.toUpperCase())).accept(url, this.lDriver);
    }

    private static final EnumMap<BrowserWindow, Function<WebDriver, String>> map3 = new EnumMap<>(BrowserWindow.class);
    private static final Function<WebDriver, String> get_page_title = d -> d.getTitle().trim();
    private static final Function<WebDriver, String> get_page_source = d -> d.getPageSource().trim();
    private static final Function<WebDriver, String> get_current_url = d -> d.getCurrentUrl().trim();
    private static final Function<WebDriver, String> get_alert_text = d -> d.switchTo().alert().getText().trim();

    static {
        map3.put(BrowserWindow.PAGE_TITLE, get_page_title);
        map3.put(BrowserWindow.PAGE_SOURCE, get_page_source);
        map3.put(BrowserWindow.CURRENT_URL, get_current_url);
        map3.put(BrowserWindow.ALERT_TEXT, get_alert_text);
    }

    public String get(@NotNull String action) {
        return map3.get(BrowserWindow.valueOf(action.toUpperCase())).apply(this.lDriver);
    }

    private static final EnumMap<BrowserWindow, UnaryOperator<WebDriver>> map4 = new EnumMap<>(BrowserWindow.class);
    private static final UnaryOperator<WebDriver> switch_to_new_tab = d -> d.switchTo().newWindow(WindowType.TAB);
    private static final UnaryOperator<WebDriver> switch_to_new_window = d -> d.switchTo().newWindow(WindowType.WINDOW);
    private static final UnaryOperator<WebDriver> switch_to_default_content = d -> d.switchTo().defaultContent();
    private static final UnaryOperator<WebDriver> switch_to_parent_frame = d -> d.switchTo().parentFrame();

    static {
        map4.put(BrowserWindow.NEW_TAB, switch_to_new_tab);
        map4.put(BrowserWindow.NEW_WINDOW, switch_to_new_window);
        map4.put(BrowserWindow.DEFAULT_CONTENT, switch_to_default_content);
        map4.put(BrowserWindow.PARENT_FRAME, switch_to_parent_frame);
    }

    public WebDriver switchTo(@NotNull String action) {
        return map4.get(BrowserWindow.valueOf(action.toUpperCase())).apply(this.lDriver);
    }

    private static final EnumMap<BrowserWindow, BiFunction<WebDriver, String, WebDriver>> map5 = new EnumMap<>(BrowserWindow.class);
    private static final BiFunction<WebDriver, String, WebDriver> switch_to_frame_by_id = (d, id) -> d.switchTo().frame(id);
    private static final BiFunction<WebDriver, String, WebDriver> switch_to_frame_by_name = (d, name) -> d.switchTo().frame(name);
    private static final BiFunction<WebDriver, String, WebDriver> switch_to_frame_by_index = (d, index) -> d.switchTo().frame(Integer.parseInt(index));

    static {
        map5.put(BrowserWindow.FRAME_BY_ID, switch_to_frame_by_id);
        map5.put(BrowserWindow.FRAME_BY_NAME, switch_to_frame_by_name);
        map5.put(BrowserWindow.FRAME_BY_INDEX, switch_to_frame_by_index);
    }

    public WebDriver switchTo(@NotNull String action, String value) {
        return map5.get(BrowserWindow.valueOf(action.toUpperCase())).apply(this.lDriver, value);
    }

    private static final EnumMap<BrowserWindow, BiFunction<WebDriver, WebElement, WebDriver>> map6 = new EnumMap<>(BrowserWindow.class);
    private static final BiFunction<WebDriver, WebElement, WebDriver> switch_to_frame_by_element = (d, element) -> d.switchTo().frame(element);

    static {
        map6.put(BrowserWindow.FRAME_BY_ELEMENT, switch_to_frame_by_element);
    }

    public WebDriver switchTo(@NotNull String action, WebElement element) {
        return map6.get(BrowserWindow.valueOf(action.toUpperCase())).apply(this.lDriver, element);
    }
}