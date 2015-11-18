package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import qlcl.search.haui.adapter.SuggestAdapter;
import qlcl.search.haui.adapter.lichThi_Adapter;
import qlcl.search.haui.adapter.new_adapter_lichthi;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.process.Process_XemLichThi;
import qlcl.search.haui.process.Save_LichThi;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView.FindListener;
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
public class activity_XemLichThi extends Fragment implements OnClickListener,
		OnItemClickListener {

	ImageButton btnImg, btnDelete;
	public static RelativeLayout layout_lichThi, layout_bg_LichThi;
	LinearLayout layout_load;
	static List<LichThi> listLichThi;// Danh sách lịch thi
	static Student student;// đối tượng student
	public static TextView tv_hoten, tv_masv, tv_lop, tv_bg_LichThi,
			tv_timeLichThi;
	public static ListView listview;
	public static AutoCompleteTextView edt;
	IsOnline isOnline;
	// Animation animation;
	DataSQLite data;
	String maSV;
	ArrayList<String> arr;
	// ArrayAdapter<String> adapter;
	SuggestAdapter adapter;
	SharedPreferences share;
	InputMethodManager input;
	Button btn_reload;
	Save_LichThi luuLichThi;
	public static Obj_LichThi StateObj;
	Gson gson;
	public static TableRow layoutInputLichThi;//layout chua edittext va nut tim kiem

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		/*
		 * Toast.makeText(getActivity(), "on createview", Toast.LENGTH_SHORT)
		 * .show();
		 */
		/*
		 * Toast.makeText(getActivity(), "state " + savedInstanceState,
		 * Toast.LENGTH_SHORT).show();
		 */

		View v = inflater.inflate(R.layout.activity_lichthi, container, false);
		input = (InputMethodManager) getActivity().getSystemService(
				Context.INPUT_METHOD_SERVICE);
		gson = new Gson();

		luuLichThi = new Save_LichThi(getActivity());

		btnImg = (ImageButton) v.findViewById(R.id.imgBtn_xemLichThi);
		btnImg.setOnClickListener(this);
		btnDelete = (ImageButton) v.findViewById(R.id.imgBtn_delete_lichThi);
		btnDelete.setOnClickListener(this);
		tv_timeLichThi = (TextView) v.findViewById(R.id.tv_timeLichThi);
		tv_hoten = (TextView) v.findViewById(R.id.tv_hotenSV);
		tv_lop = (TextView) v.findViewById(R.id.tv_lop);
		tv_masv = (TextView) v.findViewById(R.id.tv_MaSV);
		edt = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1);
		edt.requestFocus();

		layout_lichThi = (RelativeLayout) v.findViewById(R.id.Layout_LichThi);
		layout_load = (LinearLayout) v
				.findViewById(R.id.linearProcessLoading_LichThi);
		listview = (ListView) v.findViewById(R.id.list_LichThi);
		listview.setOnItemClickListener(this);
		layout_bg_LichThi = (RelativeLayout) v
				.findViewById(R.id.layout_bg_LichTHi);
		tv_bg_LichThi = (TextView) v.findViewById(R.id.tv_Bg_LichThi);

		layout_lichThi.setVisibility(View.GONE);

		layout_load.setVisibility(View.GONE);
		
		layoutInputLichThi=(TableRow) v.findViewById(R.id.layoutInputLichThi);
		layoutInputLichThi.setVisibility(View.GONE);

		btn_reload = (Button) v.findViewById(R.id.btn_reload_lichthi);
		btn_reload.setOnClickListener(this);
		btn_reload.setVisibility(View.INVISIBLE);
		/*
		 * animation = AnimationUtils.loadAnimation(getActivity(),
		 * R.anim.rotate1); animation.setAnimationListener(this);
		 */
		onLayOutBG(" Lịch thi");

		// new Loading().execute("0641060123");
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

		Obj_LichThi myLichThi = luuLichThi.readFromFile();
		if (myLichThi != null) {
			maSV = share.getString(Variable.KEY_SAVE, "");
			if (!maSV.equals("")) {
				if (savedInstanceState == null) {
					loadComplete(myLichThi);
					StateObj = myLichThi;

				} else {
					gson = new Gson();
					String json = savedInstanceState.getString("save");
					//loadComplete(gson.fromJson(json, Obj_LichThi.class));
					loadOnResume(gson.fromJson(json, Obj_LichThi.class));
				}

			}
		}

		adapter = new SuggestAdapter(getActivity(), R.layout.adapter_suggest,
				arr);

		edt.setAdapter(adapter);
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

				adapter = new SuggestAdapter(getActivity(),
						R.layout.adapter_suggest, arrFind);
				edt.setAdapter(adapter);

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

		return v;

	}

	void onLayOutBG(String st) {
		layout_bg_LichThi.setVisibility(View.VISIBLE);
		tv_bg_LichThi.setText(st);
	}

	void OffLayOutBG() {
		layout_bg_LichThi.setVisibility(View.GONE);
	}
	
	void loadOnResume(Obj_LichThi result){
		Variable.obj_lichthi = result;
		student = result.getStudent();
		listLichThi = result.getList_LichThi();

		layout_load.setVisibility(View.GONE);
		layout_lichThi.setVisibility(View.VISIBLE);
		OffLayOutBG();
		layoutInputLichThi.setVisibility(View.GONE);
		// layout_lichThi.startAnimation(animation);

		tv_hoten.setText(student.getName());
		tv_masv.setText(student.getMaSv());
		tv_lop.setText(student.getLop());
		tv_timeLichThi.setText("Cập nhật lúc " + result.getTime());

		new_adapter_lichthi adapter = new new_adapter_lichthi(listLichThi,
				getActivity());

		listview.setAdapter(adapter);
	}

	void loadComplete(Obj_LichThi result) {

		Variable.obj_lichthi = result;
		student = result.getStudent();
		listLichThi = result.getList_LichThi();

		layout_load.setVisibility(View.GONE);

		if (listLichThi.size() == 0) {
			btn_reload.setVisibility(View.VISIBLE);
			onLayOutBG("Không tìm thấy hoặc đường truyền mạng lỗi!\nVui lòng thử lại !");
		} else {
			if (!arr.contains(maSV)) {
				data.insert(maSV);

			}

			layout_lichThi.setVisibility(View.VISIBLE);
			OffLayOutBG();
			layoutInputLichThi.setVisibility(View.GONE);
			// layout_lichThi.startAnimation(animation);

			tv_hoten.setText(student.getName());
			tv_masv.setText(student.getMaSv());
			tv_lop.setText(student.getLop());
			tv_timeLichThi.setText("Cập nhật lúc " + result.getTime());

			new_adapter_lichthi adapter = new new_adapter_lichthi(listLichThi,
					getActivity());

			listview.setAdapter(adapter);
			if (share.getString(Variable.KEY_SAVE, "").equals(
					result.getStudent().getMaSv())) {
				luuLichThi.saveToFile(result);
			}

		}
	}

	class Loading extends AsyncTask<String, Void, Obj_LichThi> {

		@Override
		protected Obj_LichThi doInBackground(String... params) {
			String ma = params[0];
			Process_XemLichThi pro = new Process_XemLichThi(ma);
			pro.load_LichThi();
			return pro.getObj_lichTHi();
		}

		@Override
		protected void onPostExecute(Obj_LichThi result) {

			loadComplete(result);
			StateObj = result;

			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == btnImg.getId()) {
			/*
			 * 
			 * Sự kiện onclick cho nút tìm kiếm
			 */
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
								getActivity(), "Mã sinh viên nhập không đúng !");
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int vt, long arg3) {
		// LichThi list_Detail = listLichThi.get(vt);
		Intent it = new Intent(getActivity(), activity_ChiTietLichThi.class);
		it.putExtra("vt", vt);
		startActivity(it);

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
