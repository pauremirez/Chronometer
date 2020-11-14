package com.estella.politecnico.utils;

public class TimerUtil {

    /**
     * Method to get a String time format.
     *
     * @param millis time to be converted
     * @return the formatted time
     */
    public String getTimeStyleTextFromMillis(long millis) {
        long tempCrono = millis;
        long mm = (millis % 1000);
        tempCrono = tempCrono / 1000;
        long sec = tempCrono % 60;
        tempCrono = tempCrono / 60;
        long min = tempCrono % 60;
        tempCrono = tempCrono / 60;
        long hours = tempCrono;
        if (hours > 23)
            hours = hours % 24;

        String hourString = Long.toString(hours);
        String minString = Long.toString(min);
        String secString = Long.toString(sec);
        String mmString = Long.toString(mm);
        if (hourString.length() == 1)
            hourString = "0" + hourString;
        if (minString.length() == 1)
            minString = "0" + minString;
        if (secString.length() == 1)
            secString = "0" + secString;
        if (mmString.length() == 1)
            mmString = "00" + mmString;
        else if (mmString.length() == 2)
            mmString = "0" + mmString;

        return hourString + ":" + minString + ":" + secString + "." + mmString;
    }
}
