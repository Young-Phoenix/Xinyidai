package cn.net.xyd.ui.activity.boot;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import cn.net.xyd.R;
import cn.net.xyd.ui.activity.MainActivity;
import cn.net.xyd.utils.Constants;
import cn.net.xyd.utils.SPUtils;

public class IndexAc extends Activity {
  private SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ac_index);
    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        if ((Boolean)SPUtils.get(IndexAc.this, Constants.FIRST_START, true)) {
          // 将登录标志位设置为false，下次登录时不在显示首次登录界面
          SPUtils.put(IndexAc.this, Constants.FIRST_START, false);
          Intent intent = new Intent();
          intent.setClass(IndexAc.this,
                  GuideAc.class);
          IndexAc.this.startActivity(intent);
          IndexAc.this.finish();
        } else {
          Intent intent = new Intent();
          //intent.setClass(IndexAc.this, WebViewAc.class);
          intent.setClass(IndexAc.this, MainActivity.class);
          IndexAc.this.startActivity(intent);
          IndexAc.this.finish();

        }

      }
    }, Constants.SPLASH_DISPLAY_LENGHT);
  }
}