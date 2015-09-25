package cn.net.xyd.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.ui.activity.CommonActivity;
import cn.net.xyd.ui.activity.CommonWebActivity;
import github.phoenix.library.base.BaseLazyFragment;
import github.phoenix.library.base.BaseWebActivity;
import github.phoenix.library.eventbus.EventCenter;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class MoreFragment extends BaseLazyFragment implements View.OnClickListener {


    @Bind(R.id.ll_about_xyd)
    LinearLayout llAboutXyd;
    @Bind(R.id.ll_aqbz)
    LinearLayout llAqbz;
    @Bind(R.id.ll_contacts_us)
    LinearLayout llContactsUs;
    @Bind(R.id.ll_suggestion)
    LinearLayout llSuggestion;
    @Bind(R.id.ll_customer_hotline)
    LinearLayout llCustomerHotline;
    @Bind(R.id.tv_login_out)
    Button tvLoginOut;

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
        llAboutXyd.setOnClickListener(this);
        llAqbz.setOnClickListener(this);
        llContactsUs.setOnClickListener(this);
        llSuggestion.setOnClickListener(this);
        llCustomerHotline.setOnClickListener(this);
        tvLoginOut.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.more_fragment;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_about_xyd:
                Bundle bundle = new Bundle();
                bundle.putString(BaseWebActivity.BUNDLE_KEY_TITLE,((TextView)llAboutXyd.getChildAt(1)).getText().toString());
                bundle.putString(BaseWebActivity.BUNDLE_KEY_URL, "http://www.xyd.net.cn/mobile/index.php/Config/about/");
                bundle.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, true);
                readyGo(CommonWebActivity.class,bundle);
                /*Bundle bundle = new Bundle();
                bundle.putInt("type",1);
                readyGo(CommonActivity.class,bundle);*/
                break;
            case R.id.ll_aqbz:
                Bundle bundle1 = new Bundle();
                bundle1.putString(BaseWebActivity.BUNDLE_KEY_TITLE,((TextView)llAqbz.getChildAt(1)).getText().toString());
                bundle1.putString(BaseWebActivity.BUNDLE_KEY_URL, "http://www.xyd.net.cn/mobile/index.php/Config/anquan/");
                bundle1.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, true);
                readyGo(CommonWebActivity.class,bundle1);
                break;
            case R.id.ll_contacts_us:
                Bundle bundle2 = new Bundle();
                bundle2.putString(BaseWebActivity.BUNDLE_KEY_TITLE,((TextView)llContactsUs.getChildAt(1)).getText().toString());
                bundle2.putString(BaseWebActivity.BUNDLE_KEY_URL, "http://www.xyd.net.cn/mobile/index.php/Config/lxwm.html");
                bundle2.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, true);
                readyGo(CommonWebActivity.class,bundle2);
                break;
            case R.id.ll_suggestion:
                Bundle bundle3 = new Bundle();
                bundle3.putString(BaseWebActivity.BUNDLE_KEY_TITLE,((TextView)llSuggestion.getChildAt(1)).getText().toString());
                bundle3.putString(BaseWebActivity.BUNDLE_KEY_URL, "http://www.xyd.net.cn/mobile/index.php/Config/yijian/");
                bundle3.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, true);
                readyGo(CommonWebActivity.class,bundle3);
                break;
            case R.id.ll_customer_hotline:
                String number = ((TextView)llCustomerHotline.getChildAt(2)).getText().toString();
                //用intent启动拨打电话
                /*Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
                break;
        }
    }
}
