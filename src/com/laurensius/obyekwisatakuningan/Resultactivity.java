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
//import android.widget.Toast;

public class Resultactivity extends Activity {
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
		setContentView(R.layout.activity_result);
		WebView wv = (WebView)findViewById(R.id.wv);
		
		Intent i = getIntent();
		String method = i.getStringExtra("m");
		String param = i.getStringExtra("p");
		
		//Toast.makeText(getApplicationContext(), method + " " + param, Toast.LENGTH_SHORT).show();
		
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            	String uri_error_result = getResources().getString(R.string.uri_error_result);
            	view.loadUrl(uri_error_result);
            }
        });
		JSInterface = new JavaScriptInterface(this);
		wv.addJavascriptInterface(JSInterface, "JSInterface");
		String uri_result = getResources().getString(R.string.uri_result);
		wv.loadUrl(uri_result+"/"+method+"/"+param);
	}
	
	@Override
	public void onBackPressed(){
		finish();
		Intent i = new Intent(getApplicationContext(),Caritempatwisata.class);
		startActivity(i);
	}
	
	
	public class JavaScriptInterface {
	    Context mContext;

	    JavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    public void clickDetail(String id){
	    	Intent intent_detail = new Intent(getApplicationContext(),Detailactivity.class);
	    	intent_detail.putExtra("id", id);
	    	startActivity(intent_detail);
	    }
	    
	    public void backResult(){
	    	finish();
			Intent i = new Intent(getApplicationContext(),Caritempatwisata.class);
			startActivity(i);
	    }
	    
	    public void test(){
	    	//Toast.makeText(getApplicationContext(), "Test OK", Toast.LENGTH_SHORT).show();
	    }   
	}

}
