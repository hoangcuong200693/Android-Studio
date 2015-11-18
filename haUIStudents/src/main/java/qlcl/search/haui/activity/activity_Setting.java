package qlcl.search.haui.activity;

import qlcl.search.haui.service.Service_Check_DiemThi;
import qlcl.search.haui.service.Service_Check_KQHT;
import qlcl.search.haui.service.Service_Check_LichThi;
import qlcl.search.haui.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class activity_Setting extends PreferenceActivity {

	CheckBoxPreference check_matkhau;
	CheckBoxPreference check_ThongBao;
	CheckBoxPreference check_lichThi;
	CheckBoxPreference check_KQHT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("Cài đặt");
		addPreferencesFromResource(R.xml.menu);
		check_ThongBao = (CheckBoxPreference) findPreference("check_thongbao");

		check_ThongBao.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				Boolean bl = (Boolean) newValue;
				Intent it = new Intent(getApplicationContext(), Service_Check_DiemThi.class);

				if (bl) {

					SharedPreferences catdat = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

					String time_st = catdat.getString("time", "3600000");
					long time = Long.parseLong(time_st);

					ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
							.getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo info = cm.getActiveNetworkInfo();
					if (info != null) {
						if (info.isConnected()) {
							// start service

							startService(it);

							PendingIntent pending = PendingIntent.getService(getApplicationContext(), 0, it, 0);
							AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
							am.setRepeating(AlarmManager.RTC_WAKEUP, time, time, pending);

						} else {
							// stop service
							stopService(it);
						}
					} else {
						stopService(it);
					}

				} else {
					stopService(it);
				}

				return true;
			}
		});

		// check lich thi
		check_lichThi = (CheckBoxPreference) findPreference("check_thongbaoLichThi");
		check_lichThi.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				Boolean bl = (Boolean) newValue;
				Intent it = new Intent(getApplicationContext(), Service_Check_LichThi.class);

				if (bl) {

					SharedPreferences catdat = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

					String time_st = catdat.getString("time", "3600000");
					long time = Long.parseLong(time_st);

					ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
							.getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo info = cm.getActiveNetworkInfo();
					if (info != null) {
						if (info.isConnected()) {
							// start service

							startService(it);

							PendingIntent pending = PendingIntent.getService(getApplicationContext(), 0, it, 0);
							AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
							am.setRepeating(AlarmManager.RTC_WAKEUP, time, time, pending);

						} else {
							// stop service
							stopService(it);
						}
					} else {
						stopService(it);
					}

				} else {
					stopService(it);
				}

				return true;
			}
		});

		// check KQHT
		check_KQHT = (CheckBoxPreference) findPreference("check_thongbaoKQHT");
		check_KQHT.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				Boolean bl = (Boolean) newValue;
				Intent it = new Intent(getApplicationContext(), Service_Check_KQHT.class);

				if (bl) {

					SharedPreferences catdat = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

					String time_st = catdat.getString("time", "3600000");
					long time = Long.parseLong(time_st);

					ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
							.getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo info = cm.getActiveNetworkInfo();
					if (info != null) {
						if (info.isConnected()) {
							// start service

							startService(it);

							PendingIntent pending = PendingIntent.getService(getApplicationContext(), 0, it, 0);
							AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
							am.setRepeating(AlarmManager.RTC_WAKEUP, time, time, pending);

						} else {
							// stop service
							stopService(it);
						}
					} else {
						stopService(it);
					}

				} else {
					stopService(it);
				}

				return true;
			}
		});

		check_matkhau = (CheckBoxPreference) findPreference("check_matKhau");

		// check_matkhau.ge
		check_matkhau.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {

				Boolean bien = (Boolean) newValue;
				if (bien) {
					Intent it = new Intent(getApplicationContext(), activity_nhapMatKhauMoi.class);
					startActivity(it);
					finish();
				} else {
					Intent it = new Intent(getApplicationContext(), activity_xacnhanMK.class);
					startActivity(it);
					finish();
				}
				return true;
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			View decorView = getWindow().getDecorView();
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

		}
	}

}
