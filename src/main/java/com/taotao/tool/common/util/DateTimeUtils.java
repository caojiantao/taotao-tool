package com.taotao.tool.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class DateTimeUtils {

    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateTimeUtils() {
    }

    public static String format(LocalDateTime time) {
        return format(time, DEFAULT_DATETIME_FORMATTER);
    }

    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        return time == null ? null : time.format(formatter);
    }

    public static String formatRelative(LocalDateTime time) {
        if (time == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        if (time.isAfter(now)) {
            return "刚刚";
        }

        long minutes = ChronoUnit.MINUTES.between(time, now);
        if (minutes < 1) {
            return "刚刚";
        }
        if (minutes < 60) {
            return minutes + " 分钟前";
        }

        long hours = ChronoUnit.HOURS.between(time, now);
        if (hours < 24) {
            return hours + " 小时前";
        }

        long days = ChronoUnit.DAYS.between(time.toLocalDate(), now.toLocalDate());
        if (days < 30) {
            return days + " 天前";
        }

        return format(time);
    }

    public static String formatDuration(int seconds) {
        if (seconds <= 0) {
            return "0分";
        }
        if (seconds < 60) {
            return seconds + "秒";
        }

        int minutes = seconds / 60;
        if (minutes < 60) {
            return minutes + "分";
        }

        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;
        if (hours < 24) {
            return remainingMinutes == 0
                    ? hours + "时"
                    : hours + "时" + remainingMinutes + "分";
        }

        int days = hours / 24;
        int remainingHours = hours % 24;
        return remainingHours == 0
                ? days + "天"
                : days + "天" + remainingHours + "时";
    }
}
