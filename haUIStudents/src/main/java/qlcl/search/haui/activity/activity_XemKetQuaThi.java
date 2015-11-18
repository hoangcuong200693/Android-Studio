package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import qlcl.search.haui.activity.activity_XemLichThi.Loading;
import qlcl.search.haui.adapter.KetQuaThi_Adapter;
import qlcl.search.haui.adapter.SuggestAdapter;
import qlcl.search.haui.adapter.lichThi_Adapter;
import qlcl.search.haui.adapter.new_adapter_ketquathi;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.dialog.Dialog_confirm_Exit;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.Process_KetQuaThi;
import qlcl.search.haui.process.Save_KetQuaThi;
import qlcl.search.haui.service.Service_Check_DiemThi;
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

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class activity_XemKetQuaThi extends Fragment implements OnClickListener,
		OnItemClickListener {

	static List<MonHoc> listKetQuaThi;
	static Student student;
	ImageButton btnImg, btnDelete;
	public static RelativeLayout layout_KetQuaThi, layout_bg_KetQuaThi;
	LinearLayout layout_load;
	public static TextView tv_hoten, tv_masv, tv_lop, tv_bg_KetQuaThi,
			tv_timeKQT;
	public static ListView listview;
	public static AutoCompleteTextView edt;
	// KetQuaThi_Adapter adapter;
	new_adapter_ketquathi adapter;
	IsOnline isOnline;
	// Animation animation;
	DataSQLite data;
	String maSV;
	ArrayList<String> arr;
	SuggestAdapter adapter1;// adapter cua autocomplete textview
	LayoutInflater inflater;
	SharedPreferences share;
	InputMethodManager input;
	Button btn_reload;
	SharedPreferences saveNotify;
	Save_KetQuaThi saveKQT;
	public static TableRow layoutInputKQT;
	Obj_KetQuaThi StateObj;
	Gson gson;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater
				.inflate(R.layout.activity_ketquathi, container, false);
		input = (InputMethodManager) getActivity().getSystemService(
				Context.INPUT_METHOD_SERVICE);

		saveNotify = getActivity()
				.getSharedPreferences(Variable.FILE_NOTIFY, 0);
		saveKQT = new Save_KetQuaThi(getActivity());

		btnImg = (ImageButton) v.findViewById(R.id.imgBtn_xemKetQuaThi);
		btnImg.setOnClickListener(this);

		btnDelete = (ImageButton) v.findViewById(R.id.imgBtn_delete_ketquathi);
		btnDelete.setOnClickListener(this);
		btnDelete.setVisibility(View.INVISIBLE);

		btn_reload = (Button) v.findViewById(R.id.btn_reload_ketquathi);
		btn_reload.setOnClickListener(this);
		btn_reload.setVisibility(View.INVISIBLE);

		tv_timeKQT = (TextView) v.findViewById(R.id.tv_timeKetQuaThi);
		tv_hoten = (TextView) v.findViewById(R.id.tv_hotenSV_KetQuaThi);
		tv_masv = (TextView) v.findViewById(R.id.tv_MaSV_KetQuaThi);
		tv_lop = (TextView) v.findViewById(R.id.tv_lop_KetQuaThi);
		edt = (AutoCompleteTextView) v
				.findViewById(R.id.autoCompleteTextView1_KetQuaThi);
		listview = (ListView) v.findViewById(R.id.list_KetQuaThi);
		inflater = (LayoutInflater) getActivity().getSystemService(
				getActivity().LAYOUT_INFLATER_SERVICE);
		LinearLayout bottom = (LinearLayout) inflater.inflate(
				R.layout.tv_bottom_list, null);
		listview.addFooterView(bottom);
		listview.setOnItemClickListener(this);
		layout_KetQuaThi = (RelativeLayout) v
				.findViewById(R.id.Layout_KetQuaThi);
		layout_load = (LinearLayout) v
				.findViewById(R.id.linearProcessLoading_KetQuaThi);
		layout_KetQuaThi.setVisibility(View.GONE);
		layout_load.setVisibility(View.GONE);
		layout_bg_KetQuaThi = (RelativeLayout) v
				.findViewById(R.id.layout_bg_KetQuaThi);

		// layout chứa edittext và nút tìm kiếm
		layoutInputKQT = (TableRow) v.findViewById(R.id.layoutInputKQT);
		layoutInputKQT.setVisibility(View.GONE);

		tv_bg_KetQuaThi = (TextView) v.findViewById(R.id.tv_Bg_KetQuaThi);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		data = new DataSQLite(getActivity());
		arr = data.getAllData();

		// lay ma sinh vien duoc luu cho len edittext
		share = getActivity().getSharedPreferences(Variable.FILENAME, 0);
		// edt.setText(share.getString(Variable.KEY_SAVE, ""));
		if (edt.getText().toString().trim().equals("")) {
			btnDelete.setVisibility(View.INVISIBLE);
		} else {
			btnDelete.setVisibility(View.VISIBLE);
		}
		OffLayOutBG();
		Obj_KetQuaThi myKQT = saveKQT.readFromFile();
		if (myKQT != null) {
			maSV = share.getString(Variable.KEY_SAVE, "");
			if (!maSV.equals("")) {
				if (savedInstanceState == null) {
					loadComplete(myKQT);
					StateObj = myKQT;
				} else {
					gson = new Gson();
					String json = savedInstanceState.getString("save");
					// loadComplete(gson.fromJson(json, Obj_KetQuaThi.class));
					loadOnResume(gson.fromJson(json, Obj_KetQuaThi.class));
				}

			}
		} else {
			// Toast.makeText(getActivity(), "obj null",
			// Toast.LENGTH_SHORT).show();
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
		/*
		 * animation = AnimationUtils.loadAnimation(getActivity(),
		 * R.anim.bound); animation.setAnimationListener(this);
		 */
		// onLayOutBG("Kết quả thi");

		// TODO Auto-generated method stub
		return v;
	}

	void onLayOutBG(String st) {
		layout_bg_KetQuaThi.setVisibility(View.VISIBLE);
		tv_bg_KetQuaThi.setText(st);
	}

	void OffLayOutBG() {
		layout_bg_KetQuaThi.setVisibility(View.GONE);
	}

	void loadOnResume(Obj_KetQuaThi result) {
		Variable.obj_ketquathi = result;

		student = result.getStudent();
		listKetQuaThi = result.getList_KetQuaThi();

		layout_load.setVisibility(View.GONE);
		layout_KetQuaThi.setVisibility(View.VISIBLE);
		layoutInputKQT.setVisibility(View.GONE);
		OffLayOutBG();
		// layout_KetQuaThi.startAnimation(animation);
		tv_hoten.setText(student.getName());
		tv_masv.setText(student.getMaSv());
		tv_lop.setText(student.getLop());
		tv_timeKQT.setText("Cập nhật lúc " + result.getTime());
		adapter = new new_adapter_ketquathi(listKetQuaThi, getActivity());
		listview.setAdapter(adapter);
	}

	void loadComplete(Obj_KetQuaThi result) {
		Variable.obj_ketquathi = result;

		student = result.getStudent();
		listKetQuaThi = result.getList_KetQuaThi();

		layout_load.setVisibility(View.GONE);
		if (listKetQuaThi.size() == 0) {

			btn_reload.setVisibility(View.VISIBLE);
			onLayOutBG("Không tìm thấy hoặc đường truyền mạng lỗi!\nVui lòng thử lại !");
		} else {
			if (!arr.contains(maSV)) {
				data.insert(maSV);

			}

			layout_KetQuaThi.setVisibility(View.VISIBLE);
			layoutInputKQT.setVisibility(View.GONE);
			OffLayOutBG();
			// layout_KetQuaThi.startAnimation(animation);
			tv_hoten.setText(student.getName());
			tv_masv.setText(student.getMaSv());
			tv_lop.setText(student.getLop());
			tv_timeKQT.setText("Cập nhật lúc " + result.getTime());
			adapter = new new_adapter_ketquathi(listKetQuaThi, getActivity());
			listview.setAdapter(adapter);

			if (share.getString(Variable.KEY_SAVE, "").equals(
					edt.getText().toString())) {
				saveKQT.saveToFile(result);
			}
		}
	}

	class Loading extends AsyncTask<String, Void, Obj_KetQuaThi> {

		@Override
		protected Obj_KetQuaThi doInBackground(String... params) {
			String ma = params[0];
			Process_KetQuaThi pro = new Process_KetQuaThi(ma);
			pro.load_KetQuaThi();
			return pro.getObj_ketQuaThi();
		}

		@Override
		protected void onPostExecute(Obj_KetQuaThi result) {
			loadComplete(result);
			super.onPostExecute(result);
		}

	}

	public void tracuu() {

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
					Dialog_No_Connect dl = new Dialog_No_Connect(getActivity(),
							"Mã sinh viên nhập không đúng");
					dl.show();
				} else {
					OffLayOutBG();
					layout_load.setVisibility(View.VISIBLE);
					new Loading().execute(maSV);
				}

			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int vt, long arg3) {

		if (vt < listKetQuaThi.size()) {
			MonHoc mh = listKetQuaThi.get(vt);
			Intent it = new Intent(getActivity(),
					activity_ChiTietKetQuaThi.class);
			it.putExtra("vt", vt);

			it.putExtra("link_code",
					"http://qlcl.edu.vn/viewstudent/ket-qua-thi.htm?code="
							+ maSV);

			startActivity(it);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == btnImg.getId()) {
			tracuu();
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

	@Override
	public void onResume() {
		if (Variable.THAMDO == true) {

			/*Intent it1 = new Intent(getActivity(), Service_Check_DiemThi.class);
			getActivity().startService(it1);*/

		} else {

			listview.setSelection(Variable.POSITION_KETQUATHI);
		}

		Variable.THAMDO = false;
		super.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		gson = new Gson();
		String json = gson.toJson(StateObj);
		outState.putString("save", json);
	}

}
