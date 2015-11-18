package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.diemthi_theolop;
import qlcl.search.haui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_DiemThi_TheoLop extends BaseAdapter {

	Context ct;
	List<diemthi_theolop> list;

	public Adapter_DiemThi_TheoLop(Context ct, List<diemthi_theolop> list) {
		super();
		this.ct = ct;
		this.list = list;
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
	public View getView(int position, View view, ViewGroup parent) {
		diemthi_theolop diemthi = list.get(position);
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.adapter_diemthi_theolop, null);
		}

		TextView tv_ten, tv_diem1, tv_diem2;
		tv_ten = (TextView) view.findViewById(R.id.tv_tensv_diemthi_theolop);
		tv_ten.setText(diemthi.getTen());
		tv_diem1 = (TextView) view.findViewById(R.id.diem1_theolop);
		tv_diem1.setText(diemthi.getDiem1());
		tv_diem2 = (TextView) view.findViewById(R.id.diem2_theolop);
		tv_diem2.setText(diemthi.getDiem2());
		return view;
	}

}
