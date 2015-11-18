package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.Student_KetQuaHocTap;
import qlcl.search.haui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class adapter_Student_KetQuaHocTap extends BaseAdapter{
	
	Context ct;
	List<Student_KetQuaHocTap> list;
	
	

	public adapter_Student_KetQuaHocTap(Context ct,
			List<Student_KetQuaHocTap> list) {
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
	Student_KetQuaHocTap hocTapTheoLop=list.get(position);
	int vitri=position+1;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.new_adapter_kqht, null);

		}
		// init
		TextView tv_new_tenkqht = (TextView) v
				.findViewById(R.id.tv_new_tenkqht);
		tv_new_tenkqht.setTextColor(ct.getResources().getColor(R.color.red));
		TextView tv_new_labelTietNghi = (TextView) v
				.findViewById(R.id.tv_new_labelTietNghi);
		TextView tv_new_SoTietNghi = (TextView) v
				.findViewById(R.id.tv_new_sotietnghi);
		TextView tv_new_DieuKienThi = (TextView) v
				.findViewById(R.id.tv_new_dieukienthi);
		TextView tv_new_diemTB = (TextView) v.findViewById(R.id.tv_new_diemTB);
		
		tv_new_tenkqht.setText(vitri+". "+hocTapTheoLop.getTen());
		String diemTB =hocTapTheoLop.getDiemTB();
		if (diemTB.trim().equals("")) {
			tv_new_labelTietNghi.setVisibility(View.GONE);
			tv_new_SoTietNghi.setVisibility(View.GONE);
			tv_new_DieuKienThi.setVisibility(View.GONE);
			tv_new_diemTB.setVisibility(View.GONE);
			tv_new_tenkqht.setTextColor(ct.getResources().getColor(R.color.red));

		} else {
		//	tv_new_tenkqht.setTextColor(ct.getResources().getColor(R.color.xanhdatroi));
			tv_new_labelTietNghi.setVisibility(View.VISIBLE);
			tv_new_SoTietNghi.setVisibility(View.VISIBLE);
			tv_new_DieuKienThi.setVisibility(View.VISIBLE);
			tv_new_diemTB.setVisibility(View.VISIBLE);

			tv_new_SoTietNghi.setText(hocTapTheoLop.getSoTietNghi());
			tv_new_DieuKienThi.setText(hocTapTheoLop.getDieuKienThi());
			tv_new_diemTB.setText(hocTapTheoLop.getDiemTB());

		}
		return v;
	}

}
