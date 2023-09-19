package com.taotao.tool.util;

import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Objects;
import java.util.Properties;

/**
 * @author caojiantao
 */
public class PlaceHolderUtils {

    private static PropertyPlaceholderHelper placeHolder = new PropertyPlaceholderHelper("${", "}");

    public static String replacePlaceholders(String value, Properties properties) {
        if (Objects.isNull(properties)) {
            return value;
        }
        return placeHolder.replacePlaceholders(value, properties);
    }
}
