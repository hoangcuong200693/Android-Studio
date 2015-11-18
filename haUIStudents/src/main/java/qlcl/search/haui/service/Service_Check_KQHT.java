package qlcl.search.haui.service;

import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.activity.Splash_Screen;
import qlcl.search.haui.activity.activity_KetQuaHocTap;
import qlcl.search.haui.adapter.KetQuaHocTap_Adapter;
import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.obj.Obj_KetQuaHocTap;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.Process_KetQuaHocTap;
import qlcl.search.haui.process.Process_XemLichThi;
import qlcl.search.haui.process.Save_KQHT;
import qlcl.search.haui.process.Save_LichThi;
import qlcl.search.haui.service.Service_Check_LichThi.Loading;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class Service_Check_KQHT extends Service {

	String masv;
	Save_KQHT saveKQHT;
	SharedPreferences share;
	SharedPreferences saveNotify;
	Obj_KetQuaHocTap OldKQHT;
	List<String> listKQHTmoi = new ArrayList<String>();
int count=0;//đếm lượt tải lại
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		/*
		 * Toast.makeText(getApplicationContext(),
		 * "Service check KQHT is running", Toast.LENGTH_SHORT).show();
		 */
		saveNotify = getSharedPreferences(Variable.FILE_NOTIFY, 0);
		share = getSharedPreferences(Variable.FILENAME, 0);
		saveKQHT = new Save_KQHT(getApplicationContext());
		masv = share.getString(Variable.KEY_SAVE, "");
		OldKQHT = saveKQHT.readFromFile();
		if (!masv.equals("")) {
			new Loading().execute(masv);
		} else {
			stopSelf();
		}

		super.onCreate();
	}

	class Loading extends AsyncTask<String, Void, Obj_KetQuaHocTap> {
		String ma;

		@Override
		protected Obj_KetQuaHocTap doInBackground(String... params) {
			ma = params[0];
			Process_KetQuaHocTap pro = new Process_KetQuaHocTap(ma);
			pro.load_KetQuaHocTap();
			return pro.getObj_KetQuaHocTap();
		}

		@Override
		protected void onPostExecute(Obj_KetQuaHocTap result) {

			if (result.getListKetQuaHocTap().size() == 0) {
				count++;
				if(count<10){
				new Loading().execute(ma);
				}else{
					stopSelf();
				}
			} else {
				
				if(OldKQHT!=null){//nếu có dữ liệu đc lưu trữ thì mới tiến hành so sánh
				if (result.getStudent().getMaSv()
						.equals(OldKQHT.getStudent().getMaSv())) {
					// cap nhat ket qua hoc tap
					List<KetQuaHocTap> NewList = result.getListKetQuaHocTap();
					
					/*KetQuaHocTap kq=new KetQuaHocTap();
					kq.setTenMonHoc("lap trinh android");
					NewList.add(kq);
					kq.setDiemTrungBinh("");
				
					NewList.add(kq);*/
					
					List<KetQuaHocTap> OldList = OldKQHT.getListKetQuaHocTap();

					int countNew = NewList.size();// so luong mon moi tai ve
					int countOld = OldList.size();// so luong mon duoc luu truoc
													// do

					if (countNew > countOld) {
						for (int i = countOld; i < countNew; i++) {
							String diemTB = NewList.get(i).getDiemTrungBinh()
									.trim();
							if (!diemTB.equals("")) {
								listKQHTmoi.add(NewList.get(i).getTenMonHoc());
							}
						}

						if (listKQHTmoi.size() > 0) {

							String stKQHT = "";
							for (String st : listKQHTmoi) {
								stKQHT += st + ", ";
							}

							stKQHT = stKQHT.substring(0, stKQHT.length() - 2);
							thongBao(listKQHTmoi.size(), stKQHT);
						}

					} else {
						for (int i = 0; i < NewList.size(); i++) {
							if (!NewList.get(i).getDiemTrungBinh()
									.equals(OldList.get(i).getDiemTrungBinh())) {
								listKQHTmoi.add(NewList.get(i).getTenMonHoc());
							}
						}

						if (listKQHTmoi.size() > 0) {

							String stKQHT = "";
							for (String st : listKQHTmoi) {
								stKQHT += st + ", ";
							}

							stKQHT = stKQHT.substring(0, stKQHT.length() - 2);
							thongBao(listKQHTmoi.size(), stKQHT);
						}
					}

				}
				}
				
				//Toast.makeText(getApplicationContext(), ""+Variable.ACTIVITY_IS_ON, Toast.LENGTH_SHORT).show();
				
				if(Variable.ACTIVITY_IS_ON){
					Variable.obj_ketquahoctap=result;
					activity_KetQuaHocTap.stateObj=result;
				}

				saveKQHT.saveToFile(result);
				stopSelf();
			}

			super.onPostExecute(result);
		}
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	void thongBao(int count, String mon) {
		Intent it = new Intent(getApplicationContext(), Splash_Screen.class);

		PendingIntent pending = PendingIntent.getActivity(
				getApplicationContext(), 0, it, 0);

		Notification notify = new Notification(R.drawable.iconhaui,
				"Kết quả học tập mới", System.currentTimeMillis());

		notify.setLatestEventInfo(getApplicationContext(), count
				+ " môn có điểm trên lớp :", mon, pending);
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
		nm.notify(2, notify);
		SharedPreferences.Editor editor = saveNotify.edit();
		editor.putBoolean(Variable.KEY_NOTIFY, true);
		editor.commit();
	}

}
