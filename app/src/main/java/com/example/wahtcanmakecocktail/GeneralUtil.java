package com.example.wahtcanmakecocktail;

import java.util.Date;

public class GeneralUtil {
    private static long prevTime = 0;
    private static GeneralUtil instance;

    public static GeneralUtil getInstance() {
        if (instance == null) {
            instance = new GeneralUtil();
        }
        return instance;
    }

    public boolean isDoubleTapped() {
        Date now = new Date();
        if(prevTime == 0){
            prevTime = now.getTime();
            return false;
        }
        if((now.getTime() - prevTime) < 2000) {
            return true;
        } else {
            prevTime = now.getTime();
            return false;
        }
    }

}
