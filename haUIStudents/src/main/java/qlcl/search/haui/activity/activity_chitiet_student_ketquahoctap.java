package qlcl.search.haui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import qlcl.search.haui.adapter.adapter_viewpager_ChitietKetQuaHocTapTheoLop;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

public class activity_chitiet_student_ketquahoctap extends MyActivity {

	ViewPager pager;

	int vt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FF8900")));
	
		pager=(ViewPager) findViewById(R.id.view_pager_detail);

		Bundle bd = getIntent().getExtras();
		vt = bd.getInt("vt");
		//setTitle();
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#ffffff'>"+Variable.obj_ketquahoctaptheolop.getHeader().getTenMon()+"</font>"));
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		adapter_viewpager_ChitietKetQuaHocTapTheoLop adapter = new adapter_viewpager_ChitietKetQuaHocTapTheoLop(
				Variable.obj_ketquahoctaptheolop.getListDanhSach(), this,
				inflater);
		pager.setAdapter(adapter);
		pager.setCurrentItem(vt);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}


}
