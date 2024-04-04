package com.pixelpunch.vaultify.core.utils;

import java.util.Date;

public class CheckUtils {
    public static boolean checkIfElapsed(Date date1, Date date2, int minutes) {
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diffInMinutes = diffInMillies / (60 * 1000);
        return diffInMinutes >= minutes;
    }
}
