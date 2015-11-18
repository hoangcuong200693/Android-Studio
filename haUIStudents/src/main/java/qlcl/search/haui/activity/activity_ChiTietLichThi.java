package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.adapter.adapter_viewpager_Chitietlichthi;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class activity_ChiTietLichThi extends MyActivity {

	/*
	 * TextView tv_monThi, tv_ngayThi, tv_caThi, tv_SBD, tv_lanThi, tv_phongThi,
	 * tv_DiaDiem, tv_LePhi, tv_ghiChu;
	 */

	public static ViewPager pager;
	int vt;
	public static SlidingMenu menuslide;

	List<String> mlist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#228654")));
		setContentView(R.layout.viewpager);
		pager = (ViewPager) findViewById(R.id.view_pager_detail);
		getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Lịch thi</font>"));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Bundle bd = getIntent().getExtras();
		vt = bd.getInt("vt");
		Variable.POSITION_LICHTHI = vt;

		// add to slide menu
		List<LichThi> list_LichThi = Variable.obj_lichthi.getList_LichThi();
		for (LichThi lt : list_LichThi) {
			mlist.add(lt.getTenMonThi());
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
				.replace(R.id.menu_frame, new fragment_slide(mlist, "Lịch thi", fragment_slide.TYPE_LICHTHI)).commit();

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		adapter_viewpager_Chitietlichthi adapter = new adapter_viewpager_Chitietlichthi(
				Variable.obj_lichthi.getList_LichThi(), this, inflater);
		pager.setAdapter(adapter);
		pager.setCurrentItem(vt);
		final float MIN_SCALE = 0.75f;
		pager.setPageTransformer(true, new ViewPager.PageTransformer() {

			@SuppressLint("NewApi")
			@Override
			public void transformPage(View v, float position) {
				int pageWidth = v.getWidth();

				if (position < -1) { // [-Infinity,-1)
					// This page is way off-screen to the left.
					v.setAlpha(0);

				} else if (position <= 0) { // [-1,0]
					// Use the default slide transition when moving to the left
					// page
					v.setAlpha(1);
					v.setTranslationX(0);
					v.setScaleX(1);
					v.setScaleY(1);

				} else if (position <= 1) { // (0,1]
					// Fade the page out.
					v.setAlpha(1 - position);

					// Counteract the default slide transition
					v.setTranslationX(pageWidth * -position);

					// Scale the page down (between MIN_SCALE and 1)
					float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
					v.setScaleX(scaleFactor);
					v.setScaleY(scaleFactor);

				} else { // (1,+Infinity]
					// This page is way off-screen to the right.
					v.setAlpha(0);
				}
			}
		});
		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int vt) {
				int current = pager.getCurrentItem();
				Variable.POSITION_LICHTHI = current;

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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.menu_share:

			Student st = Variable.obj_lichthi.getStudent();
			LichThi lt = Variable.obj_lichthi.getList_LichThi().get(pager.getCurrentItem());
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT,
					"Lịch thi môn: " + lt.getTenMonThi().toUpperCase() + "\n" + st.getName() + "\nLớp: " + st.getLop()
							+ "\nNgày: " + lt.getNgayThi() + "\nCa thi: " + lt.getCaThi() + "\nSBD: " + lt.getSBD()
							+ "\nĐịa điểm: " + lt.getPhongThi() + "\nLệ thi: " + lt.getLePhiThi()
							+ "\nhttps://play.google.com/store/apps/details?id=qlcl.search.haui_qlcl");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);

			break;
		case R.id.menu_chon:
			menuslide.toggle();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_chitietlichthi, menu);

		return super.onCreateOptionsMenu(menu);
	}

}
