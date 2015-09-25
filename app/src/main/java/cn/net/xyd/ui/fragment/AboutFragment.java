package cn.net.xyd.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.base.BaseFragment;
import github.phoenix.library.base.BaseLazyFragment;
import github.phoenix.library.eventbus.EventCenter;

/**
 * @FileName:cn.net.xyd.ui.fragment.AboutFragment.java
 * @author:Phoenix
 * @date:2015-09-22 14:16
 * @Version:V1.0
 */
public class AboutFragment extends BaseLazyFragment implements View.OnClickListener{
    private static final String TAG = "AboutFragment";
    @Bind(R.id.btn_left)
    Button btnLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_right)
    Button btnRight;
    @Bind(R.id.tv_about_xyd)
    TextView tvAboutXyd;

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
        btnLeft.setBackgroundResource(R.drawable.tt_back_btn);
        btnLeft.setOnClickListener(this);
        btnRight.setVisibility(View.GONE);
        tvTitle.setText("关于新易贷");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_about;
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
            case R.id.btn_left:
                finish();
                break;
        }
    }
}
