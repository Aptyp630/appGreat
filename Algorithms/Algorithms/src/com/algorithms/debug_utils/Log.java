package com.algorithms.debug_utils;

import java.util.HashMap;
import java.util.Set;

public class Log {
    private static final boolean LOGGING_TURN_ON = true;
    private static final String TAG_FEATURE = "DEBUG";
    private Class mClazz;


    public Log(Class clazz) {
        mClazz = clazz;
    }

    public static Log getLogger(Class clazz) {
        return new Log(clazz);
    }

    public void i(String message) {
        if(LOGGING_TURN_ON) {
            android.util.Log.i(TAG_FEATURE, mClazz.getSimpleName() + "]] " + message);
        }
    }

    public void i(String message, Exception e) {
        if(LOGGING_TURN_ON) {
            android.util.Log.i(TAG_FEATURE, mClazz.getSimpleName() + "]] " + message + "   e:" + e);
        }
    }

    public void e(String message) {
        if(LOGGING_TURN_ON) {
            android.util.Log.e(TAG_FEATURE, mClazz.getSimpleName() + "]] " + message);
        }
    }

    public void w(String message) {
        if(LOGGING_TURN_ON) {
            android.util.Log.w(TAG_FEATURE, mClazz.getSimpleName() + "]] " + message);
        }
    }

    public void d(String message) {
        if(LOGGING_TURN_ON) {
            android.util.Log.d(TAG_FEATURE, mClazz.getSimpleName() + "]] " + message);
        }
    }

    public static void d(String tag, String message) {
        if(LOGGING_TURN_ON) {
            android.util.Log.d(TAG_FEATURE, tag + "]] " + message);
        }
    }

    public static void d(String tag, int number) {
        d(tag, String.valueOf(number));
    }

    public static void log(HashMap<String, String> mapToLog) {
        if(!LOGGING_TURN_ON) return;
        if(null == mapToLog) {
            android.util.Log.i(TAG_FEATURE, "]] mapToLog=" + mapToLog);
            return;
        }
        Set<String> keys = mapToLog.keySet();
        for(String key : keys) {
            android.util.Log.i(TAG_FEATURE, "]] " + key + " = " + mapToLog.get(key));
        }
    }
}
