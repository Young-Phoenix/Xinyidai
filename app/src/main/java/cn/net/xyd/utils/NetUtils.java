package cn.net.xyd.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;

public class NetUtils
{
  private NetUtils()
  {
    throw new UnsupportedOperationException("cannot be instantiated");
  }
  
  public static boolean isConnected(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected()) && (paramContext.getState() == State.CONNECTED)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isWifi(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {}
    while (paramContext.getActiveNetworkInfo().getType() != 1) {
      return false;
    }
    return true;
  }
  
  public static void openSetting(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT > 10) {}
    for (Intent localIntent = new Intent("android.settings.SETTINGS");; localIntent = new Intent("android.settings.WIRELESS_SETTINGS"))
    {
      paramActivity.startActivityForResult(localIntent, 0);
      return;
    }
  }
}



/* Location:           D:\decompiler\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     cn.net.xyd.utils.NetUtils

 * JD-Core Version:    0.7.0.1

 */