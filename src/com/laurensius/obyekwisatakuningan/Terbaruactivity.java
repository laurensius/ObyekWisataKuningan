package com.laurensius.obyekwisatakuningan;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Terbaruactivity extends Activity {
	public WebView wv;
	JavaScriptInterface JSInterface; 
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_terbaru);
		WebView wv = (WebView)findViewById(R.id.wv);	
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            	String uri_error_terbaru = getResources().getString(R.string.uri_error_terbaru);
            	view.loadUrl(uri_error_terbaru);
            }
        });
		JSInterface = new JavaScriptInterface(this);
		wv.addJavascriptInterface(JSInterface, "JSInterface");
		String uri_terbaru = getResources().getString(R.string.uri_terbaru);
		wv.loadUrl(uri_terbaru);
	}
	
	@Override
	public void onBackPressed(){
		finish();
		Intent i = new Intent(getApplicationContext(),Menuactivity.class);
		startActivity(i);
	}
	
	
	public class JavaScriptInterface {
	    Context mContext;

	    JavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    public void clickDetailTerbaru(String id){
	    	Intent intent_detail = new Intent(getApplicationContext(),Detailterbaruactivity.class);
	    	intent_detail.putExtra("id", id);
	    	startActivity(intent_detail);
	    }
	    
	    public void backTerbaru(){
	    	finish();
			Intent i = new Intent(getApplicationContext(),Menuactivity.class);
			startActivity(i);
	    }  
	}

}
