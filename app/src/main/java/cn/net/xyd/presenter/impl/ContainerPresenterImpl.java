package cn.net.xyd.presenter.impl;

import android.content.Context;

import cn.net.xyd.datamodel.ContainerDataModel;
import cn.net.xyd.datamodel.impl.ContainerDataModelImpl;
import cn.net.xyd.presenter.Presenter;
import cn.net.xyd.view.CommonContainerView;

/**
 * @FileName:cn.net.xyd.presenter.impl.ContainerPresenterImpl.java
 * @author:Phoenix
 * @date:2015-09-24 15:52
 * @Version:V1.0
 */
public class ContainerPresenterImpl implements Presenter {
    private static final String TAG = "ContainerPresenterImpl";
    private Context mContext;
    private CommonContainerView containerView;
    private ContainerDataModel containerDataModel;
    public ContainerPresenterImpl(Context context,CommonContainerView containerView){
        this.mContext = context;
        this.containerView = containerView;
        this.containerDataModel = new ContainerDataModelImpl();
    }
    @Override
    public void initialization() {
        containerView.initializePagerViews(containerDataModel.getCommonCategoryList(mContext));
    }
}
