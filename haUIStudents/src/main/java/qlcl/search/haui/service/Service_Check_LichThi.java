package qlcl.search.haui.service;

import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.activity.Option_Menu;
import qlcl.search.haui.activity.Splash_Screen;
import qlcl.search.haui.activity.activity_XemLichThi;
import qlcl.search.haui.activity.main;
import qlcl.search.haui.adapter.lichThi_Adapter;
import qlcl.search.haui.adapter.new_adapter_lichthi;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.Process_KetQuaThi;
import qlcl.search.haui.process.Process_XemLichThi;
import qlcl.search.haui.process.SaveInfo;
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

public class Service_Check_LichThi extends Service {

	String ma;
	Save_LichThi saveLichThi;
	SaveInfo saveInfo;
	SharedPreferences share;
	SharedPreferences saveNotify;
	Obj_LichThi Old_LichThi;
	List<String> listLichThiMoi = new ArrayList<String>();
	int count = 0;// đếm số lượt tải lại

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {

		/*
		 * Toast.makeText(getApplicationContext(),
		 * "Service check Lich Thi is running", Toast.LENGTH_SHORT).show();
		 */

		saveNotify = getSharedPreferences(Variable.FILE_NOTIFY, 0);
		share = getSharedPreferences(Variable.FILENAME, 0);
		saveLichThi = new Save_LichThi(getApplicationContext());
		saveInfo = new SaveInfo(getApplicationContext());
		Old_LichThi = saveLichThi.readFromFile();
		ma = share.getString(Variable.KEY_SAVE, "");

		if (!ma.equals("")) {
			new Loading().execute(ma);
		} else {
			stopSelf();
		}
		super.onCreate();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	class Loading extends AsyncTask<String, Void, Obj_LichThi> {
		String ma;

		@Override
		protected Obj_LichThi doInBackground(String... params) {
			ma = params[0];
			Process_XemLichThi pro = new Process_XemLichThi(ma);
			pro.load_LichThi();
			return pro.getObj_lichTHi();
		}

		@Override
		protected void onPostExecute(Obj_LichThi result) {
			// nếu dữ liệu rỗng thì tăng biến cout cho phép lặp 10 lần

			if (result.getList_LichThi().size() == 0) {
				count++;
				if (count < 10) {
					new Loading().execute(ma);
				} else {
					stopSelf();
				}
			} else {
				if (Old_LichThi != null) {// nếu có dữ liệu đc lưu trữ thì mới
											// tiến hành so sánh
					if (result.getStudent().getMaSv()
							.equals(Old_LichThi.getStudent().getMaSv())) {
						List<LichThi> newList = result.getList_LichThi();

						/*
						 * LichThi lt=new LichThi();
						 * lt.setTenMonThi("Lập trình Hibernate");
						 * newList.add(lt); newList.add(lt);
						 */

						List<LichThi> oldList = Old_LichThi.getList_LichThi();

						int countNew = newList.size();
						int countOld = oldList.size();

						if (countNew > countOld) {
							for (int i = countOld; i < countNew; i++) {
								listLichThiMoi.add(newList.get(i)
										.getTenMonThi());
							}

							String stLichThi = "";
							for (String st : listLichThiMoi) {
								stLichThi += st + ", ";
							}

							stLichThi = stLichThi.substring(0,
									stLichThi.length() - 2);

							thongBao(listLichThiMoi.size(), stLichThi);
						}

					}
				}
				Student student = result.getStudent();
				if (Variable.ACTIVITY_IS_ON) {

					Variable.obj_lichthi = result;
					main.pager.setCurrentItem(0);
					activity_XemLichThi.StateObj = result;

					activity_XemLichThi.layout_lichThi
							.setVisibility(View.VISIBLE);
					activity_XemLichThi.layout_bg_LichThi
							.setVisibility(View.INVISIBLE);
					// activity_XemLichThi.edt.setText(ma);
					activity_XemLichThi.tv_hoten.setText(student.getName());
					activity_XemLichThi.tv_masv.setText(student.getMaSv());
					activity_XemLichThi.tv_lop.setText(student.getLop());
					activity_XemLichThi.tv_timeLichThi.setText("Cập nhật lúc "
							+ result.getTime());
					new_adapter_lichthi adapter = new new_adapter_lichthi(
							result.getList_LichThi(), getApplicationContext());
					activity_XemLichThi.listview.setAdapter(adapter);
					
					//thay doi thong tin Option
					Option_Menu.tvName.setText(student.getName());
					Option_Menu.tvCode.setText(student.getMaSv());
					Option_Menu.tvClass.setText(student.getLop());
				}

				saveLichThi.saveToFile(result);
				// luu thong tin sinh vien vao file info
				saveInfo.saveToFile(student.getName() + "-" + student.getMaSv()
						+ "-" + student.getLop());

				

				stopSelf();
			}

			super.onPostExecute(result);
		}
	}

	void thongBao(int count, String mon) {
		Intent it = new Intent(getApplicationContext(), Splash_Screen.class);

		PendingIntent pending = PendingIntent.getActivity(
				getApplicationContext(), 0, it, 0);

		Notification notify = new Notification(R.drawable.iconhaui,
				"Lịch thi mới", System.currentTimeMillis());

		notify.setLatestEventInfo(getApplicationContext(), count
				+ " môn có lịch thi :", mon, pending);
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
