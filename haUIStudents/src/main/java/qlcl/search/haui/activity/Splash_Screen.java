package qlcl.search.haui.activity;

import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash_Screen extends ActionBarActivity {

	SharedPreferences share_setting;
	Boolean baomat;
	SharedPreferences share_ma;
	String masv;
	ProgressBar splash_progress;
	long time=1000;
	long current=0;
	TextView tv_load;
	int demload=1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_screen);
		share_setting = PreferenceManager.getDefaultSharedPreferences(this);
		share_ma = getSharedPreferences(Variable.FILENAME, 0);
		masv = share_ma.getString(Variable.KEY_SAVE, "");
		baomat = share_setting.getBoolean("check_matKhau", false);
		
		splash_progress=(ProgressBar) findViewById(R.id.splash_progress);
		splash_progress.setMax((int)time);
		
		tv_load=(TextView) findViewById(R.id.tv_load);
		
		
		//new Thread(runable).start();
		new Thread(demo).start();
	}
	

		
	Handler hd=new Handler(){
		public void handleMessage(Message msg) {
			int so=Integer.parseInt(msg.obj.toString());
			int phantram= (int) (so*100/time);
			if(phantram==100){
				tv_load.setText("Welcome");
			}else
			tv_load.setText(phantram+" %");
			
		};
	};
	
	
	Runnable demo=new Runnable() {
		
		@Override
		public void run() {
			while(current<time){
				try {
					Thread.sleep(50);
					current+=50;
			
					Message msg=hd.obtainMessage();
					msg.obj=current;
					hd.sendMessage(msg);

					splash_progress.setProgress((int)current);
					
					if(current==time){

						
						if (baomat) {
							Intent it = new Intent(Splash_Screen.this,
									activity_DangNhap.class);
							startActivity(it);
							finish();
						} else {

							if (!masv.trim().equals("")) {
								Intent it = new Intent(Splash_Screen.this,
										main.class);
								startActivity(it);
								finish();
							}else{
								Intent it = new Intent(Splash_Screen.this,
										Activity_NhapMaSV_KHoiDau.class);
								startActivity(it);
								finish();
							}
						}

						/*overridePendingTransition(R.anim.slide_out_2,
								R.anim.slide_in_1);*/
					
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	};

	Runnable runable = new Runnable() {

		@Override
		public void run() {
			synchronized (this) {
				try {
					wait(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {}

			}

		}
	};
	
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
