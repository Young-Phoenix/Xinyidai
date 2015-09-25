package cn.net.xyd.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import cn.net.xyd.R;
import cn.net.xyd.entity.BaseEntity;
import cn.net.xyd.presenter.Presenter;
import cn.net.xyd.presenter.impl.ContainerPresenterImpl;
import cn.net.xyd.ui.adapter.ContainerAdapter;
import cn.net.xyd.view.CommonContainerView;
import github.phoenix.library.base.BaseLazyFragment;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.smartlayout.SmartTabLayout;
import github.phoenix.library.widgets.XViewPager;

/**
 * @FileName:cn.net.xyd.ui.fragment.ContainerFragment.java
 * @author:Phoenix
 * @date:2015-09-24 15:50
 * @Version:V1.0
 */
public class ContainerFragment extends BaseLazyFragment implements CommonContainerView{
    private static final String TAG = "ContainerFragment";
    @Bind(R.id.fragment_tab_smart)
    SmartTabLayout mSmartLayout;
    @Bind(R.id.fragment_pager)
    XViewPager mViewPager;
    private Presenter mContainerPresenter = null;
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
        mContainerPresenter = new ContainerPresenterImpl(mContext,this);
        mContainerPresenter.initialization();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_container;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void initializePagerViews(final List<BaseEntity> categoryList) {
        if(categoryList!=null && !categoryList.isEmpty()){
            mViewPager.setOffscreenPageLimit(categoryList.size());
            mViewPager.setAdapter(new ContainerAdapter(getSupportFragmentManager(), categoryList));
            mSmartLayout.setViewPager(mViewPager);
            mSmartLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    TabListFragment fragment = (TabListFragment)mViewPager.getAdapter().instantiateItem(mViewPager,position);
                    fragment.onPageSelected(position,categoryList.get(position).getId());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
}
