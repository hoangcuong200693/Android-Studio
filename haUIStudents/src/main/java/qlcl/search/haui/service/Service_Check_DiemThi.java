package qlcl.search.haui.service;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import qlcl.search.haui.activity.Splash_Screen;
import qlcl.search.haui.activity.activity_XemKetQuaThi;
import qlcl.search.haui.activity.activity_XemLichThi;
import qlcl.search.haui.activity.main;
import qlcl.search.haui.adapter.KetQuaThi_Adapter;
import qlcl.search.haui.adapter.new_adapter_ketquathi;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.Process_KetQuaThi;
import qlcl.search.haui.process.Save_KetQuaThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class Service_Check_DiemThi extends Service {

	SharedPreferences share;
	String ma;
	SharedPreferences saveNotify;
	Save_KetQuaThi saveKQT;
	Obj_KetQuaThi Old_KQT;
	List<String> listKQTmoi = new ArrayList<String>();
	int count=0;//đếm số lượt tải lại

	@Override
	public void onCreate() {
		/*Toast.makeText(getApplicationContext(),
				"Service check Diem thi is running", Toast.LENGTH_SHORT).show();*/
		share = getSharedPreferences(Variable.FILENAME, 0);
		saveNotify = getSharedPreferences(Variable.FILE_NOTIFY, 0);
		saveKQT = new Save_KetQuaThi(getApplicationContext());
		ma = share.getString(Variable.KEY_SAVE, "");
		Old_KQT = saveKQT.readFromFile();

		// Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();
		if (!ma.trim().equals("") && Variable.THONG_BAO == false) {
			new Loading().execute(ma);
		}else{
			stopSelf();
		}

		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// Toast.makeText(this, "Service Stopped!", Toast.LENGTH_LONG).show();
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	class Loading extends AsyncTask<String, Void, Obj_KetQuaThi> {
		String myma;

		@Override
		protected Obj_KetQuaThi doInBackground(String... params) {
			myma = params[0];
			Process_KetQuaThi pro = new Process_KetQuaThi(myma);
			pro.load_KetQuaThi();
			return pro.getObj_ketQuaThi();
		}

		@Override
		protected void onPostExecute(Obj_KetQuaThi result) {

			if (result.getList_KetQuaThi().size() == 0) {
				count++;
				if(count<10){
				new Loading().execute(myma);
				}else{
					stopSelf();
				}
			} else {
				if(Old_KQT!=null){//nếu có dữ liệu đc lưu trữ thì mới tiến hành so sánh
	//so sanh ma sinh vien, neu trung thi se tien hanh kiem tra			
				if(result.getStudent().getMaSv().equals(Old_KQT.getStudent().getMaSv())){
					List<MonHoc> NewList = result.getList_KetQuaThi();
					List<MonHoc> OldList = Old_KQT.getList_KetQuaThi();
					
		/*			MonHoc mh=new MonHoc();
					mh.setTenMonHoc("xcv");
					mh.setDiemTBC("**");
					mh.setDiemThiL1("**");
					mh.setSoTinChi("2");
					mh.setDiemTBKT("**");
					mh.setDiemTBC("**");
					mh.setMaLopDoclap("234234");
					NewList.add(mh);*/
					
			/*		NewList.get(NewList.size()-3).setDiemTBC("**");
					NewList.get(NewList.size()-3).setDiemTinChi("**");*/
					
					int countNew = NewList.size();
					int countOld = OldList.size();

					if (countNew > countOld) {
						for (int i = countOld; i < countNew; i++) {/*
							String diemL1 = NewList.get(i).getDiemThiL1().trim();
							String diemL2 = NewList.get(i).getDiemThiL2().trim();
							if (!diemL1.equals("") || !diemL2.equals("")) {
								if(!String.valueOf(NewList.get(i).getDiemTBC()).toString().equals("")){
									listKQTmoi.add(NewList.get(i).getTenMonHoc());
								}
								
							}
						*/
							if(!NewList.get(i).getDiemTBC().equals("")){
								listKQTmoi.add(NewList.get(i).getTenMonHoc());
							}
						
						
						}
						
						if (listKQTmoi.size() > 0) {

							String stKQT = "";
							for (String st : listKQTmoi) {
								stKQT += st + ", ";
							}

							stKQT = stKQT.substring(0, stKQT.length() - 2);
							thongBao(listKQTmoi.size(), stKQT);
						}
						
					} else {
						for (int i = 0; i < NewList.size(); i++) {/*
							if(OldList.get(i).getDiemThiL1().equals("")){
								if(!NewList.get(i).getDiemThiL1().equals(OldList.get(i).getDiemThiL1()) ||!NewList.get(i).getDiemThiL2().equals(OldList.get(i).getDiemThiL2())){
									if(!String.valueOf(NewList.get(i).getDiemTBC()).toString().equals("")){
										listKQTmoi.add(NewList.get(i).getTenMonHoc());
									}
							}
							
								
							}else{
								if(NewList.get(i).getDiemTBC().equals(Old_KQT).get)){
									listKQTmoi.add(NewList.get(i).getTenMonHoc());
								}
							}
						*/
							
							if(!NewList.get(i).getDiemTBC().equals(OldList.get(i).getDiemTBC())){
								if(!String.valueOf(NewList.get(i).getDiemTBC()).toString().equals("")){
									listKQTmoi.add(NewList.get(i).getTenMonHoc());
								}
							}
						
						
						}
			
						
					
						
						if (listKQTmoi.size() > 0) {

							String stKQT = "";
							for (String st : listKQTmoi) {
								stKQT += st + ", ";
							}

							stKQT = stKQT.substring(0, stKQT.length() - 2);
							thongBao(listKQTmoi.size(), stKQT);
						}
					}
				}
				}
			if(Variable.ACTIVITY_IS_ON){
					Variable.obj_ketquathi=result;
					Student student=result.getStudent();
					activity_XemKetQuaThi.layout_KetQuaThi.setVisibility(View.VISIBLE);
					activity_XemKetQuaThi.layout_bg_KetQuaThi.setVisibility(View.INVISIBLE);
				//	activity_XemKetQuaThi.edt.setText(ma);
					activity_XemKetQuaThi.tv_hoten.setText(student.getName());
					activity_XemKetQuaThi.tv_masv.setText(student.getMaSv());
					activity_XemKetQuaThi.tv_lop.setText(student.getLop());
					activity_XemKetQuaThi.tv_timeKQT.setText("Cập nhật lúc "+result.getTime());
					new_adapter_ketquathi adapter=new new_adapter_ketquathi(result.getList_KetQuaThi(), getApplicationContext());
					activity_XemKetQuaThi.listview.setAdapter(adapter);
					
				}

				saveKQT.saveToFile(result);
				stopSelf();
				/*Toast.makeText(getApplicationContext(),
						"Service check Diem thi is stop", Toast.LENGTH_SHORT).show();*/
			}

			
			super.onPostExecute(result);
		}
	}

	void thongBao(int count, String mon) {
		Intent it = new Intent(getApplicationContext(), Splash_Screen.class);

		PendingIntent pending = PendingIntent.getActivity(
				getApplicationContext(), 0, it, 0);

		Notification notify = new Notification(R.drawable.iconhaui,
				"Môn có điểm thi mới", System.currentTimeMillis());

		notify.setLatestEventInfo(getApplicationContext(), count
				+ " môn có điểm thi :", mon, pending);
		Uri alarmSound = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		if (alarmSound == null) {
			alarmSound = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

		}

		long[] pattern = { 500, 500, 500, 500, 500, 500, 500, 500, 500 };

		notify.sound = alarmSound;
		// notify.vibrate = pattern;
		// notify.ledARGB = Color.BLUE;

		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.notify(0, notify);
		SharedPreferences.Editor editor = saveNotify.edit();
		editor.putBoolean(Variable.KEY_NOTIFY, true);
		editor.commit();
	}

}
