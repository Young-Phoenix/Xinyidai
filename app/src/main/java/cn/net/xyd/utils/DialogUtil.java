package cn.net.xyd.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class DialogUtil
{
  public static Dialog createLoadingDialog(Context paramContext, String paramString)
  {
    Object localObject = LayoutInflater.from(paramContext).inflate(2130903043, null);
    LinearLayout localLinearLayout = (LinearLayout)((View)localObject).findViewById(2131296260);
    ImageView localImageView = (ImageView)((View)localObject).findViewById(2131296261);
    localObject = (TextView)((View)localObject).findViewById(2131296262);
    localImageView.startAnimation(AnimationUtils.loadAnimation(paramContext, 2130968576));
    ((TextView)localObject).setText(paramString);
    paramContext = new Dialog(paramContext, 2131165186);
    paramContext.setCancelable(false);
    paramContext.setContentView(localLinearLayout, new LayoutParams(-1, -1));
    return paramContext;
  }
}



/* Location:           D:\decompiler\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     cn.net.xyd.utils.DialogUtil

 * JD-Core Version:    0.7.0.1

 */