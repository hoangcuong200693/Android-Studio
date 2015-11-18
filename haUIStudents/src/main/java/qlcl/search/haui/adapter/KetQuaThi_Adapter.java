package qlcl.search.haui.adapter;

import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class KetQuaThi_Adapter extends BaseAdapter {

	List<MonHoc> list;
	Context ct;

	public KetQuaThi_Adapter(List<MonHoc> list, Context ct) {
		super();
		this.list = list;
		this.ct = ct;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int vt, View view, ViewGroup parent) {
		String monHoc = list.get(vt).getTenMonHoc();
		String diemTBC = list.get(vt).getDiemTBC();
		String diem1 = list.get(vt).getDiemThiL1();
		String diem2 = list.get(vt).getDiemThiL2();
		boolean coDiem = false;
		boolean dongLePhi=true;
		if (!diemTBC.trim().equals("")) {
			coDiem = true;
		}
		
		if (diemTBC.trim().equals("**")) {
			dongLePhi=false;
		}
		
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.adapter_monhoc, null);

		}
		TextView tv = (TextView) view.findViewById(R.id.tvMonHoc);
		TextView tv_ketquathi = (TextView) view.findViewById(R.id.tv_ketquathi);
		if (coDiem) {
			tv_ketquathi.setVisibility(View.VISIBLE);
			if (!diem2.trim().equals("")) {
				tv_ketquathi.setText(diem1 + " - " + diem2);
			} else {
				tv_ketquathi.setText(diem1);
			}

			tv.setTextColor(Color.BLUE);
		} else {
			tv.setTextColor(Color.RED);
			tv_ketquathi.setVisibility(View.INVISIBLE);
		}
		tv.setText(monHoc);
		TextView tv_star = (TextView) view.findViewById(R.id.tv_star);
		if (!dongLePhi) {
			tv_star.setVisibility(View.VISIBLE);
			tv_ketquathi.setVisibility(View.INVISIBLE);
		} else {
			tv_star.setVisibility(View.INVISIBLE);
		}

		/*TranslateAnimation animation = null;
		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(300);
		view.setAnimation(animation);*/
		

		return view;
	}
}
