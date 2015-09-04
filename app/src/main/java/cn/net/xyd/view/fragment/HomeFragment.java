package cn.net.xyd.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.net.xyd.R;
import cn.net.xyd.adapter.ViewPagerAdapter;
import cn.net.xyd.base.BaseActivity;
import cn.net.xyd.base.BaseFragment;
import cn.net.xyd.utils.DensityUtil;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    // 引导图片资源
    private static final int[] pics = {R.drawable.banner_01, R.drawable.banner_02,
            R.drawable.banner_03};

    // 底部小店图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;

    @Override
    public int bindLayout() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void initView(View view) {
        /**
         * 获取图片的像素
         */
        /*BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.banner_01, opts);
        opts.inSampleSize = 1;
        opts.inJustDecodeBounds = false;
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banner_01, opts);
        int width=opts.outWidth;
        int height=opts.outHeight;*/

        views = new ArrayList<View>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(iv);
        }
        //RelativeLayout rlBanner = (RelativeLayout)view.findViewById(R.id.rl_banner);

        vp = (ViewPager) view.findViewById(R.id.viewpager);
        //vp = new ViewPager(getContext());
        //vp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.px2dip(getContext(),height)));
        //rlBanner.addView(vp);
        // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.addOnPageChangeListener(this);
        // button = (Button) findViewById(R.id.button);
        // 初始化底部小圆点
        initDots(view);
    }

    private void initDots(View view) {
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);

        dots = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
    }

    private int i = 0;
    private Timer timer = new Timer(true);
    private TimerTask timerTask;

    class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            Message msg = new Message();
            msg.what=0;
            mHandler.sendMessage(msg);
        }
    }
    static class MyHandler extends Handler{
        WeakReference<HomeFragment> homeFragment;
        MyHandler(HomeFragment fragment){
            homeFragment = new WeakReference<HomeFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeFragment mFragment = homeFragment.get();
            switch (msg.what){
                case 0:
                    mFragment.i++;
                    mFragment.setCurView(mFragment.i);
                    mFragment.setCurDot(mFragment.i);
                    if (mFragment.i >= 2) {
                        mFragment.i = -1;
                    }
                    break;
            }
        }
    }
    private Handler mHandler = new MyHandler(this);

    @Override
    public void onResume() {
        super.onResume();
        if (timer != null) {
            timer.schedule(timerTask = new MyTimeTask(), 3000, 3000);
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (timerTask != null)
            timerTask.cancel();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        // 设置底部小点选中状太
        setCurDot(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * 设置当前的引导页
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }

        vp.setCurrentItem(position);
    }

    /**
     * 这只当前引导小点的位置
     */
    private void setCurDot(int positon) {
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }

        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = positon;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }
}
