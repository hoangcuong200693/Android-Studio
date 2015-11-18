package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class adapter_viewpager_ChitietKetQuaHocTap extends PagerAdapter {

	List<KetQuaHocTap> listKetQuaHocTap;
	Context ct;
	LayoutInflater inflater;
	
	
	

	public adapter_viewpager_ChitietKetQuaHocTap(
			List<KetQuaHocTap> listKetQuaHocTap, Context ct,
			LayoutInflater inflater) {
		super();
		this.listKetQuaHocTap = listKetQuaHocTap;
		this.ct = ct;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listKetQuaHocTap.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0.equals(arg1);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		KetQuaHocTap kq = listKetQuaHocTap.get(position);
		View v = inflater.inflate(R.layout.adapter_detail_ketquahoctap,
				container, false);

		TextView tv_tenMon = (TextView) v
				.findViewById(R.id.tv_ChiTietMonthi_KetQuaHocTap);
		TextView tv_maLopDocLap = (TextView) v
				.findViewById(R.id.tv_maLopDocLap_KetQuaHocTap);
		TextView tv_diem1 = (TextView) v.findViewById(R.id.tv_diem1);
		TextView tv_diem2 = (TextView) v.findViewById(R.id.tv_diem2);
		TextView tv_diem3 = (TextView) v.findViewById(R.id.tv_diem3);
		TextView tv_diemGiuaHocPhan = (TextView) v
				.findViewById(R.id.tv_diemGiuaHocPhan);
		TextView tv_diemChuyenCan = (TextView) v
				.findViewById(R.id.tv_diemChuyenCan);
		TextView tv_diemTrungBinh = (TextView) v
				.findViewById(R.id.tv_diemTrungBinh);
		TextView tv_soTietNghi = (TextView) v.findViewById(R.id.tv_soTietNghi);
		TextView tv_dieuKienThi = (TextView) v
				.findViewById(R.id.tv_dieuKienThi);

		tv_tenMon.setText(kq.getTenMonHoc());
		tv_maLopDocLap.setText(kq.getMaLopDocLap());
		tv_diem1.setText(kq.getDiem1());
		tv_diem2.setText(kq.getDiem2());
		tv_diem3.setText(kq.getDiem3());

		tv_diemGiuaHocPhan.setText(kq.getDiemGiuaHocPhan());
		tv_diemChuyenCan.setText(kq.getDiemChuyenCan());
		tv_diemTrungBinh.setText(kq.getDiemTrungBinh());
		tv_soTietNghi.setText(kq.getSoTietNghi());
		tv_dieuKienThi.setText(kq.getDieuKienThi());
		container.addView(v);

		return v;
	}

}
