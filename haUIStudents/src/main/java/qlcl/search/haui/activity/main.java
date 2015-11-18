package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.adapter.FragmentAdapter;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.dialog.Dialog_confirm_Exit;
import qlcl.search.haui.service.Service_Check_DiemThi;
import qlcl.search.haui.service.Service_Check_KQHT;
import qlcl.search.haui.service.Service_Check_LichThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.startapp.android.publish.StartAppAd;

public class main extends ActionBarActivity implements OnClickListener {

	public static ViewPager pager;
	List<Fragment> listFragment = new ArrayList<Fragment>();
	List<String> listTitle = new ArrayList<String>();
	// PagerTitleStrip TitlePager;
	// PagerSlidingTabStrip TitlePager;
	IsOnline isOnline;
	NotificationManager nm;
	SharedPreferences share_setting;
	SharedPreferences saveNotify;
	Boolean haveNotify;
	StartAppAd adv;
	SlidingMenu sm;
	LinearLayout layout_LichThi;
	LinearLayout layout_KetQuaThi;
	LinearLayout layout_KQHT;
	LinearLayout layout_TichLuy;
	View focus_lichthi, focus_ketquathi, focus_ketquaht, focus_tichluy;
	CanvasTransformer mTransformer;

	ShareActionProvider share;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		/*
		 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */

