package cn.net.xyd.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.net.xyd.R;
import cn.net.xyd.base.BaseActivity;
import cn.net.xyd.common.NetUtils;
import cn.net.xyd.receiver.NetStateReceiver;
import cn.net.xyd.utils.Constants;
import cn.net.xyd.utils.DialogUtil;

public class WebViewAc
        extends BaseActivity implements View.OnClickListener {
    private String currentURL;
    private Dialog dialog;
    private long exitTime;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    WebViewAc.this.dialog.dismiss();
                    break;
                case 1:
                    WebViewAc.this.dialog.show();
            }
        }
    };
    private WebView mWebView;
    private TextView reload;
    private ImageView refresh;
    private NetStateReceiver receiver;

    //根据资源id获取资源内容
    private String getStrFromID(int id) {
        return getResources().getString(id);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initSet() {
        this.reload = (TextView) findViewById(R.id.reload);
        reload.setOnClickListener(this);
        this.refresh = (ImageView) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        this.mWebView = ((WebView) findViewById(R.id.webview));
        this.dialog = DialogUtil.createLoadingDialog(this, getStrFromID(R.string.loading));
       // this.currentURL = getResources().getString(R.string.base_url);
        this.currentURL = Constants.BASE_URL;
        this.mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings localWebSettings = this.mWebView.getSettings();
        //启动JavaScript
        localWebSettings.setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(new ToastForJs(this), "androidObj");
        //将图片调整到适合webview的大小
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setLoadWithOverviewMode(true);
        localWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int progress) {
                if (progress == 100) {
                    Log.e("WebViewAc", "load finish");
                    WebViewAc.this.mHandler.sendEmptyMessage(0);
                    return;
                }
                Log.e("WebViewAc", "loading……");
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (reload.getVisibility() == View.VISIBLE) {
                    reload.setVisibility(View.GONE);
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                reload.setVisibility(View.VISIBLE);

                Toast.makeText(WebViewAc.this, "加载失败，请稍候再试", Toast.LENGTH_LONG).show();
            }

            //打开网页时不调用系统浏览器， 而是在本WebView中显示
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (NetUtils.isConnected(WebViewAc.this)) {
                    WebViewAc.this.currentURL = url;
                    webView.loadUrl(url);
                } else {
                    WebViewAc.this.showNoNetWorkDialog();
                }
                return true;
            }

        });
    }

    //加载页面
    private void load() {
        this.mHandler.sendEmptyMessage(1);
        this.mWebView.loadUrl(this.currentURL);
    }

    //注册网络状态监听
    private void registerNetChangeReceiver() {
        this.receiver = new NetStateReceiver();
        Log.i("TAG", "register receiver android.net.conn.CONNECTIVITY_CHANGE");
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        localIntentFilter.setPriority(1000);
        registerReceiver(this.receiver, localIntentFilter);
    }

    //无网络时显示对话框
    private void showNoNetWorkDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getStrFromID(R.string.dialog_title))
                .setMessage(getStrFromID(R.string.dialog_msg))
                .setPositiveButton(getStrFromID(R.string.dialog_positive_btn), new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reload.setVisibility(View.VISIBLE);
                        NetUtils.openSetting(WebViewAc.this);
                    }
                }).setNegativeButton(getStrFromID(R.string.dialog_negative_btn), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                WebViewAc.this.finish();
            }
        }).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Log.e("WebViewAc", "reloading");
            if (this.mWebView != null) {
                this.mWebView.loadUrl(this.currentURL);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.receiver != null) {
            unregisterReceiver(this.receiver);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((this.mWebView == null) || (!this.mWebView.canGoBack())) {
                if (System.currentTimeMillis() - this.exitTime > 2000L) {
                    this.exitTime = System.currentTimeMillis();
                    Toast.makeText(this, getResources().getString(R.string.press_again), Toast.LENGTH_LONG).show();
                } else {
                    finish();
                    System.exit(0);
                }
            } else {
                this.mWebView.goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload:
                load();
                break;
            case R.id.refresh:
                Toast.makeText(this, "refresh", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_webview;
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void initialized() {
        registerNetChangeReceiver();
        initSet();
        if (NetUtils.isConnected(this)) {
            load();
        } else {
            showNoNetWorkDialog();
        }
    }

    private class ToastForJs {
        private Context mContext;

        public ToastForJs(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        private void showToast(String msg) {
            Toast.makeText(this.mContext, msg, Toast.LENGTH_LONG).show();
        }
    }
}
