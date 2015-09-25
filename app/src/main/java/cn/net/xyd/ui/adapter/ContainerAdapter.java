package cn.net.xyd.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.net.xyd.entity.BaseEntity;
import cn.net.xyd.ui.fragment.TabListFragment;

/**
 * @FileName:cn.net.xyd.ui.adapter.ContainerAdapter.java
 * @author:Phoenix
 * @date:2015-09-24 16:05
 * @Version:V1.0
 */
public class ContainerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ContainerAdapter";
    private List<BaseEntity> mCategoryList = null;
    public ContainerAdapter(FragmentManager fm,List<BaseEntity> categoryList){
        super(fm);
        mCategoryList = categoryList;

    }
    @Override
    public Fragment getItem(int position) {
        return new TabListFragment();
    }

    @Override
    public int getCount() {
        return mCategoryList != null  ?mCategoryList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryList != null ? mCategoryList.get(position).getName() : null;
    }
}
