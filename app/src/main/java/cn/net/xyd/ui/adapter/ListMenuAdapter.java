package cn.net.xyd.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.net.xyd.R;
import cn.net.xyd.entity.ListMenu;
import cn.net.xyd.utils.TimeUtils;


/**
 * Created by Administrator on 2015/8/4 0004.
 */
public class ListMenuAdapter extends BaseAdapter {
    private DisplayImageOptions options;
    private Context mContext;
    private List<ListMenu> menus;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder = null;
    public ListMenuAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.fang1) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.fang1) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.fang1) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象
    }

    public void setMenus(List<ListMenu> menus) {
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus != null?menus.size():0;
    }

    @Override
    public Object getItem(int position) {
        return menus != null ? menus.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.info_list_item,null);
            viewHolder.pictureView = (ImageView)convertView.findViewById(R.id.iv_picture);
            viewHolder.title = (TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.content = (TextView)convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        ListMenu menu = (ListMenu)getItem(position);
        if(menu!=null){
            if(TextUtils.isEmpty(menu.getPicUrl())){
                    ImageLoader.getInstance().displayImage(menu.getPicUrl(),
                            viewHolder.pictureView, options,
                            new AnimateFirstDisplayListener());
            }else{
                viewHolder.pictureView.setImageResource(R.drawable.fang1);
            }
            viewHolder.title.setText(menu.getTitle());
            viewHolder.content.setText(menu.getContent());
        }
        return convertView;
    }

    private final class ViewHolder {
        private ImageView pictureView;
        private TextView title;
        private TextView content;
    }
    private static class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(loadedImage);
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
