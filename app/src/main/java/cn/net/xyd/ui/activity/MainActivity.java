package cn.net.xyd.ui.activity;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.base.BaseFragmentActivity;
import cn.net.xyd.common.ToastUtils;
import cn.net.xyd.ui.fragment.AccountFragment;
import cn.net.xyd.ui.fragment.HomeFragment;
import cn.net.xyd.ui.fragment.MessageFragment;
import cn.net.xyd.ui.fragment.MoreFragment;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.netstatus.NetUtils;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class MainActivity extends BaseFragmentActivity implements TabHost.OnTabChangeListener, AMapLocationListener,View.OnClickListener {
    @Bind(R.id.btn_left)
    Button btnLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_right)
    Button btnRight;
    @Bind(R.id.fl_fragmentContent)
    FrameLayout flFragmentContent;
    @Bind(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, MessageFragment.class, AccountFragment.class, MoreFragment.class};

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_message_btn,
            R.drawable.tab_account_btn, R.drawable.tab_more_btn};
    private String mTextviewArray[];
    private String mTabTag[];
    private LocationManagerProxy mLocationManagerProxy;
    private String currentCity;

    /**
     * 初始化tab选项卡视图
     *
     * @param index
     * @return
     */
    private View getTabItemView(int index) {
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

    public void init() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);
        mLocationManagerProxy.setGpsEnable(false);
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
    protected void onResume() {
        super.onResume();
        if (mLocationManagerProxy == null) {
            init();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocation();

    }

    private void stopLocation() {
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(this);
            mLocationManagerProxy.destory();
        }
        mLocationManagerProxy = null;
    }

    @Override
    public void onTabChanged(String tabId) {
        //ToastUtils.showLong(this,"tabId:"+tabId);
        if(tabId.equals("home")){
            tvTitle.setText("新易贷");
        }else if(tabId.equals("message")){
            tvTitle.setText("消息中心");
        }else if(tabId.equals("account")){
            tvTitle.setText("个人中心");
        }else if(tabId.equals("more")){
            tvTitle.setText("更多信息");
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0) {
            //获取位置信息
            Double geoLat = aMapLocation.getLatitude();
            Double geoLng = aMapLocation.getLongitude();
            currentCity = aMapLocation.getCity();
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText(currentCity);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.main_activity_layout;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);

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
        init();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                case R.id.btn_left:
                break;
                case R.id.btn_right:
                break;
        }
    }
}
