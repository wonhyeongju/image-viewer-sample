package app.wonlab.com.imageviewersample.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import app.wonlab.com.imageviewersample.BuildConfig;

/**
 * Created by wonhj on 2015/08/03.
 */
public class LogUtil {
    private final static String TAG = "image-viewer-sample";

    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(TAG, message);
        }
    }

    public static void d(String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.d(TAG, message);
        }
    }

    public static void e(String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(TAG, message);
        }
    }

    public static String exceptionToString(Throwable throwable) {
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
