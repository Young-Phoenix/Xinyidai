package cn.net.xyd.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import butterknife.Bind;
import cn.net.xyd.R;
import cn.net.xyd.api.ApiConstants;
import cn.net.xyd.entity.LicaiProductEntity;
import cn.net.xyd.entity.ResponseLiCaiProductEntity;
import cn.net.xyd.listeners.OnCommonPageSelectedListener;
import cn.net.xyd.presenter.ListPresenter;
import cn.net.xyd.presenter.impl.LicaiListPresenterImpl;
import cn.net.xyd.ui.adapter.LicaiListViewAdapter;
import cn.net.xyd.utils.Constants;
import cn.net.xyd.utils.UriHelper;
import cn.net.xyd.view.LicaiListView;
import github.phoenix.library.base.BaseLazyFragment;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.netstatus.NetUtils;
import github.phoenix.library.widgets.LoadMoreListView;
import github.phoenix.library.widgets.XSwipeRefreshLayout;

/**
 * @FileName:cn.net.xyd.ui.fragment.TabListFragment.java
 * @author:Phoenix
 * @date:2015-09-23 16:16
 * @Version:V1.0
 */
public class TabListFragment extends BaseLazyFragment implements LicaiListView, OnCommonPageSelectedListener,AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener ,LoadMoreListView.IXListViewListener{
    private static final String TAG = "TabListFragment";
    @Bind(R.id.fragment_list_view)
    LoadMoreListView fragmentListView;
    @Bind(R.id.fragment_list_swipe_layout)
    XSwipeRefreshLayout fragmentListSwipeLayout;
    /**
     * this variable must be initialized.
     */
    private static String mCurrentCategory = null;
    /**
     * the page number
     */
    private int mCurrentPage = 0;

    private ListPresenter mLicaiListPresenter = null;
    private LicaiListViewAdapter<LicaiProductEntity> mListViewAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentCategory = getResources().getStringArray(R.array.licai_category_list)[0];
    }

    @Override
    protected void onFirstUserVisible() {
        mCurrentPage = 0;
        mLicaiListPresenter = new LicaiListPresenterImpl(mContext,this);
        if(NetUtils.isNetworkConnected(mContext)){
            if(fragmentListSwipeLayout!=null){
                fragmentListSwipeLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLicaiListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA,mCurrentCategory,mCurrentPage,false);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        }else{
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLicaiListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA,mCurrentCategory,mCurrentPage,false);
                }
            });
        }
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
        mListViewAdapter = new LicaiListViewAdapter<LicaiProductEntity>(mContext);

        fragmentListView.setOnItemClickListener(this);
        fragmentListView.setXListViewListener(this);
        fragmentListView.setAdapter(mListViewAdapter);

        fragmentListSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        fragmentListSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_licai_list;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    @Override
    public void refreshListData(ResponseLiCaiProductEntity responseListEntity) {
        if(null != fragmentListSwipeLayout){
            fragmentListSwipeLayout.setRefreshing(false);
        }
        if(responseListEntity!=null){
            if(mListViewAdapter!=null){
                mListViewAdapter.getDatas().clear();
                mListViewAdapter.getDatas().addAll(responseListEntity.getProducts());
                mListViewAdapter.notifyDataSetChanged();
            }
            if(fragmentListView!=null){
                if(UriHelper.getInstance().calculateTotalPages(responseListEntity.getTotalNum())>mCurrentPage){
                    fragmentListView.setPullLoadEnable(true);
                }else{
                    fragmentListView.setPullLoadEnable(false);
                }
            }
        }
    }

    @Override
    public void addMoreListData(ResponseLiCaiProductEntity responseListEntity) {
        if(fragmentListView!=null){
            fragmentListView.stopLoadMore();
        }
        if(responseListEntity!=null){
            if(mListViewAdapter!=null){
                mListViewAdapter.getDatas().addAll(responseListEntity.getProducts());
                mListViewAdapter.notifyDataSetChanged();
            }
            if(fragmentListView!=null){
                if(UriHelper.getInstance().calculateTotalPages(responseListEntity.getTotalNum())>mCurrentPage){
                    fragmentListView.setPullLoadEnable(true);
                }else{
                    fragmentListView.setPullLoadEnable(false);
                }
            }
        }
    }

    @Override
    public void navigateToImagesDetail(int position, LicaiProductEntity entity, int x, int y, int width, int height) {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void showError(String msg) {
        if(fragmentListSwipeLayout!=null){
            fragmentListSwipeLayout.setRefreshing(false);
        }
        toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLicaiListPresenter.loadListData(TAG_LOG, Constants.EVENT_REFRESH_DATA,mCurrentCategory,mCurrentPage,false);
            }
        });
    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void onPageSelected(int position, String keywords) {
        mCurrentCategory = keywords;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mLicaiListPresenter.loadListData(TAG_LOG,Constants.EVENT_REFRESH_DATA,mCurrentCategory,mCurrentPage,true);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        mLicaiListPresenter.loadListData(TAG_LOG,Constants.EVENT_REFRESH_DATA,mCurrentCategory,mCurrentPage,true);
    }
}
