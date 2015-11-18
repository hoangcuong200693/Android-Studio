package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import qlcl.search.haui.adapter.adapter_viewpager_ChitietKetQuaThi;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.dialog.Dialog_confirm_Exit;
import qlcl.search.haui.obj.KetQuaThi;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class activity_ChiTietKetQuaThi extends MyActivity {

	String diem, link_code, linkDanhSach;
	IsOnline isOnline;
	public static ViewPager pager;
	int vt;
	public static SlidingMenu menuslide;
	List<String> mlist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#807FBE")));
		getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Kết quả thi</font>"));
		Bundle bd = getIntent().getExtras();
		vt = bd.getInt("vt");

		Variable.POSITION_KETQUATHI = vt;// luu lai vi tri vua click

		setContentView(R.layout.viewpager);
		init();
		List<MonHoc> list_KetQuaThi = Variable.obj_ketquathi.getList_KetQuaThi();
		for (MonHoc mh : list_KetQuaThi) {
			mlist.add(mh.getTenMonHoc());
		}
		menuslide = new SlidingMenu(this);
		menuslide.setShadowWidth(R.dimen.shadow_width);
		// sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		menuslide.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menuslide.setFadeDegree(0.35f);
		menuslide.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		menuslide.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		menuslide.setMode(SlidingMenu.RIGHT);
		menuslide.setBehindScrollScale(0.0f);
		menuslide.setMenu(R.layout.menu_frame);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new fragment_slide(mlist, "Lịch thi", fragment_slide.TYPE_KETQUATHI))
				.commit();

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		adapter_viewpager_ChitietKetQuaThi adapter = new adapter_viewpager_ChitietKetQuaThi(
				Variable.obj_ketquathi.getList_KetQuaThi(), this, inflater);
		pager.setAdapter(adapter);
		pager.setCurrentItem(vt);
		pager.setPageTransformer(true, new ViewPager.PageTransformer() {
			private static final float MIN_SCALE = 0.85f;
			private static final float MIN_ALPHA = 0.5f;

			@SuppressLint("NewApi")
			@Override
			public void transformPage(View view, float position) {
				int pageWidth = view.getWidth();
				int pageHeight = view.getHeight();

				if (position < -1) { // [-Infinity,-1)
					// This page is way off-screen to the left.
					view.setAlpha(0);

				} else if (position <= 1) { // [-1,1]
					// Modify the default slide transition to shrink the page as
					// well
					float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
					float vertMargin = pageHeight * (1 - scaleFactor) / 2;
					float horzMargin = pageWidth * (1 - scaleFactor) / 2;
					if (position < 0) {
						view.setTranslationX(horzMargin - vertMargin / 2);
					} else {
						view.setTranslationX(-horzMargin + vertMargin / 2);
					}

					// Scale the page down (between MIN_SCALE and 1)
					view.setScaleX(scaleFactor);
					view.setScaleY(scaleFactor);

					// Fade the page relative to its size.
					view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

				} else { // (1,+Infinity]
					// This page is way off-screen to the right.
					view.setAlpha(0);
				}
			}

		});
		isOnline = new IsOnline(getApplicationContext());

		diem = (Variable.obj_ketquathi.getList_KetQuaThi().get(pager.getCurrentItem()).getDiemTBC());

		link_code = "http://sv.qlcl.edu.vn/student/transactionmodules/lich-thi.htm";
		if (diem.equals("**")) {
			Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(activity_ChiTietKetQuaThi.this,
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

		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				Variable.POSITION_KETQUATHI = pager.getCurrentItem();

				diem = (Variable.obj_ketquathi.getList_KetQuaThi().get(pager.getCurrentItem()).getDiemTBC());

				link_code = "http://sv.qlcl.edu.vn/student/transactionmodules/lich-thi.htm";
				if (diem.equals("**")) {
					Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(activity_ChiTietKetQuaThi.this,
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

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu_ketquathi, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.menu_shareKQT:
			Student st = Variable.obj_ketquathi.getStudent();
			MonHoc mh = Variable.obj_ketquathi.getList_KetQuaThi().get(pager.getCurrentItem());
			String diemthi;
			if (mh.getDiemThiL2().equals("")) {
				diemthi = mh.getDiemThiL1();
			} else {
				diemthi = mh.getDiemThiL2();
			}
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT,
					"Điểm thi môn: : " + mh.getTenMonHoc().toUpperCase() + "\n" + st.getName() + "\nLớp: " + st.getLop()
							+ "\nĐiểm thi: " + diemthi + "\nĐiểm chung bình chung: " + mh.getDiemTBC()
							+ "\nĐiểm tín chỉ: " + mh.getDiemTinChi()
							+ "\nhttps://play.google.com/store/apps/details?id=qlcl.search.haui_qlcl");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);

			break;

		case R.id.menu_chon:
			menuslide.toggle();
			break;

		case R.id.menu_xemtheolop:
			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(activity_ChiTietKetQuaThi.this,
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				linkDanhSach = Variable.obj_ketquathi.getList_KetQuaThi().get(pager.getCurrentItem()).getLinkTheoLop();
				Intent it1 = new Intent(activity_ChiTietKetQuaThi.this, activity_DiemThi_TheoLop.class);
				it1.putExtra(Variable.KETQUATHI_LINK_DSACH, linkDanhSach);
				startActivity(it1);
			}
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	public void init() {

		pager = (ViewPager) findViewById(R.id.view_pager_detail);
	}

}
