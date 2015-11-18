package qlcl.search.haui.activity;

import qlcl.search.haui.service.Service_Check_DiemThi;
import qlcl.search.haui.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


/**
 * 
 * 
 * @author Eo
 * widget to check Ketquathi
 *
 */
public class act_widget extends AppWidgetProvider{

	@Override
	public void onReceive(Context context, Intent intent) {
	
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews remote;
		ComponentName component;
		//DateFormat format=SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
		remote=new RemoteViews(context.getPackageName(),R.layout.widget);
		component=new ComponentName(context, act_widget.class);
		//remote.setTextViewText(R.id.tv_time, format.format(new Date()));
		Intent it=new Intent(context, Service_Check_DiemThi.class);
		PendingIntent pd=PendingIntent.getService(context, 0, it, 0);
		remote.setOnClickPendingIntent(R.id.btn_check_diemthi, pd);
		appWidgetManager.updateAppWidget(appWidgetIds, remote);
		//appWidgetManager.updateAppWidget( component, remote );
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	

}
