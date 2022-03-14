package org.ampion.auspost.helpers;

import com.github.javafaker.Faker;
import org.ampion.auspost.enums.RandomValues;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.ampion.auspost.Constants.*;

public class Random {
    private static final EnumMap<RandomValues, UnaryOperator<String>> map1 = new EnumMap<>(RandomValues.class);
    // In pattern pass '?' marks based on number of characters needed
    private static final UnaryOperator<String> string = pattern -> new Faker(new Locale(LANGUAGE)).letterify(pattern);
    // In pattern pass '#' marks based on number of numeric needed
    private static final UnaryOperator<String> number = pattern -> new Faker(new Locale(LANGUAGE)).numerify(pattern);
    // In pattern pass '?' marks based on number of characters needed
    // In pattern pass '#' marks based on number of numeric needed
    private static final UnaryOperator<String> alpha_numeric = pattern -> new Faker(new Locale(LANGUAGE)).bothify(pattern);

    static {
        map1.put(RandomValues.STRING, string);
        map1.put(RandomValues.NUMBER, number);
        map1.put(RandomValues.ALPHA_NUMERIC, alpha_numeric);
    }

    public void values(@NotNull String action, String pattern) {
        map1.get(RandomValues.valueOf(action.toUpperCase())).apply(pattern);
    }

    private static final EnumMap<RandomValues, Supplier<String>> map2 = new EnumMap<>(RandomValues.class);
    private static final Supplier<String> full_name = () -> new Faker().name().fullName();
    private static final Supplier<String> first_name = () -> new Faker().name().firstName();
    private static final Supplier<String> last_name = () -> new Faker().name().lastName();
    private static final Supplier<String> name_with_middle = () -> new Faker().name().nameWithMiddle();

    static {
        map2.put(RandomValues.FULL_NAME, full_name);
        map2.put(RandomValues.FIRST_NAME, first_name);
        map2.put(RandomValues.LAST_NAME, last_name);
        map2.put(RandomValues.NAME_WITH_MIDDLE, name_with_middle);
    }

    public void values(@NotNull String action) {
        map2.get(RandomValues.valueOf(action.toUpperCase())).get();
    }

    private static final EnumMap<RandomValues, IntFunction<Object[]>> map3 = new EnumMap<>(RandomValues.class);
    private static final IntFunction<Object[]> array_of_full_name = size -> {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Faker().name().fullName());
        }
        return list.toArray();
    };
    private static final IntFunction<Object[]> array_of_first_name = size -> {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Faker().name().firstName());
        }
        return list.toArray();
    };
    private static final IntFunction<Object[]> array_of_last_name = size -> {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Faker().name().lastName());
        }
        return list.toArray();
    };
    private static final IntFunction<Object[]> array_of_name_with_middle = size -> {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Faker().name().nameWithMiddle());
        }
        return list.toArray();
    };

    static {
        map3.put(RandomValues.ARRAY_OF_FULL_NAME, array_of_full_name);
        map3.put(RandomValues.ARRAY_OF_FIRST_NAME, array_of_first_name);
        map3.put(RandomValues.ARRAY_OF_LAST_NAME, array_of_last_name);
        map3.put(RandomValues.ARRAY_OF_NAME_WITH_MIDDLE, array_of_name_with_middle);
    }

    public void values(@NotNull String action, Integer size) {
        map3.get(RandomValues.valueOf(action.toUpperCase())).apply(size);
    }
}