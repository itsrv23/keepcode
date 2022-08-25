package ru.itsrv23.keepcode_test.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Integer parseCountryId(String id){
        // country-57
        Pattern pattern = Pattern.compile("country-(\\d*)");
        Matcher matcher = pattern.matcher(id);
        if (!matcher.find()) {
            return null;
        }
        return Integer.valueOf(matcher.group(1));
    }
}
