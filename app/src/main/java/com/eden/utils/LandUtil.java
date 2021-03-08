package com.eden.utils;

import java.util.Random;

public class LandUtil {

    //generating random erosion Tendency

    public static Double getRandomErosionTendency(){
        int value = new Random().nextInt(100);
        return Double.valueOf(value);
    }
}
