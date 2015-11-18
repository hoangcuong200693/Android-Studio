package qlcl.search.haui.activity;

import java.util.List;

import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.dialog.Dialog_confirm_Exit;
import qlcl.search.haui.dialog.dialog_diemthi;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Obj_TichLuy;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.ChuanHoaTichLuy;
import qlcl.search.haui.process.Process_KetQuaThi;
import qlcl.search.haui.process.Process_TinhTichTuy;
import qlcl.search.haui.process.SaveTichLuy;
import qlcl.search.haui.process.Save_HuongDanTichLuy;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieGraph.OnSliceClickedListener;
import com.echo.holographlibrary.PieSlice;

public class activity_TichLuy extends Fragment {

	PieGraph grap;
	TextView tv_tenSV, tv_maSV, tv_lop, tv_TichLuy, tv_diemA, tv_diemB,
			tv_diemC, tv_diemD, tv_diemF, tv_tinchi, tv_timeUpdate;

	ImageView btn_refresh;
	
	Save_HuongDanTichLuy save_HuongDan;//file đánh dấu là đã hiển thị  hướng dẫn chưa

	SharedPreferences share;
	String maSV;
	ChuanHoaTichLuy chuanHoa;
	Process_TinhTichTuy pro_TichLuy;
	List<String> listA;
	List<String> listB;
	List<String> listC;
	List<String> listD;
	List<String> listF;

	int colorA;
	int colorB;
	int colorC;
	int colorD;
	int colorF;

	SaveTichLuy FileTichLuy;
	Obj_TichLuy myObjTichLuy;
	// AlertDialog.Builder dialog;
	myListAdapter adapter;
	RelativeLayout layoutLoad;

