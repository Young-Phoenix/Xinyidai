package cn.net.xyd.datamodel.impl;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.net.xyd.R;
import cn.net.xyd.datamodel.ListViewDataModel;
import cn.net.xyd.entity.BaseEntity;
import cn.net.xyd.entity.LicaiProductEntity;
import cn.net.xyd.entity.ResponseLiCaiProductEntity;
import cn.net.xyd.listeners.BaseMultiLoadedListener;
import cn.net.xyd.utils.UriHelper;
import cn.net.xyd.utils.VolleyHelper;

/**
 * @FileName:cn.net.xyd.datamodel.impl.LicaiListViewDataModelImpl.java
 * @author:Phoenix
 * @date:2015-09-23 16:41
 * @Version:V1.0
 */
public class LicaiListViewDataModelImpl implements ListViewDataModel {
    private static final String TAG = "LicaiListViewDataModelImpl";
    private BaseMultiLoadedListener loadedListener = null;
    public LicaiListViewDataModelImpl(BaseMultiLoadedListener loadedListener){
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonListData(String requestTag,final int event_tag, String keywords, int page) {
        GsonRequest<ResponseLiCaiProductEntity> gsonRequest = new GsonRequest<ResponseLiCaiProductEntity>(
                UriHelper.getInstance().getImagesListUrl(keywords, page),
                null,
                new TypeToken<ResponseLiCaiProductEntity>() {
                }.getType(),
                new Response.Listener<ResponseLiCaiProductEntity>() {
                    @Override
                    public void onResponse(ResponseLiCaiProductEntity response) {
                        ResponseLiCaiProductEntity responseLiCaiProductEntity = new ResponseLiCaiProductEntity();
                        List<LicaiProductEntity>  licaiProductEntitys = new ArrayList<>();
                        for(int i=0;i<30;i++){
                            licaiProductEntitys.add(new LicaiProductEntity());
                        }
                        responseLiCaiProductEntity.setProducts(licaiProductEntitys);
                        loadedListener.onSuccess(event_tag, responseLiCaiProductEntity);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadedListener.onException(error.getMessage());
                    }
                }
        );

        gsonRequest.setShouldCache(true);
        gsonRequest.setTag(requestTag);

        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }
}
