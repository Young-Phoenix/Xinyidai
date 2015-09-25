package cn.net.xyd.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.base.BaseFragmentActivity;
import cn.net.xyd.entity.ListMenu;
import cn.net.xyd.presenter.Presenter;
import cn.net.xyd.presenter.impl.ListMenuPresenterImpl;
import cn.net.xyd.ui.adapter.ListMenuAdapter;
import cn.net.xyd.view.ListMenuView;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.netstatus.NetUtils;

/**
 * @FileName:cn.net.xyd.ui.activity.ListMenuActivity.java
 * @author:Phoenix
 * @date:2015-09-23 09:02
 * @Version:V1.0
 */
public class ListMenuActivity extends BaseFragmentActivity implements ListMenuView,View.OnClickListener{
    private static final String TAG = "ListMenuActivity";
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.btn_left)
    Button btnLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_right)
    Button btnRight;
    @Bind(R.id.list_view)
    ListView listView;
    private int type;
    private String title;
    private Presenter menuPresenter;
    private ListMenuAdapter menuAdapter;
    @Override
    protected void getBundleExtras(Bundle extras) {
        type = extras.getInt("type");
        title = extras.getString("title");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.list_menu_layout;
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
        btnLeft.setBackgroundResource(R.drawable.tt_back_btn);
        btnLeft.setOnClickListener(this);
        tvTitle.setText(title);
        menuPresenter = new ListMenuPresenterImpl(this,this,type);
        menuPresenter.initialization();
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
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    public void initializeViews(List<ListMenu> menus) {
        menuAdapter = new ListMenuAdapter(this);
        menuAdapter.setMenus(menus);
        listView.setAdapter(menuAdapter);
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
