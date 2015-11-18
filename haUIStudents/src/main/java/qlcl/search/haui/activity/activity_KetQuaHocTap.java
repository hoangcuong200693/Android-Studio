package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import qlcl.search.haui.activity.activity_XemKetQuaThi.Loading;
import qlcl.search.haui.adapter.KetQuaHocTap_Adapter;
import qlcl.search.haui.adapter.KetQuaThi_Adapter;
import qlcl.search.haui.adapter.SuggestAdapter;
import qlcl.search.haui.adapter.new_adapter_kqht;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.obj.Obj_KetQuaHocTap;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.Process_KetQuaHocTap;
import qlcl.search.haui.process.Save_KQHT;
import qlcl.search.haui.sqlite.DataSQLite;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTitleStrip;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class activity_KetQuaHocTap extends Fragment implements
		OnClickListener, OnItemClickListener {

	List<KetQuaHocTap> listKetQuaHocTap;
	Student student;
	ImageButton btnImg, btnDelete;
	public static RelativeLayout layout_KetQuaHT, layout_bg_KetQuaHocTap;
	LinearLayout layout_load;
	public static TextView tv_hoten, tv_masv, tv_lop, tv_bg_KetQuaHocTap,tv_timeKQHT;;
	public static	ListView listview;
	public static AutoCompleteTextView edt;
	//KetQuaHocTap_Adapter adapter;
	new_adapter_kqht adapter;
	IsOnline isOnline;

	DataSQLite data;
	LayoutInflater inflater;
	String maSV;
	ArrayList<String> arr;
	SuggestAdapter adapter1;
	SharedPreferences share;
	InputMethodManager input;
	Button btn_reload;
	Save_KQHT saveKQHT;
	public static	Obj_KetQuaHocTap stateObj;
	public static	TableRow layoutInputKQHT;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

/*		Toast.makeText(getActivity(), "on createview", Toast.LENGTH_SHORT)
		.show();
	Toast.makeText(getActivity(), "state " + savedInstanceState,
		Toast.LENGTH_SHORT).show();*/

		View v = inflater.inflate(R.layout.activity_ketquahoctap, container,
				false);

		input = (InputMethodManager) getActivity().getSystemService(
				Context.INPUT_METHOD_SERVICE);
		btnImg = (ImageButton) v.findViewById(R.id.imgBtn_xemKetQuaHocTap);
		btnImg.setOnClickListener(this);

		btnDelete = (ImageButton) v
				.findViewById(R.id.imgBtn_delete_KetQuaHocTap);
		btnDelete.setOnClickListener(this);
		btnDelete.setVisibility(View.INVISIBLE);

		btn_reload = (Button) v.findViewById(R.id.btn_reload_ketquahoctap);
		btn_reload.setOnClickListener(this);
		btn_reload.setVisibility(View.INVISIBLE);

		//layout chứa edittext và nút tìm kiếm
		layoutInputKQHT=(TableRow) v.findViewById(R.id.layoutInputKQHT);
		layoutInputKQHT.setVisibility(View.GONE);
		
		layout_KetQuaHT = (RelativeLayout) v
				.findViewById(R.id.Layout_KetQuaHocTap);
		layout_load = (LinearLayout) v
				.findViewById(R.id.linearProcessLoading_KetQuaHocTap);
		tv_timeKQHT=(TextView) v.findViewById(R.id.tv_timeKetQuaHT);
		tv_hoten = (TextView) v.findViewById(R.id.tv_hotenSV_KetQuaHocTap);
		tv_lop = (TextView) v.findViewById(R.id.tv_lop_KetQuaHocTap);
		tv_masv = (TextView) v.findViewById(R.id.tv_MaSV_KetQuaHocTap);
		layout_KetQuaHT.setVisibility(View.GONE);
		layout_load.setVisibility(View.GONE);
		edt = (AutoCompleteTextView) v
				.findViewById(R.id.autoCompleteTextView1_KetQuaHocTap);
		listview = (ListView) v.findViewById(R.id.list_KetQuaHocTap);
		inflater = (LayoutInflater) getActivity().getSystemService(
				getActivity().LAYOUT_INFLATER_SERVICE);
		LinearLayout bottom = (LinearLayout) inflater.inflate(
				R.layout.tv_bottom_list, null);
		listview.addFooterView(bottom);
		listview.setOnItemClickListener(this);
		layout_bg_KetQuaHocTap = (RelativeLayout) v
				.findViewById(R.id.layout_bg_KetQuaHocTap);
		tv_bg_KetQuaHocTap = (TextView) v.findViewById(R.id.tv_Bg_KetQuaHocTap);

		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		onLayOutBG("Kết quả học tập");
		data = new DataSQLite(getActivity());
		arr = data.getAllData();
		// lay ma sinh vien duoc luu cho len edittext
		share = getActivity().getSharedPreferences(Variable.FILENAME, 0);
	//	edt.setText(share.getString(Variable.KEY_SAVE, ""));
		if (edt.getText().toString().trim().equals("")) {
			btnDelete.setVisibility(View.INVISIBLE);
		} else {
			btnDelete.setVisibility(View.VISIBLE);
		}
		
		saveKQHT=new Save_KQHT(getActivity());
		Obj_KetQuaHocTap myKQHT=saveKQHT.readFromFile();
		if (myKQHT != null) {
			maSV = share.getString(Variable.KEY_SAVE, "");
			if (!maSV.equals("")) {
				if (savedInstanceState == null) {
					loadComplete(myKQHT);
					stateObj=myKQHT;
				} else {
					Gson gson = new Gson();
					String json = savedInstanceState.getString("save");
				//	loadComplete(gson.fromJson(json, Obj_KetQuaHocTap.class));
				loadOnResume(gson.fromJson(json, Obj_KetQuaHocTap.class));
				}

			}
		}

		
		

		adapter1 = new SuggestAdapter(getActivity(), R.layout.adapter_suggest,
				arr);

		edt.setAdapter(adapter1);
		edt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				ArrayList<String> arrFind = new ArrayList<String>();
				for (String st : arr) {
					if (st.indexOf(s.toString()) == 0) {
						arrFind.add(st);
					}
				}

				adapter1 = new SuggestAdapter(getActivity(),
						R.layout.adapter_suggest, arrFind);
				edt.setAdapter(adapter1);

				if (s.toString().length() > 0) {
					btnDelete.setVisibility(View.VISIBLE);
				} else {
					btnDelete.setVisibility(View.INVISIBLE);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		isOnline = new IsOnline(getActivity());
		

		// TODO Auto-generated method stub
		return v;
	}

	void onLayOutBG(String st) {
		layout_bg_KetQuaHocTap.setVisibility(View.VISIBLE);
		tv_bg_KetQuaHocTap.setText(st);
	}

	void OffLayOutBG() {
		layout_bg_KetQuaHocTap.setVisibility(View.GONE);
	}
	
	void loadOnResume(Obj_KetQuaHocTap result){
		Variable.obj_ketquahoctap = result;

		student = result.getStudent();
		listKetQuaHocTap = result.getListKetQuaHocTap();
		layout_load.setVisibility(View.GONE);
		OffLayOutBG();
		layoutInputKQHT.setVisibility(View.GONE);

		layout_KetQuaHT.setVisibility(View.VISIBLE);
		tv_hoten.setText(student.getName());
		tv_masv.setText(student.getMaSv());
		tv_lop.setText(student.getLop());
		tv_timeKQHT.setText("Cập nhật lúc "+result.getTime());
		adapter = new new_adapter_kqht(listKetQuaHocTap, getActivity());

		listview.setAdapter(adapter);
	}

	void loadComplete(Obj_KetQuaHocTap result) {

		Variable.obj_ketquahoctap = result;

		student = result.getStudent();
		listKetQuaHocTap = result.getListKetQuaHocTap();
		layout_load.setVisibility(View.GONE);
		if (listKetQuaHocTap.size() == 0) {
			btn_reload.setVisibility(View.VISIBLE);
			onLayOutBG("Không tìm thấy hoặc đường truyền mạng lỗi!\nVui lòng thử lại !");
		} else {

			if (!arr.contains(maSV)) {
				data.insert(maSV);

			}
			
			OffLayOutBG();
			layoutInputKQHT.setVisibility(View.GONE);

			layout_KetQuaHT.setVisibility(View.VISIBLE);
			tv_hoten.setText(student.getName());
			tv_masv.setText(student.getMaSv());
			tv_lop.setText(student.getLop());
			tv_timeKQHT.setText("Cập nhật lúc "+result.getTime());
			adapter = new new_adapter_kqht(listKetQuaHocTap, getActivity());

			listview.setAdapter(adapter);
			
			//kiểm tra nếu mã được lưu giống mã vừa load giống nhau hay k, nếu giống thì lưu kết quả 
			if(share.getString(Variable.KEY_SAVE, "").equals(result.getStudent().getMaSv())){
				saveKQHT.saveToFile(result);
			}
			

		}
	}

	class Loading extends AsyncTask<String, Void, Obj_KetQuaHocTap> {

		@Override
		protected Obj_KetQuaHocTap doInBackground(String... params) {
			String ma = params[0];
			Process_KetQuaHocTap pro = new Process_KetQuaHocTap(ma);
			pro.load_KetQuaHocTap();
			return pro.getObj_KetQuaHocTap();
		}

		@Override
		protected void onPostExecute(Obj_KetQuaHocTap result) {
			loadComplete(result);
			stateObj=result;

			super.onPostExecute(result);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int vt, long arg3) {

		if (vt < listKetQuaHocTap.size()) {
			KetQuaHocTap kq = listKetQuaHocTap.get(vt);
			Intent it = new Intent(getActivity(),
					activity_ChiTietKetQuaHocTap.class);
			it.putExtra("vt", vt);

			startActivity(it);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == btnImg.getId()) {
			maSV = edt.getText().toString();

			input.hideSoftInputFromWindow(edt.getApplicationWindowToken(), 0);

			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(getActivity(),
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				if (maSV.equalsIgnoreCase("")) {
					Dialog_No_Connect dl = new Dialog_No_Connect(getActivity(),
							"Hãy nhập mã sinh viên");
					dl.show();
				} else {

					if (maSV.length() != 10) {
						Dialog_No_Connect dl = new Dialog_No_Connect(
								getActivity(), "Mã sinh viên nhập không đúng");
						dl.show();

					} else {
						OffLayOutBG();
						layout_load.setVisibility(View.VISIBLE);

						// layout_lichThi.setVisibility(View.VISIBLE);
						new Loading().execute(maSV);
					}

				}
			}

		}

		if (v.getId() == btnDelete.getId()) {
			edt.setText("");
			edt.requestFocus();
			edt.setFocusableInTouchMode(true);
			input.showSoftInput(edt, InputMethodManager.SHOW_FORCED);
			btnDelete.setVisibility(View.INVISIBLE);
		}

		if (v.getId() == btn_reload.getId()) {
			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(getActivity(),
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				OffLayOutBG();
				layout_load.setVisibility(View.VISIBLE);

				// layout_lichThi.setVisibility(View.VISIBLE);
				new Loading().execute(maSV);
			}

		}

	}

	/*@Override
	public void onResume() {

		if (Variable.obj_ketquahoctap != null) {

			if (Variable.obj_ketquahoctap.getListKetQuaHocTap().size() > 0) {

				student = Variable.obj_ketquahoctap.getStudent();
				listKetQuaHocTap = Variable.obj_ketquahoctap
						.getListKetQuaHocTap();
				layout_load.setVisibility(View.GONE);

				layout_KetQuaHT.setVisibility(View.VISIBLE);
				OffLayOutBG();
				tv_hoten.setText(student.getName());
				tv_masv.setText(student.getMaSv());
				tv_lop.setText(student.getLop());
				adapter = new KetQuaHocTap_Adapter(listKetQuaHocTap,
						getActivity());

				listview.setAdapter(adapter);
				listview.setSelection(Variable.POSITION_KETQUAHOCTAP);

			}
		}

		super.onResume();
	}*/
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		Gson gson = new Gson();
		String json = gson.toJson(stateObj);
		outState.putString("save", json);
	}

}
