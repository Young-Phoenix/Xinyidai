package cn.net.xyd.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.net.xyd.R;
import cn.net.xyd.entity.Message;
import cn.net.xyd.utils.TimeUtils;

/**
 * Created by Administrator on 2015/9/4 0004.
 */
public class MessageInfoAdapter extends BaseAdapter {
    protected Context mContext;
    protected ViewHolder viewHolder = null;
    protected List<Message> infos;
    private LayoutInflater mInflater;
    public MessageInfoAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setInfos(List<Message> infos) {
        this.infos = infos;
    }

    @Override
    public int getCount() {
        return infos != null?infos.size():0;
    }

    @Override
    public Object getItem(int position) {
        return infos != null ? infos.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.message_info_list_item,null);
            viewHolder.title = (TextView)convertView.findViewById(R.id.tv_title);
            viewHolder.time = (TextView)convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Message msg = (Message)getItem(position);
        if(msg!=null){
            viewHolder.title.setText(msg.getTitle());
            viewHolder.time.setText(TimeUtils.longToStrng(msg.getTime()));
        }
        return convertView;
    }
    protected final class ViewHolder {
        protected TextView title;
        protected TextView time;
    }

}
