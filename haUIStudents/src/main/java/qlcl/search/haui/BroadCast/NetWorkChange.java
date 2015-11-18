package qlcl.search.haui.BroadCast;

import qlcl.search.haui.activity.IsOnline;
import qlcl.search.haui.service.Service_Check_DiemThi;
import qlcl.search.haui.service.Service_Check_KQHT;
import qlcl.search.haui.service.Service_Check_LichThi;
import qlcl.search.haui.variable.Variable;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class NetWorkChange extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		SharedPreferences catdat = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean thongbao = catdat.getBoolean("check_thongbao", true);
		boolean thongbaoLichThi = catdat.getBoolean("check_thongbaoLichThi",
				true);
		boolean thongbaoKQHT = catdat.getBoolean("check_thongbaoKQHT", true);
		String time_st = catdat.getString("time", "1800000");
	//	String time_st = "20000";
		// Toast.makeText(context, "time "+time_st, Toast.LENGTH_SHORT).show();
		long time = Long.parseLong(time_st);
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		// kiem tra dieu kien chay service diem thi
		if (thongbao) {
			Intent it = new Intent(context, Service_Check_DiemThi.class);

			if (info != null) {
				if (info.isConnected()) {
					// start service

					//context.startService(it);

					PendingIntent pending = PendingIntent.getService(context,
							0, it, PendingIntent.FLAG_UPDATE_CURRENT);
					AlarmManager am = (AlarmManager) context
							.getSystemService(context.ALARM_SERVICE);

					am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), time,
							pending);

				} else {
					// stop service
					context.stopService(it);
				}
			} else {
				context.stopService(it);
				PendingIntent pending = PendingIntent.getService(context, 0,
						it, 0);
				AlarmManager am = (AlarmManager) context
						.getSystemService(context.ALARM_SERVICE);
				am.cancel(pending);
		/*		Toast.makeText(context, "Tat hien gio "+time_st,
				 Toast.LENGTH_SHORT).show();*/
			}

		}

		if (thongbaoLichThi) {
			Intent it = new Intent(context, Service_Check_LichThi.class);

			if (info != null) {
				if (info.isConnected()) {
					// start service

					//context.startService(it);

					PendingIntent pending = PendingIntent.getService(context,
							0, it, 0);
					AlarmManager am = (AlarmManager) context
							.getSystemService(context.ALARM_SERVICE);

					am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), time,
							pending);

				} else {
					// stop service
					context.stopService(it);
				}
			} else {
				context.stopService(it);
				PendingIntent pending = PendingIntent.getService(context, 0,
						it, 0);
				AlarmManager am = (AlarmManager) context
						.getSystemService(context.ALARM_SERVICE);
				am.cancel(pending);
				// Toast.makeText(context, "Tat hien gio "+time_st,
				// Toast.LENGTH_SHORT).show();
			}

		}
		
		//check KQHT
		
		if (thongbaoKQHT) {
			Intent it = new Intent(context, Service_Check_KQHT.class);

			if (info != null) {
				if (info.isConnected()) {
					// start service

			//context.startService(it);

					PendingIntent pending = PendingIntent.getService(context,
							0, it, 0);
					AlarmManager am = (AlarmManager) context
							.getSystemService(context.ALARM_SERVICE);

					am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), time,
							pending);

				} else {
					// stop service
					context.stopService(it);
				}
			} else {
				context.stopService(it);
				PendingIntent pending = PendingIntent.getService(context, 0,
						it, 0);
				AlarmManager am = (AlarmManager) context
						.getSystemService(context.ALARM_SERVICE);
				am.cancel(pending);
				// Toast.makeText(context, "Tat hien gio "+time_st,
				// Toast.LENGTH_SHORT).show();
			}

		}

	}

}
