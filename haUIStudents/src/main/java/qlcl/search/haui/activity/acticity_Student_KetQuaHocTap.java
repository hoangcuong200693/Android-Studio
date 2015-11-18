package qlcl.search.haui.activity;

import java.util.List;

import qlcl.search.haui.adapter.adapter_Student_KetQuaHocTap;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.obj.Obj_Student_KetQuaHocTap;
import qlcl.search.haui.obj.Student_KetQuaHocTap;
import qlcl.search.haui.obj.header_BangDanhSach;
import qlcl.search.haui.process.Process_Student_KetQuaThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class acticity_Student_KetQuaHocTap extends MyActivity implements
		OnItemClickListener, OnClickListener {

	TextView tv_tenMon, tv_soTinhChi, tv_Lop;
	LinearLayout layout_load;
	PullToRefreshListView listview;
	String link;
	header_BangDanhSach header;
	List<Student_KetQuaHocTap> list;
	adapter_Student_KetQuaHocTap adapter;
	RelativeLayout layout_error;
	Button btn_reload;
	IsOnline isOnline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FF5900")));
		getSupportActionBar()
				.setTitle(
						Html.fromHtml("<font color='#ffffff'>Bảng điểm thành phần</font>"));
		setContentView(R.layout.activity_student_ketquahoctap);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Bundle bd = getIntent().getExtras();
		link = bd.getString(Variable.KETQUAHOCTAP_DANHSACHLOP);
		init();
		new load().execute();

	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	void init() {

		btn_reload = (Button) findViewById(R.id.btn_reload_kqht);
		btn_reload.setOnClickListener(this);
		layout_error = (RelativeLayout) findViewById(R.id.layout_error_kqht);
		layout_error.setVisibility(View.INVISIBLE);

		isOnline = new IsOnline(this);

		tv_tenMon = (TextView) findViewById(R.id.tv_tenMon_Student_KetQuaHocTap);
		tv_soTinhChi = (TextView) findViewById(R.id.tv_soTinChi_Student_KetQuaHocTap);
		tv_Lop = (TextView) findViewById(R.id.tv_lop_Student_KetQuaHocTap);
		layout_load = (LinearLayout) findViewById(R.id.linearProcessLoading_Student_KetQuaHocTap);

		listview = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_tb);
		listview.setMode(Mode.PULL_FROM_END);
		listview.setRefreshingLabel("Đang tải...");
		listview.setReleaseLabel("Nhả ra để làm mới danh sách");
		listview.setPullLabel("Kéo xuống để tải thêm danh sách");
		listview.setOnItemClickListener(this);
		listview.setLoadingDrawable(getResources().getDrawable(
				R.drawable.background_sprint_small));
		listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(
						getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				if (!header.getLinkNext().trim().equals("")) {

					if (!isOnline.checkOnline()) {
						Dialog_No_Connect dl = new Dialog_No_Connect(
								acticity_Student_KetQuaHocTap.this,
								"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
						dl.show();
					} else {
						new load_Refresh().execute();
					}

				}
				listview.onRefreshComplete();

			}
		});

		/*
		 * Su kien khi keo xuong cuoi listview
		 */

		listview.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				if (!header.getLinkNext().trim().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Kéo xuống để tải thêm !", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Đã tải hết danh sách !", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

		layout_load.setVisibility(View.VISIBLE);

		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

	}

	/*
	 * tai ket qua hoc tap
	 */

	class load extends AsyncTask<Void, Void, Obj_Student_KetQuaHocTap> {

		@Override
		protected Obj_Student_KetQuaHocTap doInBackground(Void... params) {
			Process_Student_KetQuaThi pro = new Process_Student_KetQuaThi(link);
			pro.load();

			return pro.getObj();
		}

		@Override
		protected void onPostExecute(Obj_Student_KetQuaHocTap result) {

			try {

				Variable.obj_ketquahoctaptheolop = result;
				header = result.getHeader();
				list = result.getListDanhSach();
				layout_load.setVisibility(View.INVISIBLE);

				adapter = new adapter_Student_KetQuaHocTap(
						getApplicationContext(), list);
				listview.setAdapter(adapter);

				tv_tenMon.setText(header.getTenMon());
				tv_soTinhChi.setText(header.getTinChi());
				tv_Lop.setText(header.getLop());

			}

			catch (Exception e) {

				layout_error.setVisibility(View.VISIBLE);

			}
			super.onPostExecute(result);
		}

	}

	/*
	 * phuong thuc pull to refresh, tai ket qua hoc tap khi pull listview
	 */

	class load_Refresh extends AsyncTask<Void, Void, Obj_Student_KetQuaHocTap> {

		@Override
		protected Obj_Student_KetQuaHocTap doInBackground(Void... params) {

			Process_Student_KetQuaThi pro = new Process_Student_KetQuaThi(link
					+ header.getLinkNext());
			pro.load();

			return pro.getObj();
		}

		@Override
		protected void onPostExecute(Obj_Student_KetQuaHocTap result) {

			header_BangDanhSach header_new = result.getHeader();
			header.setLinkNext(header_new.getLinkNext());
			final List<Student_KetQuaHocTap> list_new = result
					.getListDanhSach();
			for (Student_KetQuaHocTap st : list_new) {
				// Variable.obj_ketquahoctaptheolop.getListDanhSach().add(st);
				list.add(st);
			}
			// layout_load.setVisibility(View.INVISIBLE);

			adapter.notifyDataSetChanged();
			listview.setAdapter(adapter);

			listview.onRefreshComplete();
			listview.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					listview.getRefreshableView().setSelection(
							list.size() - list_new.size() - 1);
				}
			});
			super.onPostExecute(result);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int vt, long arg3) {
		/*
		 * Toast.makeText(getApplicationContext(), "vt" + vt,
		 * Toast.LENGTH_SHORT) .show();
		 */

		Intent it = new Intent(acticity_Student_KetQuaHocTap.this,
				activity_chitiet_student_ketquahoctap.class);
		it.putExtra("vt", vt - 1);

		startActivity(it);

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
	public void onClick(View v) {

		if (v.getId() == btn_reload.getId()) {

			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(this,
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				layout_error.setVisibility(View.INVISIBLE);
				layout_load.setVisibility(View.VISIBLE);
				new load().execute();
			}

		}
		// TODO Auto-generated method stub

	}
}
