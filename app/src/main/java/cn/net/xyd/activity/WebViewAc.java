package cn.net.xyd.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import cn.net.xyd.R;
import cn.net.xyd.receiver.NetStateReceiver;
import cn.net.xyd.utils.DialogUtil;
import cn.net.xyd.utils.NetUtils;

public class WebViewAc
        extends BaseAc {
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
    private NetStateReceiver receiver;

    private String getStrFromID(int paramInt) {
        return getResources().getString(paramInt);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initSet() {
        this.mWebView = ((WebView) findViewById(R.id.webview));
        this.dialog = DialogUtil.createLoadingDialog(this, getStrFromID(R.string.loading));
        this.currentURL = getResources().getString(R.string.base_url);
        this.mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings localWebSettings = this.mWebView.getSettings();
        localWebSettings.setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(new ToastForJs(this), "androidObj");
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setLoadWithOverviewMode(true);
        localWebSettings.setCacheMode(1);
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
            public void onPageFinished(WebView webView, String paramAnonymousString) {
                super.onPageFinished(webView, paramAnonymousString);
            }

            public void onPageStarted(WebView webView, String paramAnonymousString, Bitmap paramAnonymousBitmap) {
                super.onPageStarted(webView, paramAnonymousString, paramAnonymousBitmap);
            }

            public void onReceivedError(WebView webView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2) {
                super.onReceivedError(webView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
                Toast.makeText(WebViewAc.this, "同步失败，请稍候再试", Toast.LENGTH_LONG).show();
            }

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

    private void load() {
        this.mHandler.sendEmptyMessage(1);
        this.mWebView.loadUrl(this.currentURL);
    }

    private void registerNetChangeReceiver() {
        this.receiver = new NetStateReceiver();
        Log.i("TAG", "register receiver android.net.conn.CONNECTIVITY_CHANGE");
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        localIntentFilter.setPriority(1000);
        registerReceiver(this.receiver, localIntentFilter);
    }

    private void showNoNetWorkDialog() {
        new AlertDialog.Builder(this).setTitle(getStrFromID(R.string.dialog_title)).setMessage(getStrFromID(R.string.dialog_msg)).setPositiveButton(getStrFromID(R.string.dialog_positive_btn), new OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                NetUtils.openSetting(WebViewAc.this);
            }
        }).setNegativeButton(getStrFromID(R.string.dialog_negative_btn), new OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                WebViewAc.this.finish();
            }
        }).show();
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        if (paramInt1 == RESULT_OK) {
            Log.e("WebViewAc", "reloading");
            if (this.mWebView != null) {
                this.mWebView.loadUrl(this.currentURL);
            }
        }
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    protected void onCreate(@Nullable Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.ac_webview);
        registerNetChangeReceiver();
        initSet();
        if (NetUtils.isConnected(this)) {
            load();
        } else {
            showNoNetWorkDialog();
        }
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
                this.mWebView.goBack();
                return true;
            } else if (System.currentTimeMillis() - this.exitTime > 2000L) {
                this.exitTime = System.currentTimeMillis();
                Toast.makeText(this, getResources().getString(R.string.press_again), Toast.LENGTH_LONG).show();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class ToastForJs {
        private Context mContext;

        public ToastForJs(Context paramContext) {
            this.mContext = paramContext;
        }

        @JavascriptInterface
        private void showToast(String paramString) {
            Toast.makeText(this.mContext, paramString, Toast.LENGTH_LONG).show();
        }
    }
}
