package qlcl.search.haui.activity;

import qlcl.search.haui.activity.activity_XemLichThi.Loading;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.service.Service_Check_DiemThi;
import qlcl.search.haui.service.Service_Check_KQHT;
import qlcl.search.haui.service.Service_Check_LichThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class Activity_NhapMaSV_KHoiDau extends ActionBarActivity implements
		OnClickListener {

	EditText edt_nhapmasv;
	Button btn_tieptuc;
	InputMethodManager input;
	String maSV;
	SharedPreferences share_ma;
	IsOnline isOnline;
	LinearLayout layout_Loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_nhapmasv);
		share_ma = getSharedPreferences(Variable.FILENAME, 0);
		isOnline = new IsOnline(getApplicationContext());

		input = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		edt_nhapmasv = (EditText) findViewById(R.id.edt_nhapmasv);
		layout_Loading = (LinearLayout) findViewById(R.id.linearProcessLoading_nhapmasv);
		layout_Loading.setVisibility(View.INVISIBLE);
		btn_tieptuc = (Button) findViewById(R.id.btn_tieptuc);
		btn_tieptuc.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == btn_tieptuc.getId()) {
			maSV = edt_nhapmasv.getText().toString();
			input.hideSoftInputFromWindow(
					edt_nhapmasv.getApplicationWindowToken(), 0);

			if (maSV.equalsIgnoreCase("")) {
				Dialog_No_Connect dl = new Dialog_No_Connect(this,
						"Hãy nhập mã sinh viên");
				dl.show();
			} else {

				if (maSV.length() != 10) {
					Dialog_No_Connect dl = new Dialog_No_Connect(this,
							"Mã sinh viên nhập không đúng !");
					dl.show();

				} else {
					
				

					

					if (isOnline.checkOnline()) {
						
						SharedPreferences.Editor editor = share_ma.edit();
						editor.putString(Variable.KEY_SAVE, maSV);
						editor.commit();
						
						btn_tieptuc.setVisibility(View.INVISIBLE);
						
						layout_Loading.setVisibility(View.VISIBLE);
						Intent it1 = new Intent(Activity_NhapMaSV_KHoiDau.this,
								Service_Check_DiemThi.class);
						startService(it1);

						Intent it2 = new Intent(Activity_NhapMaSV_KHoiDau.this,
								Service_Check_LichThi.class);
						startService(it2);

						Intent it3 = new Intent(Activity_NhapMaSV_KHoiDau.this,
								Service_Check_KQHT.class);
						startService(it3);
						
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(10000);
									Intent it = new Intent(Activity_NhapMaSV_KHoiDau.this,
											main.class);
									startActivity(it);
									finish();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						}).start();
					} else {
						
						Dialog_No_Connect dl = new Dialog_No_Connect(Activity_NhapMaSV_KHoiDau.this,
								"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
						dl.show();

					/*	Intent it = new Intent(Activity_NhapMaSV_KHoiDau.this,
								main.class);
						startActivity(it);
						finish();*/

					}

				}

			}

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
