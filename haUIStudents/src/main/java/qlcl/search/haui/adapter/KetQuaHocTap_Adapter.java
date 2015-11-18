package qlcl.search.haui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.R;

public class KetQuaHocTap_Adapter extends BaseAdapter {

	List<KetQuaHocTap> list;
	Context ct;

	public KetQuaHocTap_Adapter(List<KetQuaHocTap> list, Context ct) {
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
		String diemTB = list.get(vt).getDiemTrungBinh();
		boolean coDiem = false;
		if (!diemTB.trim().equals("")) {
			coDiem = true;
		}

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.adapter_monhoc, null);

		}
		TextView tv = (TextView) view.findViewById(R.id.tvMonHoc);
		TextView tv_ketqua = (TextView) view.findViewById(R.id.tv_ketquathi);
	
		if (coDiem) {
			tv.setTextColor(Color.BLUE);
			tv_ketqua.setVisibility(View.VISIBLE);
			tv_ketqua.setText(diemTB);

		} else {
			tv.setTextColor(Color.RED);
			tv_ketqua.setVisibility(View.INVISIBLE);
		}
		tv.setText(monHoc);
		
		/*TranslateAnimation animation = null;
	     //   if (vt  & gt;  mLastPosition) {
	            animation = new TranslateAnimation(
	                Animation.RELATIVE_TO_SELF,
	                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
	                Animation.RELATIVE_TO_SELF, -1.0f,
	                Animation.RELATIVE_TO_SELF, 0.0f);

	            animation.setDuration(150);
	            view.startAnimation(animation);*/

		return view;
	}

}
