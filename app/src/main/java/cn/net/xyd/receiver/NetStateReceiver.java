package cn.net.xyd.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class NetStateReceiver
  extends BroadcastReceiver
{
  public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (TextUtils.equals(paramIntent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
      Log.e("net", "net changed");
    }
  }
}


/* Location:           D:\decompiler\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     cn.net.xyd.receiver.NetStateReceiver
 * JD-Core Version:    0.7.0.1
 */