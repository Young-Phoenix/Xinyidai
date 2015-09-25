package cn.net.xyd.presenter.impl;

import android.content.Context;

import cn.net.xyd.datamodel.ListMenuDataModel;
import cn.net.xyd.datamodel.impl.ListMenuDataModelImpl;
import cn.net.xyd.presenter.Presenter;
import cn.net.xyd.view.ListMenuView;

/**
 * @FileName:cn.net.xyd.presenter.impl.ListMenuPresenterImpl.java
 * @author:Phoenix
 * @date:2015-09-23 09:56
 * @Version:V1.0
 */
public class ListMenuPresenterImpl implements Presenter {
    private static final String TAG = "ListMenuPresenterImpl";
    private Context mContext;
    private ListMenuView menuView;
    private ListMenuDataModel menuDataModel;
    private int type;
    public ListMenuPresenterImpl(Context context,ListMenuView menuView,int type){
        if(menuView == null){
            throw new IllegalArgumentException("Constructor's parameters must not be NULL");
        }
        this.mContext = context;
        this.menuView = menuView;
        this.menuDataModel = new ListMenuDataModelImpl();
        this.type = type;
    }
    @Override
    public void initialization() {
        menuView.initializeViews(menuDataModel.getListMenu(mContext,type));
    }
}
