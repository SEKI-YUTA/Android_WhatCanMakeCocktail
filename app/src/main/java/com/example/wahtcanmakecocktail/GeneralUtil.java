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

    // x秒以内に２回以上実行されたかを判定
    public boolean isDoubleTapped() {
        int x = 2000;
        Date now = new Date();
        if(prevTime == 0){
            prevTime = now.getTime();
            return false;
        }
        if((now.getTime() - prevTime) < x) {
            return true;
        } else {
            prevTime = now.getTime();
            return false;
        }
    }

}
