package com.laurensius.obyekwisatakuningan;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Splashscreen extends Activity {
	WebView wv;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_splashscreen);
		WebView wv = (WebView)findViewById(R.id.wv);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){
				Thread timer = new Thread() {
					public void run() {
						try {
							sleep(3500);
							finish();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							Intent i = new Intent(getApplicationContext(),Menuactivity.class);
							startActivity(i);
						}
					}
				};
				timer.start();
			}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){}
        });
		String uri_splash = getResources().getString(R.string.uri_splash);
		wv.loadUrl(uri_splash);
	}
}
