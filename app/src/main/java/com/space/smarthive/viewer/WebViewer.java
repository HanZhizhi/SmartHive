package com.space.smarthive.viewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gyf.immersionbar.ImmersionBar;
import com.space.smarthive.R;
import com.space.smarthive.databinding.ActivityWebViewerBinding;
import com.space.smarthive.utils.TextUtil;

/*public class WebViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewer);
    }

    public static void start(Context context, String url, String title){
        Intent i = new Intent(context,WebViewer.class);
        Bundle bd = new Bundle();
        bd.putString("url", url);
        bd.putString("title", title);
        i.putExtras(bd);
        context.startActivity(i);
    }
}*/

public class WebViewer extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "WebViewer";
    ActivityWebViewerBinding viewBinding;
    private WebView webView;
    private String url, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityWebViewerBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        Intent intent=getIntent();
        Bundle data=null;
        if (intent == null || (data=intent.getExtras()) == null){
            //ToastAndCloseActivity("no intent");
            return;
        }

        url = data.getString("url"); title = data.getString("title");

        if (TextUtil.isEmptyString(url) || TextUtil.isEmptyString(title)){
            //ToastAndCloseActivity("no url or title");
            return;
        }

        initViews();
        doBussiness();
    }

    private void initViews(){
        ImmersionBar.with(this)
                .statusBarColor(R.color.darkWhite)
                .navigationBarColor(R.color.darkWhite)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();

        viewBinding.tvViewTitle.setText(getTitle());
        viewBinding.ivViewrBack.setOnClickListener(this);
        viewBinding.ivViewrShare.setOnClickListener(this);

        webView = viewBinding.urlViewer;

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);

        //设置可以访问文件
        settings.setAllowFileAccess(true);
        //设置支持缩放
        settings.setBuiltInZoomControls(true);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url==null) return true;

                try {
                    if(url.startsWith("weixin://") //微信
                            || url.startsWith("alipays://") //支付宝
                            || url.startsWith("mailto://") //邮件
                            || url.startsWith("tel://")//电话
                            || url.startsWith("dianping://")//大众点评
                            || url.startsWith("openapp.jdmobile://")//京东
                            || url.startsWith("jianshu://")
                        //其他自定义的scheme
                    ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    private void doBussiness(){
        webView.loadUrl(url);
    }

    public static void start(Context context, String url, String title){
        Intent i = new Intent(context,WebViewer.class);
        Bundle bd = new Bundle();
        bd.putString("url", url);
        bd.putString("title", title);
        i.putExtras(bd);
        context.startActivity(i);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_viewr_back:
                finish();
                break;
            case R.id.iv_viewr_share:
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(textIntent, "分享链接"));
                break;
        }
    }
}