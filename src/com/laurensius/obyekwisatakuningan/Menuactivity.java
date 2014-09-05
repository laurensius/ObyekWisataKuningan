package com.laurensius.obyekwisatakuningan;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
//import android.widget.Toast;

public class Menuactivity extends Activity {
	
	GPSTracker gps;
	WebView wv;
	JavaScriptInterface JSInterface; 
	Dialog dialogExit;
	Button btnExitYa,btnExitTidak;
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_menu);
		WebView wv = (WebView)findViewById(R.id.wv);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){}
        });
		JSInterface = new JavaScriptInterface(this);
		wv.addJavascriptInterface(JSInterface, "JSInterface");
		String uri_menu = getResources().getString(R.string.uri_menu);
		wv.loadUrl(uri_menu);
	}
	
	@Override
	public void onBackPressed(){
		JSInterface.menuKeluar();
	}
	
	
	public class JavaScriptInterface {
	    Context mContext;

	    JavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    
	    public void menuCariTempatWisata(){
	    	finish();
	    	Intent i = new Intent(getApplicationContext(),Caritempatwisata.class);
			startActivity(i);
	    }
	    
	    public void menuTempatWisataTerbaru(){
	    	finish();
	    	Intent i = new Intent(getApplicationContext(),Terbaruactivity.class);
			startActivity(i);
	    }
	    
	    public void menuTentangAplikasi(){
	    	finish();
	    	Intent i = new Intent(getApplicationContext(), Tentangactivity.class);
	    	startActivity(i);
	    }
	    
	    public void menuKeluar(){
	    	dialogExit = new Dialog(Menuactivity.this);
			dialogExit.setContentView(R.layout.activity_dialogexit);
			dialogExit.setTitle("Konfirmasi Keluar");
			btnExitYa = (Button) dialogExit.findViewById(R.id.btnExitYa);
			btnExitTidak = (Button) dialogExit.findViewById(R.id.btnExitTidak);
			dialogExit.show();
			
			btnExitYa.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			btnExitTidak.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialogExit.dismiss();
				}
			});
	    }
	}

}
