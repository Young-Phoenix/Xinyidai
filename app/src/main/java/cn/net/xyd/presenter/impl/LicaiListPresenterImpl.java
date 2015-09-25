package cn.net.xyd.presenter.impl;

import android.content.Context;

import cn.net.xyd.datamodel.ListViewDataModel;
import cn.net.xyd.datamodel.impl.LicaiListViewDataModelImpl;
import cn.net.xyd.entity.LicaiProductEntity;
import cn.net.xyd.entity.ResponseLiCaiProductEntity;
import cn.net.xyd.listeners.BaseMultiLoadedListener;
import cn.net.xyd.presenter.ListPresenter;
import cn.net.xyd.utils.Constants;
import cn.net.xyd.view.LicaiListView;

/**
 * @FileName:cn.net.xyd.presenter.impl.LicaiListPresenterImpl.java
 * @author:Phoenix
 * @date:2015-09-23 16:27
 * @Version:V1.0
 */
public class LicaiListPresenterImpl implements ListPresenter,BaseMultiLoadedListener<ResponseLiCaiProductEntity> {
    private static final String TAG = "LicaiListPresenterImpl";
    private Context mContext;
    private LicaiListView mLicaiListView;
    private ListViewDataModel mListViewDateModel;
    public LicaiListPresenterImpl(Context context,LicaiListView licaiListView){
        mContext = context;
        mLicaiListView = licaiListView;
        mListViewDateModel = new LicaiListViewDataModelImpl(this);
    }

    @Override
    public void onSuccess(int event_tag, ResponseLiCaiProductEntity data) {
        mLicaiListView.hiddenLoading();
        if(event_tag== Constants.EVENT_REFRESH_DATA){
            mLicaiListView.refreshListData(data);
        }else if(event_tag==Constants.EVENT_LOAD_MORE_DATA){
            mLicaiListView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mLicaiListView.hiddenLoading();
        mLicaiListView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mLicaiListView.hiddenLoading();
        mLicaiListView.showError(msg);
    }

    @Override
    public void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh) {
        mLicaiListView.hiddenLoading();
        if(!isSwipeRefresh){
            mLicaiListView.showLoading(mContext.getString(github.phoenix.library.R.string.common_loading_message));
        }
        mListViewDateModel.getCommonListData(requestTag,event_tag,keywords,page);
    }

    @Override
    public void onItemClickListener(int position, LicaiProductEntity entity) {

    }

}
