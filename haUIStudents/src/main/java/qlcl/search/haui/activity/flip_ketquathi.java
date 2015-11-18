package qlcl.search.haui.activity;

import qlcl.search.haui.adapter.adapter_flip_ketquathi;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.dialog.Dialog_confirm_Exit;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/*import com.aphidmobile.flip.FlipViewController;
import com.aphidmobile.flip.FlipViewController.ViewFlipListener;*/

public class flip_ketquathi extends ActionBarActivity {/*

	String diem, link_code, linkDanhSach;
	IsOnline isOnline;
	int vt;
	MenuItem menudanhgia;
	FlipViewController flip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Kết quả thi");
		flip = new FlipViewController(this, FlipViewController.HORIZONTAL);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Bundle bd = getIntent().getExtras();
		vt = bd.getInt("vt");

		Variable.POSITION_KETQUATHI = vt;// luu lai vi tri vua click

		setContentView(R.layout.viewpager);

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		adapter_flip_ketquathi adapter = new adapter_flip_ketquathi(
				Variable.obj_ketquathi.getList_KetQuaThi(), this, inflater);

		flip.setAdapter(adapter);
		flip.setSelection(vt);
		setContentView(flip);
		isOnline = new IsOnline(getApplicationContext());

		diem = (Variable.obj_ketquathi.getList_KetQuaThi().get(
				flip.getSelectedItemPosition()).getDiemTBC());

		link_code = "http://sv.qlcl.edu.vn/student/transactionmodules/lich-thi.htm";
		if (diem.equals("**")) {
			Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(
					flip_ketquathi.this,
					"Bạn cần đóng lệ phí thi để xem điểm môn này . Bạn có muốn đóng không ?") {

				@Override
				public void OKClick() {
					Variable.THAMDO = true;
					Intent it = new Intent(Intent.ACTION_VIEW);
					it.setData(Uri.parse(link_code));
					startActivity(it);
					this.dismiss();

				}
			};
			dialog.show();
		}
		flip.setOnViewFlipListener(new ViewFlipListener() {

			@Override
			public void onViewFlipped(View view, int position) {

				Variable.POSITION_KETQUATHI = position;

				diem = (Variable.obj_ketquathi.getList_KetQuaThi()
						.get(position).getDiemTBC());

				link_code = "http://sv.qlcl.edu.vn/student/transactionmodules/lich-thi.htm";
				if (diem.equals("**")) {
					Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(
							flip_ketquathi.this,
							"Bạn cần đóng lệ phí thi để xem điểm môn này . Bạn có muốn đóng không ?") {

						@Override
						public void OKClick() {
							Variable.THAMDO = true;
							Intent it = new Intent(Intent.ACTION_VIEW);
							it.setData(Uri.parse(link_code));
							startActivity(it);
							this.dismiss();

						}
					};
					dialog.show();

				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getSupportMenuInflater().inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.menu_chon:

			break;

		case R.id.menu_xemtheolop:
			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(
						flip_ketquathi.this,
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				linkDanhSach = Variable.obj_ketquathi.getList_KetQuaThi()
						.get(flip.getSelectedItemPosition()).getLinkTheoLop();
				Intent it1 = new Intent(flip_ketquathi.this,
						activity_DiemThi_TheoLop.class);
				it1.putExtra(Variable.KETQUATHI_LINK_DSACH, linkDanhSach);
				startActivity(it1);
			}
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onMenuItemSelected(int id, MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onMenuItemSelected(id, item);
	}
*/}
