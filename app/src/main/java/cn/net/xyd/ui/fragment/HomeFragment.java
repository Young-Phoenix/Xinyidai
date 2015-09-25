package cn.net.xyd.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.datamodel.impl.ListMenuDataModelImpl;
import cn.net.xyd.presenter.impl.ListMenuPresenterImpl;
import cn.net.xyd.ui.activity.LicaiActivity;
import cn.net.xyd.ui.activity.ListMenuActivity;
import cn.net.xyd.ui.adapter.ViewPagerAdapter;
import cn.net.xyd.widget.AnimateFirstDisplayListener;
import github.phoenix.library.base.BaseLazyFragment;
import github.phoenix.library.eventbus.EventCenter;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class HomeFragment extends BaseLazyFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.ll_dots)
    LinearLayout llDots;
    @Bind(R.id.rl_banner)
    RelativeLayout rlBanner;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.ib_fangchan)
    ImageButton ibFangchan;
    @Bind(R.id.ib_qiche)
    ImageButton ibQiche;
    @Bind(R.id.ib_daxuesheng)
    ImageButton ibDaxuesheng;
    @Bind(R.id.ib_huodong)
    ImageButton ibHuodong;
    @Bind(R.id.ib_licai)
    ImageButton ibLicai;
    @Bind(R.id.ib_tjf)
    ImageButton ibTjf;
    @Bind(R.id.ib_tjc)
    ImageButton ibTjc;
    @Bind(R.id.ib_xxfb)
    ImageButton ibXxfb;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    // 引导图片资源
    private static final int[] pics = new int[6];//{R.drawable.banner_01, R.drawable.banner_02,R.drawable.banner_03};

    // 底部小店图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;

    DisplayImageOptions options;

    private void initImageOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.banner_01) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.banner_01)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.banner_01)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .decodingOptions(new BitmapFactory.Options())//设置图片的解码配置
                        //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                        //设置图片加入缓存前，对bitmap进行设置
                        //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
    }

    private void initDots() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        dots = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {

            //dots[i] = (ImageView) llDots.getChildAt(i);
            dots[i] = new ImageView(getContext());
            dots[i].setLayoutParams(params);
            dots[i].setPadding(15, 15, 15, 15);
            dots[i].setClickable(true);
            dots[i].setImageResource(R.drawable.dot);
            dots[i].setEnabled(true);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应

            llDots.addView(dots[i]);
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
            msg.what = 0;
            mHandler.sendMessage(msg);
        }
    }

    static class MyHandler extends Handler {
        WeakReference<HomeFragment> homeFragment;

        MyHandler(HomeFragment fragment) {
            homeFragment = new WeakReference<HomeFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeFragment mFragment = homeFragment.get();
            switch (msg.what) {
                case 0:
                    mFragment.i++;
                    mFragment.setCurView(mFragment.i);
                    mFragment.setCurDot(mFragment.i);
                    if (mFragment.i >= pics.length - 1) {
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
    public void onPause() {
        super.onPause();
        if (timerTask != null)
            timerTask.cancel();
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
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
        initImageOptions();
        views = new ArrayList<View>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setLayoutParams(mParams);
            //iv.setImageResource(pics[i]);
            ImageLoader.getInstance().displayImage("http://www.xyd.net.cn/mobile/html5/Tpl/Public/images/5.jpg",
                    iv, options,
                    new AnimateFirstDisplayListener());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(iv);
        }
        //RelativeLayout rlBanner = (RelativeLayout)view.findViewById(R.id.rl_banner);
        //vp = (ViewPager) view.findViewById(R.id.viewpager);
        //vp = new ViewPager(getContext());
        //vp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.px2dip(getContext(),height)));
        //rlBanner.addView(vp);
        // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views);
        viewpager.setAdapter(vpAdapter);
        // 绑定回调
        viewpager.addOnPageChangeListener(this);
        // button = (Button) findViewById(R.id.button);
        // 初始化底部小圆点
        initDots();
        ibFangchan.setOnClickListener(this);
        ibQiche.setOnClickListener(this);
        ibXxfb.setOnClickListener(this);
        ibLicai.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.home_fragment_layout;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
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

        viewpager.setCurrentItem(position);
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
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.ib_fangchan:
                bundle.putInt("type", ListMenuDataModelImpl.FANG);
                bundle.putString("title","房产");
                readyGo(ListMenuActivity.class, bundle);
                break;
            case R.id.ib_qiche:
                bundle.putInt("type", ListMenuDataModelImpl.CHE);
                bundle.putString("title", "汽车");
                readyGo(ListMenuActivity.class, bundle);
                break;
            case R.id.ib_xxfb:
                bundle.putInt("type", ListMenuDataModelImpl.PUBLISH);
                bundle.putString("title","信息发布");
                readyGo(ListMenuActivity.class, bundle);
                break;
            case R.id.ib_licai:
                bundle.putString("title","新易贷理财产品");
                readyGo(LicaiActivity.class, bundle);
                break;
            default:
                int position = (Integer) v.getTag();
                setCurView(position);
                setCurDot(position);
                break;
        }

    }
}
