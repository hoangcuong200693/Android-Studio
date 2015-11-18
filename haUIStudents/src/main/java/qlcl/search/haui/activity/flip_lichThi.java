package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.adapter.adapter_flip_lichthi;
import qlcl.search.haui.adapter.adapter_viewpager_Chitietlichthi;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/*import com.aphidmobile.flip.FlipViewController;*/
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class flip_lichThi extends Fragment {/*
	int vt;
	public static SlidingMenu menuslide;

	List<String> mlist = new ArrayList<String>();
	public static FlipViewController flip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		flip = new FlipViewController(this, FlipViewController.HORIZONTAL);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#228654")));
		setContentView(R.layout.viewpager);
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#ffffff'>Lịch thi</font>"));
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

		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.menu_frame,
						new fragment_slide(mlist, "Lịch thi",
								fragment_slide.TYPE_LICHTHI)).commit();

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		adapter_flip_lichthi adapter = new adapter_flip_lichthi(
				Variable.obj_lichthi.getList_LichThi(), this);
		flip.setAdapter(adapter);
		flip.setSelection(vt);

		setContentView(flip);
	}
	@Override
	public boolean onMenuItemSelected(int id, MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onMenuItemSelected(id, item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		menu.add("Chọn").setIcon(R.drawable.ic_action_overflow_d).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				;
				menuslide.toggle();
				fragment_slide.setPositon(flip.getSelectedItemPosition());
			
				return false;
			}
		}).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return super.onCreateOptionsMenu(menu);
	}
	
	  @Override
	  protected void onResume() {
	    super.onResume();
	    flip.onResume();
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    flip.onPause();
	  }

*/}
