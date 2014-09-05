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
import android.widget.Spinner;
import android.widget.EditText;

@SuppressLint("DefaultLocale")
public class Caritempatwisata extends Activity {
	
	GPSTracker gps;
	JavaScriptInterface JSInterface; 
	Dialog dialogExit;
	Button btnExitYa,btnExitTidak,btn_cari;
	EditText et;
	Spinner sp;
	
	
	@SuppressLint({ "SetJavaScriptEnabled", "DefaultLocale" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_caritempatwisata);
		
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
		String uri_caritempatwisata = getResources().getString(R.string.uri_caritempatwisata);
		wv.loadUrl(uri_caritempatwisata);		
		//---------------
//		gps = new GPSTracker(Caritempatwisata.this);
//        if(gps.canGetLocation()){        	
//        	double latitude = gps.getLatitude();
//        	double longitude = gps.getLongitude();
//        	//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
//        }else{
//        	gps.showSettingsAlert();
//        }
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
	    
	    public void buttonCari(String text, String param){
	    	char tmp_chr[] = new char[text.length()];
	    	String method;
	    	for(int ctr=0;ctr<text.length();ctr++){
	    		if(text.charAt(ctr)==' '){
	    			tmp_chr[ctr]='_';
	    		}else{
	    			tmp_chr[ctr]=text.charAt(ctr);
	    		}
	    	}
	    	method = String.valueOf(tmp_chr);
	    	
	    	//Toast.makeText(getApplicationContext(), method +" "+ param, Toast.LENGTH_SHORT).show();
	    	finish();
	    	Intent intent_result = new Intent(getApplicationContext(),Resultactivity.class);
	    	intent_result.putExtra("m", method);
	    	intent_result.putExtra("p", param);
	    	startActivity(intent_result);
	    }
	    
	    public void menuKeluar(){
	    	dialogExit = new Dialog(Caritempatwisata.this);
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
