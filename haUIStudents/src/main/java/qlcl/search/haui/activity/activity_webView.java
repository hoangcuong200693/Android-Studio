package qlcl.search.haui.activity;


import com.startapp.android.publish.StartAppAd;

import qlcl.search.haui.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class activity_webView extends ActionBarActivity {

	WebView web;
	LinearLayout linearLayout;
	StartAppAd adv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.web_view);
		StartAppAd.init(this, "101480587", "201218331");
		adv=new StartAppAd(this);
		adv.loadAd();
		adv.showAd();
		getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
				Window.PROGRESS_VISIBILITY_ON);
		web = (WebView) findViewById(R.id.webView);
		linearLayout = (LinearLayout) findViewById(R.id.linearProcessLoading);

		// you gave
		// to the WebView in the main.xml
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setSupportZoom(true); // Zoom Control on web
		// (You
		// don't need this
		// if ROM supports Multi-Touch
		web.getSettings().setBuiltInZoomControls(true);
		web.loadUrl("http://haui.edu.vn");
		web.setWebViewClient(new client());

	}

	class client extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub

			super.onPageFinished(view, url);
			linearLayout.setVisibility(View.GONE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return super.shouldOverrideUrlLoading(view, url);
		}

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			View decorView = getWindow().getDecorView();
			decorView.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			

		}
	
	}
}
