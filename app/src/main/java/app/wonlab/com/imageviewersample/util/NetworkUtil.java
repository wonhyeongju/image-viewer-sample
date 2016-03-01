package app.wonlab.com.imageviewersample.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by wonx on 2015/08/04.
 */
public class NetworkUtil {

    public static boolean isConnectedNetwork(Context context) {
        return (isConnectedWifi(context) || isConnectedMobile(context));
    }

    private static boolean isConnectedWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return (nInfo != null && nInfo.isConnected() && nInfo.getTypeName().toUpperCase().equals("WIFI"));
    }

    private static boolean isConnectedMobile(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return (nInfo != null && nInfo.isConnected() && nInfo.getTypeName().toUpperCase().equals("MOBILE"));
    }
}
