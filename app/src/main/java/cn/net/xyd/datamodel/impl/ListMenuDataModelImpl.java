package cn.net.xyd.datamodel.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.net.xyd.R;
import cn.net.xyd.datamodel.ListMenuDataModel;
import cn.net.xyd.entity.ListMenu;

/**
 * @FileName:cn.net.xyd.datamodel.impl.ListMenuDataModelImpl.java
 * @author:Phoenix
 * @date:2015-09-23 10:00
 * @Version:V1.0
 */
public class ListMenuDataModelImpl implements ListMenuDataModel {
    private static final String TAG = "ListMenuDataModelImpl";
    public static final int FANG = 1;
    public static final int CHE = 2;
    public static final int PUBLISH = 3;

    @Override
    public List<ListMenu> getListMenu(Context context, int type) {
        List<ListMenu> menus = new ArrayList<ListMenu>();
        switch (type) {
            case FANG:
                menus.add(new ListMenu("drawable://" + R.drawable.fang1, "附近中介", "真实信息，等你来选"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang2, "附近房源", "地图找房，直观精准"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang3, "房屋出租", "闲置房屋，快速出租"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang4, "房屋买卖", "海量房源，轻松买卖"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang5, "好房推荐", "购房推荐，贴心服务"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang6, "房产金融", "专业平台，金融无忧"));
                break;
            case CHE:
                menus.add(new ListMenu("drawable://" + R.drawable.fang2, "附近车商", "二手车辆，精准定位"));
                menus.add(new ListMenu("drawable://" + R.drawable.xiche, "附近洗车", "预约洗车，方便快捷"));
                menus.add(new ListMenu("drawable://" + R.drawable.xiuche, "附近修车", "合理定价，服务至上"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang4, "车辆买卖", "海量信息，快速交易"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang5, "好车推荐", "购房推荐，有问必答"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang6, "汽车金融", "专业平台，金融无忧"));
                break;
            case PUBLISH:
                menus.add(new ListMenu("drawable://" + R.drawable.fang1, "卖房信息发布", "真实信息，等你来选"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang2, "租房信息发布", "地图找房，直观精准"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang3, "卖车信息发布", "闲置房屋，快速出租"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang4, "买房求购信息", "海量房源，轻松买卖"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang5, "租房求购信息", "购房推荐，贴心服务"));
                menus.add(new ListMenu("drawable://" + R.drawable.fang6, "买车求购信息", "专业平台，金融无忧"));
                break;
        }
        return menus;
    }
}
