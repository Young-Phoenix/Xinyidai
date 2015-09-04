package cn.net.xyd.view.activity;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import cn.net.xyd.R;
import cn.net.xyd.base.BaseActivity;
import cn.net.xyd.common.ToastUtils;
import cn.net.xyd.view.fragment.AccountFragment;
import cn.net.xyd.view.fragment.HomeFragment;
import cn.net.xyd.view.fragment.MessageFragment;
import cn.net.xyd.view.fragment.MoreFragment;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener{
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, MessageFragment.class,AccountFragment.class,MoreFragment.class};

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_message_btn,
            R.drawable.tab_account_btn,R.drawable.tab_more_btn};
    private String mTextviewArray[];
    private String mTabTag[];
    private FragmentTabHost mTabHost;
    @Override
    public int getLayoutId() {
        return R.layout.main_activity_layout;
    }

    @Override
    public void setupViews() {

        /*ActionBar actionBar = this.getActionBar();
        actionBar.setCustomView(R.layout.title_bar);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.show();*/

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_fragmentContent);
        mTabHost.setOnTabChangedListener(this);
        mTextviewArray = getResources().getStringArray(R.array.tab_menu);
        mTabTag = getResources().getStringArray(R.array.tab_tag);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTag[i]).setIndicator(
                    getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.home_btn_bg);
        }
    }
    /**
     * 初始化tab选项卡视图
     *
     * @param index
     * @return
     */
    private View getTabItemView( int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
        /*
         * ImageView imageView = (ImageView) view.findViewById(R.id.iamge_view);
		 * imageView.setImageResource(mImageViewArray[index]);
		 */
        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(mTextviewArray[index]);
        Drawable drawable = getResources().getDrawable(mImageViewArray[index]);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        return view;
    }
    @Override
    public void initialized() {

    }
    private long temp = 0;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - temp > 2000) {
            ToastUtils.showShort(this, R.string.press_again_exit);
            temp = System.currentTimeMillis();
        } else {
            this.finish();
            System.exit(0);
        }
    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
