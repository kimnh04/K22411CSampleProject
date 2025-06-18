package com.nguyenkim.utils;

import java.util.Arrays;
import java.util.List;

public class NetworkUtils {
    private static final List<String> VIETTEL_PREFIXES = Arrays.asList(
            "086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"
    );

    private static final List<String> MOBIFONE_PREFIXES = Arrays.asList(
            "089", "090", "093", "070", "076", "077", "078", "079"
    );

    public static boolean isViettel(String phoneNumber) {
        String normalized = normalizePhoneNumber(phoneNumber);
        return VIETTEL_PREFIXES.stream().anyMatch(normalized::startsWith);
    }

    public static boolean isMobifone(String phoneNumber) {
        String normalized = normalizePhoneNumber(phoneNumber);
        return MOBIFONE_PREFIXES.stream().anyMatch(normalized::startsWith);
    }

    public static boolean isOther(String phoneNumber) {
        return !isViettel(phoneNumber) && !isMobifone(phoneNumber);
    }

    private static String normalizePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return "";
        // Chuyển từ +8498... thành 098..., loại bỏ dấu cách nếu có
        phoneNumber = phoneNumber.replaceAll("\\s+", "");
        if (phoneNumber.startsWith("+84")) {
            phoneNumber = "0" + phoneNumber.substring(3);
        }
        return phoneNumber;
    }
}