		setContentView(R.layout.main);
		getSupportActionBar()
				.setTitle(
						Html.fromHtml("<font color='#000000' size='1'>HaUI Students</font>"));
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.WHITE));

		StartAppAd.init(this, "101480587", "201218331");
		adv = new StartAppAd(this);
		adv.loadAd();
		adv.showAd();

		layout_LichThi = (LinearLayout) findViewById(R.id.layout_LichThi);
		layout_LichThi.setOnClickListener(this);
		layout_KetQuaThi = (LinearLayout) findViewById(R.id.layout_KetQuaThi);
		layout_KetQuaThi.setOnClickListener(this);
		layout_KQHT = (LinearLayout) findViewById(R.id.layout_KQHT);
		layout_KQHT.setOnClickListener(this);
		layout_TichLuy = (LinearLayout) findViewById(R.id.layout_TichLuy);
		layout_TichLuy.setOnClickListener(this);

		focus_lichthi = findViewById(R.id.focus_lichthi);
		focus_ketquathi = findViewById(R.id.focus_ketquathi);
		focus_ketquaht = findViewById(R.id.focus_ketquaht);
		focus_tichluy = findViewById(R.id.focus_tichluy);
		showFocusLichThi();

		// Thông báo activity đang hoạt động
		Variable.ACTIVITY_IS_ON = true;

		saveNotify = getSharedPreferences(Variable.FILE_NOTIFY, 0);
		haveNotify = saveNotify.getBoolean(Variable.KEY_NOTIFY, false);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// TitlePager = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		isOnline = new IsOnline(this);
		pager = (ViewPager) findViewById(R.id.pager);
		listFragment.add(new activity_XemLichThi());
		listFragment.add(new activity_XemKetQuaThi());
		listFragment.add(new activity_KetQuaHocTap());
		listFragment.add(new activity_TichLuy());
		// listFragment.add(new Option_Menu());

		listTitle.add("Lịch thi");
		listTitle.add("Kết quả thi");
		listTitle.add("Kết quả học tập");
		listTitle.add("Tích lũy");
		// listTitle.add("Menu");

		FragmentAdapter adapter = new FragmentAdapter(
				getSupportFragmentManager(), listFragment, listTitle);
		pager.setAdapter(adapter);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		// TitlePager.setViewPager(pager);

		// Tat notify neu co
		if (haveNotify) {
			nm.cancelAll();
			// pager.setCurrentItem(1);
		} else {
			// runService();
		}

		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int vt) {

				switch (vt) {
				case 0:

					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					showFocusLichThi();
					break;
				case 1:

					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					showFocusKetQuaThi();
					break;
				case 2:

					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					showFocusKetQuaHT();
					break;

				case 3:
					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					showFocusTichLuy();
					break;
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

		try {

			mTransformer = new CanvasTransformer() {

				@Override
				public void transformCanvas(Canvas canvas, float percentOpen) {
					canvas.translate(
							0,
							canvas.getHeight()
									* (1 - interp.getInterpolation(percentOpen)));
				}
			};

			sm = new SlidingMenu(this);
			sm.setShadowWidth(R.dimen.shadow_width);
			// sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
			sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
			sm.setFadeDegree(0.35f);
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
			sm.setBehindScrollScale(0.0f);
			sm.setBehindCanvasTransformer(mTransformer);
			sm.setMenu(R.layout.menu_frame);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, new Option_Menu()).commit();

		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	void runService() {
		if (isOnline.checkOnline()) {
			Intent it1 = new Intent(main.this, Service_Check_DiemThi.class);
			startService(it1);

			Intent it2 = new Intent(main.this, Service_Check_LichThi.class);
			startService(it2);

			Intent it3 = new Intent(main.this, Service_Check_KQHT.class);
			startService(it3);
		}
	}

	private static Interpolator interp = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t + 1.0f;
		}
	};
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	switch(item.getItemId()){
	case R.id.menu_refresh:
		if (!isOnline.checkOnline()) {
			Dialog_No_Connect dl = new Dialog_No_Connect(
					main.this,
					"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
			dl.show();
		} else {
			runService();
			Toast.makeText(getApplicationContext(),
					"Đang làm mới dữ liệu ...",
					Toast.LENGTH_LONG).show();
		}
		break;
	case R.id.menu_search:
		int indexPager = pager.getCurrentItem();
		switch (indexPager) {
		case 0:
			activity_XemLichThi.layoutInputLichThi
					.setVisibility(View.VISIBLE);
			break;
		case 1:
			activity_XemKetQuaThi.layoutInputKQT
					.setVisibility(View.VISIBLE);
			break;
		case 2:
			activity_KetQuaHocTap.layoutInputKQHT
					.setVisibility(View.VISIBLE);
			break;

		}
		break;
	case R.id.menu_chon:
		sm.toggle();
		break;
	}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { // TODO
		// Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public void onBackPressed() {

		if (sm.isMenuShowing()) {
			sm.toggle();
		} else {

			Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(main.this,
					"Bạn có muốn thoát ứng dụng hay không ?") {

				@Override
				public void OKClick() {
					finish();
					adv.onBackPressed();
					Variable.obj_lichthi = null;
					Variable.obj_ketquahoctap = null;
					Variable.obj_ketquahoctaptheolop = null;
					Variable.ACTIVITY_IS_ON = false;

				}
			};

			dialog.show();

		}

	}

	void showFocusLichThi() {
		focus_lichthi.setVisibility(View.VISIBLE);
		focus_ketquathi.setVisibility(View.INVISIBLE);
		focus_ketquaht.setVisibility(View.INVISIBLE);
		focus_tichluy.setVisibility(View.INVISIBLE);
	}

	void showFocusKetQuaThi() {
		focus_lichthi.setVisibility(View.INVISIBLE);
		focus_ketquathi.setVisibility(View.VISIBLE);
		focus_ketquaht.setVisibility(View.INVISIBLE);
		focus_tichluy.setVisibility(View.INVISIBLE);
	}

	void showFocusKetQuaHT() {
		focus_lichthi.setVisibility(View.INVISIBLE);
		focus_ketquathi.setVisibility(View.INVISIBLE);
		focus_ketquaht.setVisibility(View.VISIBLE);
		focus_tichluy.setVisibility(View.INVISIBLE);
	}

	void showFocusTichLuy() {
		focus_lichthi.setVisibility(View.INVISIBLE);
		focus_ketquathi.setVisibility(View.INVISIBLE);
		focus_ketquaht.setVisibility(View.INVISIBLE);
		focus_tichluy.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.layout_LichThi:
			pager.setCurrentItem(0);
			showFocusLichThi();
			break;

		case R.id.layout_KetQuaThi:
			pager.setCurrentItem(1);
			showFocusKetQuaThi();
			break;

		case R.id.layout_KQHT:
			pager.setCurrentItem(2);
			showFocusKetQuaHT();
			break;

		case R.id.layout_TichLuy:
			showFocusTichLuy();
			pager.setCurrentItem(3);
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			View decorView = getWindow().getDecorView();
			decorView.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			

		}
	
	}

}
