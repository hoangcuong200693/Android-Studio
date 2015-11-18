package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.R;
import android.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class new_adapter_kqht extends BaseAdapter {

	List<KetQuaHocTap> list;
	Context ct;

	public new_adapter_kqht(List<KetQuaHocTap> list, Context ct) {
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int vt, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub

		KetQuaHocTap kqht = list.get(vt);
		int vitri=vt+1;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.new_adapter_kqht, null);

		}
		// init
		TextView tv_new_tenkqht = (TextView) v
				.findViewById(R.id.tv_new_tenkqht);
		TextView tv_new_labelTietNghi = (TextView) v
				.findViewById(R.id.tv_new_labelTietNghi);
		TextView tv_new_SoTietNghi = (TextView) v
				.findViewById(R.id.tv_new_sotietnghi);
		TextView tv_new_DieuKienThi = (TextView) v
				.findViewById(R.id.tv_new_dieukienthi);
		TextView tv_new_diemTB = (TextView) v.findViewById(R.id.tv_new_diemTB);
		// set value
		tv_new_tenkqht.setText(vitri+". "+kqht.getTenMonHoc());
		String diemTB = kqht.getDiemTrungBinh();
		if (diemTB.trim().equals("")) {
			tv_new_labelTietNghi.setVisibility(View.GONE);
			tv_new_SoTietNghi.setVisibility(View.GONE);
			tv_new_DieuKienThi.setVisibility(View.GONE);
			tv_new_diemTB.setVisibility(View.GONE);
			tv_new_tenkqht.setTextColor(ct.getResources().getColor(R.color.red));

		} else {
			tv_new_tenkqht.setTextColor(ct.getResources().getColor(R.color.xanhdatroi));
			tv_new_labelTietNghi.setVisibility(View.VISIBLE);
			tv_new_SoTietNghi.setVisibility(View.VISIBLE);
			tv_new_DieuKienThi.setVisibility(View.VISIBLE);
			tv_new_diemTB.setVisibility(View.VISIBLE);

			tv_new_SoTietNghi.setText(kqht.getSoTietNghi());
			tv_new_DieuKienThi.setText(kqht.getDieuKienThi());
			tv_new_diemTB.setText(kqht.getDiemTrungBinh());

		}

		return v;
	}

}
