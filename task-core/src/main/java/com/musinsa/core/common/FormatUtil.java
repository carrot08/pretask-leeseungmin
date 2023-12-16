package com.musinsa.core.common;

import java.text.DecimalFormat;

public class FormatUtil {
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");

    public static String price(int amount) {
        return PRICE_FORMAT.format(amount);
    }
}
