package qlcl.search.haui.activity;

import java.util.ArrayList;
import java.util.List;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import qlcl.search.haui.adapter.adapter_viewpager_ChitietKetQuaHocTap;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.obj.Student;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.app.Activity;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class activity_ChiTietKetQuaHocTap extends MyActivity {

	public static ViewPager pager;
	String link_danhsach;
	IsOnline isOnline;
	int vt;
	public static SlidingMenu menuslide;
	List<String> mlist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#EA151E")));
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#ffffff'>Kết quả học tập</font>"));
		setContentView(R.layout.viewpager);
		Bundle bd = getIntent().getExtras();
		vt = bd.getInt("vt");
		Variable.POSITION_KETQUAHOCTAP = vt;

		List<KetQuaHocTap> list_KetQuaHT = Variable.obj_ketquahoctap
				.getListKetQuaHocTap();
		for (KetQuaHocTap mh : list_KetQuaHT) {
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

		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.menu_frame,
						new fragment_slide(mlist, "Lịch thi",
								fragment_slide.TYPE_KETQUAHT)).commit();

		pager = (ViewPager) findViewById(R.id.view_pager_detail);

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		adapter_viewpager_ChitietKetQuaHocTap adapter = new adapter_viewpager_ChitietKetQuaHocTap(
				Variable.obj_ketquahoctap.getListKetQuaHocTap(), this, inflater);
		pager.setAdapter(adapter);
		pager.setCurrentItem(vt);
		link_danhsach = (Variable.obj_ketquahoctap.getListKetQuaHocTap()
				.get(vt).getLinkDsLop());

		isOnline = new IsOnline(getApplicationContext());

		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Variable.POSITION_KETQUAHOCTAP = pager.getCurrentItem();

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
		// Toast.makeText(getApplicationContext(), link_danhsach,
		// Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			break;
			
		case R.id.menu_share:
			
			Student st = Variable.obj_lichthi.getStudent();
			KetQuaHocTap kqht = Variable.obj_ketquahoctap
					.getListKetQuaHocTap().get(
							pager.getCurrentItem());
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent
					.putExtra(
							Intent.EXTRA_TEXT,
							"Kết quả học tập môn: "
									+ kqht.getTenMonHoc()
											.toUpperCase()
									+ "\n"
									+ st.getName()
									+ "\nLớp: "
									+ st.getLop()
									+ "\nĐiểm TB: "
									+ kqht.getDiemTrungBinh()
									+ "\nĐiều kiện thi: "
									+ kqht.getDieuKienThi()
									+ "\nhttps://play.google.com/store/apps/details?id=qlcl.search.haui_qlcl");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			
			break;
		case R.id.menu_xemtheolop:
			
			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dl = new Dialog_No_Connect(
						activity_ChiTietKetQuaHocTap.this,
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dl.show();
			} else {
				link_danhsach = (Variable.obj_ketquahoctap
						.getListKetQuaHocTap().get(
								pager.getCurrentItem())
						.getLinkDsLop());
				Intent it = new Intent(
						activity_ChiTietKetQuaHocTap.this,
						acticity_Student_KetQuaHocTap.class);
				it.putExtra(
						Variable.KETQUAHOCTAP_DANHSACHLOP,
						link_danhsach);
				startActivity(it);
			}
			
			break;
			
		case R.id.menu_chon:
			menuslide.toggle();
			break;
		}
	
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.menu_chitietketquahoctap, menu);
		
		return super.onCreateOptionsMenu(menu);
	}


}
