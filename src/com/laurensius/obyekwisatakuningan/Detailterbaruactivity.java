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


public class Detailterbaruactivity extends Activity {
	WebView wv;
	JavaScriptInterface JSInterface;
	GPSTracker gps;
	double latitude,longitude;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_detailterbaru);
		WebView wv = (WebView)findViewById(R.id.wv);
		
		Intent i = getIntent();
		String id = i.getStringExtra("id");
		
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            	String uri_error_detail_terbaru = getResources().getString(R.string.uri_error_detail_terbaru);
            	view.loadUrl(uri_error_detail_terbaru);
            }
        });

		gps = new GPSTracker(Detailterbaruactivity.this);
		if(gps.canGetLocation()){        	
			latitude = gps.getLatitude();
		    longitude = gps.getLongitude();
		    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
		}else{
			gps.showSettingsAlert();
		}
		
		JSInterface = new JavaScriptInterface(this);
		wv.addJavascriptInterface(JSInterface, "JSInterface");
		String uri_detail_terbaru = getResources().getString(R.string.uri_detail_terbaru);
		wv.loadUrl(uri_detail_terbaru+"/"+id+"/"+latitude+"/"+longitude);
	}
	
	
	
	public class JavaScriptInterface {
	    Context mContext;

	    JavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    public void backTerbaru(){
	    	finish();
	    }
	}

}
