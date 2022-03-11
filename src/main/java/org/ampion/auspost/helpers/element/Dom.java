package org.ampion.auspost.helpers.element;

import org.ampion.auspost.enums.Elements;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.EnumMap;
import java.util.function.*;

public class Dom {
    private static final EnumMap<Elements, Consumer<WebElement>> map1 = new EnumMap<>(Elements.class);
    private static final Consumer<WebElement> clear = WebElement::clear;
    private static final Consumer<WebElement> click = WebElement::click;
    private static final Consumer<WebElement> submit = WebElement::submit;

    static {
        map1.put(Elements.CLEAR, clear);
        map1.put(Elements.CLICK, click);
        map1.put(Elements.SUBMIT, submit);
    }

    public void element(@NotNull String action, WebElement element) {
        map1.get(Elements.valueOf(action.toUpperCase())).accept(element);
    }

    private static final EnumMap<Elements, Function<WebElement, String>> map2 = new EnumMap<>(Elements.class);
    private static final Function<WebElement, String> text_from = WebElement::getText;
    private static final Function<WebElement, String> tag_name_from = WebElement::getTagName;

    static {
        map2.put(Elements.TEXT_FROM, text_from);
        map2.put(Elements.TAG_NAME_FROM, tag_name_from);
    }

    public String get(@NotNull String action, WebElement element) {
        return map2.get(Elements.valueOf(action.toUpperCase())).apply(element);
    }

    private static final EnumMap<Elements, Predicate<WebElement>> map3 = new EnumMap<>(Elements.class);
    private static final Predicate<WebElement> is_displayed = WebElement::isDisplayed;
    private static final Predicate<WebElement> is_enabled = WebElement::isEnabled;
    private static final Predicate<WebElement> is_selected = WebElement::isSelected;

    static {
        map3.put(Elements.IS_DISPLAYED, is_displayed);
        map3.put(Elements.IS_ENABLED, is_enabled);
        map3.put(Elements.IS_SELECTED, is_selected);
    }

    public boolean checkIf(WebElement element, @NotNull String action) {
        return map3.get(Elements.valueOf(action.toUpperCase())).test(element);
    }

    private static final EnumMap<Elements, BiConsumer<String, WebElement>> map4 = new EnumMap<>(Elements.class);
    private static final BiConsumer<String, WebElement> visible_text = (s, e) -> {
        Select dropDown = new Select(e);
        dropDown.selectByVisibleText(s);
    };
    private static final BiConsumer<String, WebElement> value = (s, e) -> {
        Select dropDown = new Select(e);
        dropDown.selectByValue(s);
    };
    private static final BiConsumer<String, WebElement> index = (s, e) -> {
        Select dropDown = new Select(e);
        dropDown.selectByIndex(Integer.parseInt(s));
    };

    static {
        map4.put(Elements.VISIBLE_TEXT, visible_text);
        map4.put(Elements.VALUE, value);
        map4.put(Elements.INDEX, index);
    }

    public void selectBy(@NotNull String action, String value, WebElement element) {
        map4.get(Elements.valueOf(action.toUpperCase())).accept(value, element);
    }

    private static final EnumMap<Elements, BiFunction<WebElement, String, String>> map5 = new EnumMap<>(Elements.class);
    private static final BiFunction<WebElement, String, String> attribute = WebElement::getAttribute;
    private static final BiFunction<WebElement, String, String> domAttribute = WebElement::getDomAttribute;
    private static final BiFunction<WebElement, String, String> domProperty = WebElement::getDomProperty;
    private static final BiFunction<WebElement, String, String> cssValue = WebElement::getCssValue;

    static {
        map5.put(Elements.ATTRIBUTE, attribute);
        map5.put(Elements.DOM_ATTRIBUTE, domAttribute);
        map5.put(Elements.DOM_PROPERTY, domProperty);
        map5.put(Elements.CSS_VALUE, cssValue);
    }

    public String element(@NotNull String action, String value, WebElement element) {
        return map5.get(Elements.valueOf(action.toUpperCase())).apply(element, value);
    }

    private static final EnumMap<Elements, BiConsumer<WebElement, String>> map6 = new EnumMap<>(Elements.class);
    private static final BiConsumer<WebElement, String> type = WebElement::sendKeys;

    static {
        map6.put(Elements.TEXT, type);
    }

    public void type(@NotNull String action, String text, WebElement element) {
        map6.get(Elements.valueOf(action.toUpperCase())).accept(element, text);
    }
}