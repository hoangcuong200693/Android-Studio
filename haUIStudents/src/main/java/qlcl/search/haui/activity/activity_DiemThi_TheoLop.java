package qlcl.search.haui.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
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

import qlcl.search.haui.activity.acticity_Student_KetQuaHocTap.load;
import qlcl.search.haui.activity.acticity_Student_KetQuaHocTap.load_Refresh;
import qlcl.search.haui.adapter.Adapter_DiemThi_TheoLop;
import qlcl.search.haui.adapter.adapter_Student_KetQuaHocTap;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.obj.Obj_DiemThi_TheoLop;
import qlcl.search.haui.obj.Obj_Student_KetQuaHocTap;
import qlcl.search.haui.obj.Student_KetQuaHocTap;
import qlcl.search.haui.obj.diemthi_theolop;
import qlcl.search.haui.obj.header_BangDanhSach;
import qlcl.search.haui.process.Process_DiemThi_TheoLop;
import qlcl.search.haui.process.Process_Student_KetQuaThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class activity_DiemThi_TheoLop extends ActionBarActivity implements
		OnClickListener {

	TextView tv_tenMon, tv_soTinhChi, tv_Lop;
	LinearLayout layout_load;
	PullToRefreshListView listview;
	String link;
	header_BangDanhSach header;
	List<diemthi_theolop> list;
	Adapter_DiemThi_TheoLop adapter;
	Button btn_reLoad;
	RelativeLayout layout_Error;
	IsOnline isOnline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#8D1B5B")));
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#ffffff'>Điểm thi theo lớp</font>"));
		setContentView(R.layout.activity_diemthi_theolop);
		//setTitle("");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		init();
		Bundle bd = getIntent().getExtras();

		link = bd.getString(Variable.KETQUATHI_LINK_DSACH);

		new load().execute();

	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	void init() {
		// nut tai lai khi bi loi
		btn_reLoad = (Button) findViewById(R.id.btn_reload);
		btn_reLoad.setOnClickListener(this);
		// layout hien thi khi load loi
		layout_Error = (RelativeLayout) findViewById(R.id.layout_error);
		layout_Error.setVisibility(View.INVISIBLE);

		isOnline = new IsOnline(this);

		tv_tenMon = (TextView) findViewById(R.id.tv_tenMon_diemthi_theoLop);
		tv_soTinhChi = (TextView) findViewById(R.id.tv_soTinChi_diemthi_theolop);
		tv_Lop = (TextView) findViewById(R.id.tv_lop_diemthi_theolop);
		listview = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_diemthi_theoLop);
		listview.setMode(Mode.PULL_FROM_END);
		listview.setRefreshingLabel("Đang tải...");
		listview.setReleaseLabel("Nhả ra để làm mới danh sách");
		listview.setPullLabel("Kéo xuống để tải thêm danh sách");
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
								activity_DiemThi_TheoLop.this,
								"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
						dl.show();
					} else {
						new load_Refresh().execute();
					}

				}
				listview.onRefreshComplete();

			}
		});

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

		layout_load = (LinearLayout) findViewById(R.id.linearProcessLoading_diemthi_theoLop);

		layout_load.setVisibility(View.VISIBLE);

		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

	}

	class load extends AsyncTask<Void, Void, Obj_DiemThi_TheoLop> {

		@Override
		protected Obj_DiemThi_TheoLop doInBackground(Void... params) {
			Process_DiemThi_TheoLop pro = new Process_DiemThi_TheoLop(link);
			pro.load();

			return pro.getObj();
		}

		@Override
		protected void onPostExecute(Obj_DiemThi_TheoLop result) {

			try {
				header = result.getHeader();
				list = result.getListDanhSachDiemThi();
				layout_load.setVisibility(View.INVISIBLE);

				if (list.size() == 0) {
					layout_Error.setVisibility(View.VISIBLE);

				} else {
					adapter = new Adapter_DiemThi_TheoLop(
							getApplicationContext(), list);
					listview.setAdapter(adapter);

					tv_tenMon.setText(header.getTenMon());
					tv_soTinhChi.setText(header.getTinChi());
					tv_Lop.setText(header.getLop());
				}

			}

			catch (Exception e) {
				layout_Error.setVisibility(View.VISIBLE);

			}

			super.onPostExecute(result);
		}

	}

	class load_Refresh extends AsyncTask<Void, Void, Obj_DiemThi_TheoLop> {

		@Override
		protected Obj_DiemThi_TheoLop doInBackground(Void... params) {

			Process_DiemThi_TheoLop pro = new Process_DiemThi_TheoLop(link
					+ header.getLinkNext());
			pro.load();

			return pro.getObj();
		}

		@Override
		protected void onPostExecute(Obj_DiemThi_TheoLop result) {

			header_BangDanhSach header_new = result.getHeader();
			header.setLinkNext(header_new.getLinkNext());
			final List<diemthi_theolop> list_new = result
					.getListDanhSachDiemThi();
			for (diemthi_theolop st : list_new) {
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
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onClick(View v) {
		if (v.getId() == btn_reLoad.getId()) {
			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(this,
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				layout_Error.setVisibility(View.INVISIBLE);
				layout_load.setVisibility(View.VISIBLE);
				new load().execute();
			}

		}

	}

}
