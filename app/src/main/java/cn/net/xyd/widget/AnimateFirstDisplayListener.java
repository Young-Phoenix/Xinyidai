package cn.net.xyd.widget;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @FileName:cn.net.xyd.widget.AnimateFirstDisplayListener.java
 * @author:Phoenix
 * @date:2015-09-18 16:42
 * @Version:V1.0
 */
public class AnimateFirstDisplayListener extends SimpleImageLoadingListener{
    private static final String TAG = "AnimateFirstDisplayListener";
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
