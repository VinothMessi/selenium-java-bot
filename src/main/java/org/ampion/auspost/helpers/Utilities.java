package org.ampion.auspost.helpers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Calendar;

public class Utilities {
    private Utilities() {
    }

    public static String encode(@NotNull String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    @Contract("_ -> new")
    public static @NotNull String decode(@NotNull String encodedValue) {
        return new String(Base64.getDecoder().decode(encodedValue.getBytes()));
    }

    public static @NotNull String currentDateTime() {
        var date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(date);
    }

    public static @NotNull String localTime() {
        return LocalTime.now().toString();
    }

    public static @NotNull String localTime(String zoneID) {
        return LocalTime.now(ZoneId.of(zoneID)).toString();
    }

    public static long epochMilliSeconds() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    public static long epochSeconds() {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }
}