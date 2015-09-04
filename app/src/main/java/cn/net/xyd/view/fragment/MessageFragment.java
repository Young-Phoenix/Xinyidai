package cn.net.xyd.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.net.xyd.R;
import cn.net.xyd.adapter.MessageInfoAdapter;
import cn.net.xyd.base.BaseFragment;
import cn.net.xyd.entity.Message;

/**
 * Created by Administrator on 2015/8/24 0024.
 */
public class MessageFragment extends BaseFragment implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener2{
    protected PullToRefreshListView pullToRefresh;
    private MessageInfoAdapter messageInfoAdapter;
    @Override
    public int bindLayout() {
        return R.layout.pulltorefresh_list_fragment;
    }

    @Override
    public void initView(View view) {
        pullToRefresh = (PullToRefreshListView) view.findViewById(R.id.pull_to_refresh_listview);
        ListView mListView = pullToRefresh.getRefreshableView();
        messageInfoAdapter = new MessageInfoAdapter(getActivity());
        mListView.setAdapter(messageInfoAdapter);
        mListView.setOnItemClickListener(this);
        pullToRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefresh.setOnRefreshListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        List<Message> msgs = new ArrayList<Message>();
        for(int i=0;i<10;i++){
            msgs.add(new Message("充值失败","2015-08-29 09:25:30"));
        }
        messageInfoAdapter.setInfos(msgs);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
