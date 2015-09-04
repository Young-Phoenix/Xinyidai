package cn.net.xyd.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.net.xyd.common.LogUtils;


/**
 * Created by Administrator on 2015/8/20 0020.
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment{
    private View mContextView = null;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtils.d(TAG, "BaseFragment-->onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "BaseFragment-->onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.d(TAG, "BaseFragment-->onCreateView()");

        if (mContextView != null) {
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (parent != null) {
                parent.removeView(mContextView);
            }
        } else {
            mContextView = inflater.inflate(bindLayout(), container,false);
            initView(mContextView);
        }

        doBusiness(getActivity());

        return mContextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtils.d(TAG, "BaseFragment-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LogUtils.d(TAG, "BaseFragment-->onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        LogUtils.d(TAG, "BaseFragment-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.d(TAG, "BaseFragment-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.d(TAG, "BaseFragment-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.d(TAG, "BaseFragment-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        LogUtils.d(TAG, "BaseFragment-->onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtils.d(TAG, "BaseFragment-->onDetach()");
        super.onDetach();
    }

    /**
     * @return
     */
    protected Activity getContext(){
        return getActivity();
    }

}