	dialog_diemthi dialog;
	IsOnline isOnline;
	LinearLayout layoutLoadTichLuy, layoutLoiTichLuy;
	Button btnReloadTichLuy;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.activity_tichluy, container,false);
		

		/*// TODO Auto-generated method stub
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

		grap = (PieGraph) v.findViewById(R.id.piegraph);
		tv_tenSV = (TextView) v.findViewById(R.id.tv_hotenSV_tichluy);
		tv_maSV = (TextView) v.findViewById(R.id.tv_MaSV_tichluy);
		tv_lop = (TextView) v.findViewById(R.id.tv_lop_tichluy);
		tv_TichLuy = (TextView) v.findViewById(R.id.tv_tichluy);
		tv_diemA = (TextView) v.findViewById(R.id.tv_diemA);
		tv_diemB = (TextView) v.findViewById(R.id.tv_diemB);
		tv_diemC = (TextView) v.findViewById(R.id.tv_diemC);
		tv_diemD = (TextView) v.findViewById(R.id.tv_diemD);
		tv_diemF = (TextView) v.findViewById(R.id.tv_diemF);
		tv_tinchi = (TextView) v.findViewById(R.id.tv_tinchi);
		tv_timeUpdate = (TextView) v.findViewById(R.id.tv_timeUpdate);

		layoutLoadTichLuy = (LinearLayout) v.findViewById(R.id.layoutLoadTichLuy);
		layoutLoiTichLuy = (LinearLayout) v.findViewById(R.id.layoutLoiTichLuy);
		layoutLoiTichLuy.setVisibility(View.INVISIBLE);

		btn_refresh = (ImageView) v.findViewById(R.id.btn_refresh);
		btn_refresh.setOnClickListener(reload);

		colorA = getResources().getColor(R.color.diemA);
		colorB = getResources().getColor(R.color.diemB);
		colorC = getResources().getColor(R.color.diemC);
		colorD = getResources().getColor(R.color.diemD);
		colorF = getResources().getColor(R.color.diemF);
		
		
		save_HuongDan=new Save_HuongDanTichLuy(getActivity());

		btnReloadTichLuy = (Button) v.findViewById(R.id.btn_reloadTichLuy);
		btnReloadTichLuy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (isOnline.checkOnline()) {
					layoutLoadTichLuy.setVisibility(View.VISIBLE);
					layoutLoiTichLuy.setVisibility(View.INVISIBLE);
					Load_And_CheckConnect();

				} else {
					Dialog_No_Connect dl = new Dialog_No_Connect(getActivity(),
							"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
					dl.show();
				}
			
			}
		});

		layoutLoad = (RelativeLayout) v.findViewById(R.id.layout_loadingTichLuy);
		layoutLoad.setVisibility(View.INVISIBLE);

		// lay ma sinh vien
		share = getActivity().getSharedPreferences(Variable.FILENAME, 0);
		maSV = share.getString(Variable.KEY_SAVE, null);

		isOnline = new IsOnline(getActivity());

		// khoi tao file luu diem tich luy
		FileTichLuy = new SaveTichLuy(getActivity());
		myObjTichLuy = FileTichLuy.readFromFile();
		if (myObjTichLuy == null) {
			Load_And_CheckConnect();
		} else {
			String masvFileSave = myObjTichLuy.getmStudent().getMaSv().trim();
			if (maSV.equals(masvFileSave)) {
				LoadFromFile(myObjTichLuy);
			} else {
				if (maSV != null)
					Load_And_CheckConnect();
			}
		}
		// dialog = new AlertDialog.Builder(this);

		final OnClickListener dialogClick = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				dialog.dismiss();

			}
		};

		grap.setOnSliceClickedListener(new OnSliceClickedListener() {

			@Override
			public void onClick(int index) {
				switch (index) {
				case 0:
					dialog = new dialog_diemthi("Danh sách môn điểm A",
							getActivity(), listA, colorA);
					dialog.show();
					break;
				case 1:
					dialog = new dialog_diemthi("Danh sách môn điểm B",
							getActivity(), listB, colorB);
					dialog.show();
					break;
				case 2:
					dialog = new dialog_diemthi("Danh sách môn điểm C",
							getActivity(), listC, colorC);
					dialog.show();
					break;
				case 3:
					dialog = new dialog_diemthi("Danh sách môn điểm D",
							getActivity(), listD, colorD);
					dialog.show();
					break;
				case 4:
					dialog = new dialog_diemthi("Danh sách môn điểm F",
							getActivity(), listF, colorF);
					dialog.show();
					break;

				}

			}
		});

	
		
		return v;
	}


	android.view.View.OnClickListener reload = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			Load_And_CheckConnect();
		}
	};

	void Load_And_CheckConnect() {

		if (isOnline.checkOnline()) {
			new loadKetQuaThi().execute(maSV);
		} else {
			Dialog_No_Connect dl = new Dialog_No_Connect(getActivity(),
					"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
			dl.show();
		}

	}

	void LoadFromFile(Obj_TichLuy obj) {

		Student st = obj.getmStudent();
		boolean hople;

		tv_tenSV.setText(st.getName());
		tv_maSV.setText(st.getMaSv());
		tv_lop.setText(st.getLop());

		hople = obj.isHopLe();
		/*
		 * Toast.makeText(getApplicationContext(), "Diem tich luy " +
		 * diemTichLuy, Toast.LENGTH_SHORT).show();
		 */
		tv_TichLuy.setText(obj.getDiemTichLuy());

		int soDiemA = obj.getSoDiemA();
		int soDiemB = obj.getSoDiemB();
		int soDiemC = obj.getSoDiemC();
		int soDiemD = obj.getSoDiemD();
		int soDiemF = obj.getSoDiemF();
		int tinchi = obj.getTinchi();

		int tinchA = obj.getTinchA();
		int tinchB = obj.getTinchB();
		int tinchC = obj.getTinchC();
		int tinchD = obj.getTinchD();
		int tinchF = obj.getTinchF();

		listA = obj.getListA();
		listB = obj.getListB();
		listC = obj.getListC();
		listD = obj.getListD();
		listF = obj.getListF();

		tv_diemA.setText(soDiemA +" môn"+ " - " + tinchA + " tín chỉ");
		tv_diemB.setText(soDiemB +" môn"+ " - " + tinchB + " tín chỉ");
		tv_diemC.setText(soDiemC +" môn"+ " - " + tinchC + " tín chỉ");
		tv_diemD.setText(soDiemD +" môn"+ " - " + tinchD + " tín chỉ");
		tv_diemF.setText(soDiemF +" môn"+ " - " + tinchF + " tín chỉ");
		tv_tinchi.setText(tinchi + "");
		tv_timeUpdate.setText("Cập nhật lúc: " + obj.getTime());

		/*
		 * Toast.makeText(getApplicationContext(), "diem a " + soDiemA,
		 * Toast.LENGTH_SHORT).show();
		 */

		// grap.removeSlices();

		PieSlice slice = new PieSlice();
		slice.setColor(getResources().getColor(R.color.diemA));
		slice.setValue(soDiemA);
		grap.addSlice(slice);

		slice = new PieSlice();
		slice.setColor(getResources().getColor(R.color.diemB));
		slice.setValue(soDiemB);
		grap.addSlice(slice);

		slice = new PieSlice();
		slice.setColor(getResources().getColor(R.color.diemC));
		slice.setValue(soDiemC);
		grap.addSlice(slice);

		slice = new PieSlice();
		slice.setColor(getResources().getColor(R.color.diemD));
		slice.setValue(soDiemD);
		grap.addSlice(slice);

		if (soDiemF > 0) {
			slice = new PieSlice();
			slice.setColor(getResources().getColor(R.color.diemF));
			slice.setValue(soDiemF);
			grap.addSlice(slice);
		}

		if (!hople) {
			Dialog_confirm_Exit dl = new Dialog_confirm_Exit(
					getActivity(),
					"Lệ phí thi chưa được đóng đầy đủ, việc thống kê và tính toán sẽ không chính xác. Bạn có muốn đóng lệ phí không ?") {

				@Override
				public void OKClick() {
					// TODO Auto-generated method stub
					Intent it = new Intent(Intent.ACTION_VIEW);
					it.setData(Uri
							.parse("http://sv.qlcl.edu.vn/student/transactionmodules/lich-thi.htm"));
					startActivity(it);

				}
			};

			dl.show();
		}

	}

	void loadComplete(Obj_KetQuaThi result) {
		final Student st = result.getStudent();
		boolean hople;

		tv_tenSV.setText(st.getName());
		tv_maSV.setText(st.getMaSv());
		tv_lop.setText(st.getLop());

		chuanHoa = new ChuanHoaTichLuy(result.getList_KetQuaThi());

		pro_TichLuy = new Process_TinhTichTuy(chuanHoa.getListTichLuy());
		String diemTichLuy = pro_TichLuy.getTichLuy();
		
		if(diemTichLuy!=null){
			hople = chuanHoa.isHopLe();
			/*
			 * Toast.makeText(getApplicationContext(), "Diem tich luy " +
			 * diemTichLuy, Toast.LENGTH_SHORT).show();
			 */
			tv_TichLuy.setText(pro_TichLuy.getTichLuy());

			int soDiemA = pro_TichLuy.getSoDiemA();
			int soDiemB = pro_TichLuy.getSoDiemB();
			int soDiemC = pro_TichLuy.getSoDiemC();
			int soDiemD = pro_TichLuy.getSoDiemD();
			int soDiemF = pro_TichLuy.getSoDiemF();
			int tinchi = pro_TichLuy.getTinchi();

			int tinchA = pro_TichLuy.getTinchA();
			int tinchB = pro_TichLuy.getTinchB();
			int tinchC = pro_TichLuy.getTinchC();
			int tinchD = pro_TichLuy.getTinchD();
			int tinchF = pro_TichLuy.getTinchF();

			listA = pro_TichLuy.getListA();
			listB = pro_TichLuy.getListB();
			listC = pro_TichLuy.getListC();
			listD = pro_TichLuy.getListD();
			listF = pro_TichLuy.getListF();

			tv_diemA.setText(soDiemA +" môn"+ " - " + tinchA + " tín chỉ");
			tv_diemB.setText(soDiemB +" môn"+ " - " + tinchB + " tín chỉ");
			tv_diemC.setText(soDiemC +" môn"+ " - " + tinchC + " tín chỉ");
			tv_diemD.setText(soDiemD +" môn"+ " - " + tinchD + " tín chỉ");
			tv_diemF.setText(soDiemF +" môn"+ " - " + tinchF + " tín chỉ");
			tv_tinchi.setText(tinchi + "");
			tv_timeUpdate.setText("Cập nhật lúc: " + pro_TichLuy.getTime());

			Obj_TichLuy tichluy = new Obj_TichLuy();
			tichluy.setmStudent(st);
			tichluy.setTime(pro_TichLuy.getTime());
			tichluy.setListA(listA);
			tichluy.setListB(listB);
			tichluy.setListC(listC);
			tichluy.setListD(listD);
			tichluy.setListF(listF);
			tichluy.setSoDiemA(soDiemA);
			tichluy.setSoDiemB(soDiemB);
			tichluy.setSoDiemC(soDiemC);
			tichluy.setSoDiemD(soDiemD);
			tichluy.setSoDiemF(soDiemF);
			tichluy.setTinchA(tinchA);
			tichluy.setTinchB(tinchB);
			tichluy.setTinchC(tinchC);
			tichluy.setTinchD(tinchD);
			tichluy.setTinchF(tinchF);
			tichluy.setTinchi(tinchi);
			tichluy.setHopLe(hople);
			tichluy.setDiemTichLuy(pro_TichLuy.getTichLuy());

			if (result.getList_KetQuaThi().size() > 0) {
				FileTichLuy.saveToFile(tichluy);
			}
			// Toast.makeText(getApplicationContext(), "Luu file thanh cong",
			// Toast.LENGTH_SHORT).show();

			/*
			 * Toast.makeText(getApplicationContext(), "diem F " + soDiemF,
			 * Toast.LENGTH_SHORT).show();
			 */
			grap.removeSlices();

			PieSlice slice = new PieSlice();
			slice.setColor(getResources().getColor(R.color.diemA));
			slice.setValue(soDiemA);
			grap.addSlice(slice);

			slice = new PieSlice();
			slice.setColor(getResources().getColor(R.color.diemB));
			slice.setValue(soDiemB);
			grap.addSlice(slice);

			slice = new PieSlice();
			slice.setColor(getResources().getColor(R.color.diemC));
			slice.setValue(soDiemC);
			grap.addSlice(slice);

			slice = new PieSlice();
			slice.setColor(getResources().getColor(R.color.diemD));
			slice.setValue(soDiemD);
			grap.addSlice(slice);

			if (soDiemF > 0) {
				slice = new PieSlice();
				slice.setColor(getResources().getColor(R.color.diemF));
				slice.setValue(soDiemF);
				grap.addSlice(slice);
			}


			if (!hople) {
				Dialog_confirm_Exit dl = new Dialog_confirm_Exit(
						getActivity(),
						"Lệ phí thi chưa được đóng đầy đủ, việc thống kê và tính toán sẽ không chính xác. Bạn có muốn đóng lệ phí không ?") {

					@Override
					public void OKClick() {
						// TODO Auto-generated method stub
						Intent it = new Intent(Intent.ACTION_VIEW);
						it.setData(Uri
								.parse("http://sv.qlcl.edu.vn/student/transactionmodules/lich-thi.htm"));
						startActivity(it);
						dismiss();
					}
				};

				dl.show();
			}
		}else{

			Dialog_confirm_Exit dialogLoi=new Dialog_confirm_Exit(getActivity(),"Không thể lấy số tín chỉ của một số môn do chưa điền vào phiếu thăm dò hoặc chưa đóng lệ phí thi. Xem chi tiết?") {
				
				@Override
				public void OKClick() {
					Intent it = new Intent(Intent.ACTION_VIEW);
					it.setData(Uri
							.parse("http://qlcl.edu.vn/viewstudent/ket-qua-thi.htm?code="+st.getMaSv()));
					startActivity(it);
					dismiss();
					
				}
			};
			
			dialogLoi.show();
		
		}
		

	}

	class loadKetQuaThi extends AsyncTask<String, Void, Obj_KetQuaThi> {

		@Override
		protected void onPostExecute(Obj_KetQuaThi result) {

			// TODO Auto-generated method stub
			if (result.getList_KetQuaThi().size() != 0) {
				loadComplete(result);
				layoutLoad.setVisibility(View.INVISIBLE);
				boolean hd=save_HuongDan.isHuongDan();
				if(!hd){
					Dialog_No_Connect dl=new Dialog_No_Connect(getActivity(), "Để xem danh sách thống kê cụ thể điểm bạn hãy ấn vào các thành phần của biểu đồ");
					dl.show();
					save_HuongDan.setHuongDan();
				}
			} else {

				layoutLoadTichLuy.setVisibility(View.INVISIBLE);
				layoutLoiTichLuy.setVisibility(View.VISIBLE);
				
			

			}

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			layoutLoad.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

		@Override
		protected Obj_KetQuaThi doInBackground(String... params) {

			Process_KetQuaThi pro_KQT = new Process_KetQuaThi(params[0]);
			pro_KQT.load_KetQuaThi();
			Obj_KetQuaThi obj = pro_KQT.getObj_ketQuaThi();

			// TODO Auto-generated method stub
			return obj;
		}

	}

	class myListAdapter extends BaseAdapter {

		List<String> list;
		LayoutInflater infalter;
		Context ct;

		public myListAdapter(List<String> list) {
			super();
			this.list = list;
			ct = getActivity();
			infalter = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = infalter.inflate(R.layout.textview, null);

			}
			TextView tv = (TextView) convertView.findViewById(R.id.tv);
			tv.setText(((int) position + 1) + ". " + list.get(position));
			return convertView;
		}

	}
}
