package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class adapter_viewpager_Chitietlichthi extends PagerAdapter {

	List<LichThi> listLichThi;
	Context ct;
	LayoutInflater inflater;

	public adapter_viewpager_Chitietlichthi(List<LichThi> listLichThi,
			Context ct, LayoutInflater inflater) {
		super();
		this.listLichThi = listLichThi;
		this.ct = ct;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listLichThi.size();
	}

	@Override
	public boolean isViewFromObject(View v, Object o) {
		// TODO Auto-generated method stub
		return v.equals(o);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LichThi lichthi = listLichThi.get(position);

		View v = inflater.inflate(R.layout.adapter_detail_lichthi, container,
				false);

		TextView tv_monThi = (TextView) v.findViewById(R.id.tv_ChiTietMonThi);
		TextView tv_ngayThi = (TextView) v.findViewById(R.id.tv_NgayThi);
		TextView tv_caThi = (TextView) v.findViewById(R.id.tv_CaThi);
		TextView tv_SBD = (TextView) v.findViewById(R.id.tv_SBD);
		TextView tv_lanThi = (TextView) v.findViewById(R.id.tv_LanThi);
		TextView tv_phongThi = (TextView) v.findViewById(R.id.tv_PhongThi);
		TextView tv_DiaDiem = (TextView) v.findViewById(R.id.tv_DiaDiem);
		TextView tv_LePhi = (TextView) v.findViewById(R.id.tv_LePhi);
		TextView tv_ghiChu = (TextView) v.findViewById(R.id.tv_GhiChu);

		tv_monThi.setText(lichthi.getTenMonThi());
		tv_ngayThi.setText(lichthi.getNgayThi());
		tv_caThi.setText(lichthi.getCaThi());
		tv_SBD.setText(lichthi.getSBD());
		tv_lanThi.setText(lichthi.getLanThi());
		tv_phongThi.setText(lichthi.getPhongThi());
		tv_DiaDiem.setText(lichthi.getDiaDiem());
		tv_LePhi.setText(lichthi.getLePhiThi());
		tv_ghiChu.setText(lichthi.getGhiChu());

		container.addView(v, 0);

		return v;

	}

}
