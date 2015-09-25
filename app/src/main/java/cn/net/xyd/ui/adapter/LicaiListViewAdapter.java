package cn.net.xyd.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName:cn.net.xyd.ui.adapter.LicaiListViewAdapter.java
 * @author:Phoenix
 * @date:2015-09-24 17:29
 * @Version:V1.0
 */
public class LicaiListViewAdapter<T> extends BaseAdapter {
    private static final String TAG = "LicaiListViewAdapter";
    private Context mContext;
    public LicaiListViewAdapter(Context context){
        mContext = context;
    }
    private List<T> datas = new ArrayList<>();

    public List<T> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
