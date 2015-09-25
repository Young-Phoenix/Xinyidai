package cn.net.xyd.utils;

public class Constants
{
  /**
   * 启动页延迟时间，3秒
   */
  public static final int SPLASH_DISPLAY_LENGHT = 3000;
  /**
   * sharepreference中保存是否首次打开应用状态的key
   */
  public static final String FIRST_START="firststart";

  //public static final String BASE_URL = "http://www.xyd.net.cn/mobile/index.php/Index/one_one.html";
  public static final String BASE_URL = "http://www.xyd.net.cn/mobile/index.php/Index/main.html";

  public static final int EVENT_BEGIN = 0X100;
  public static final int EVENT_REFRESH_DATA = EVENT_BEGIN + 10;
  public static final int EVENT_LOAD_MORE_DATA = EVENT_BEGIN + 20;
  public static final int EVENT_START_PLAY_MUSIC = EVENT_BEGIN + 30;
  public static final int EVENT_STOP_PLAY_MUSIC = EVENT_BEGIN + 40;
}
