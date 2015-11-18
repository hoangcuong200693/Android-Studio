package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.Student_KetQuaHocTap;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class adapter_viewpager_ChitietKetQuaHocTapTheoLop extends PagerAdapter {

	List<Student_KetQuaHocTap> listKQHTTHeoLop;
	Context ct;
	LayoutInflater inflater;

	public adapter_viewpager_ChitietKetQuaHocTapTheoLop(
			List<Student_KetQuaHocTap> listKQHTTHeoLop, Context ct,
			LayoutInflater inflater) {
		super();
		this.listKQHTTHeoLop = listKQHTTHeoLop;
		this.ct = ct;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listKQHTTHeoLop.size();
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

		Student_KetQuaHocTap st = listKQHTTHeoLop.get(position);
		View v = inflater.inflate(R.layout.adapter_detail_student_ketquahoctap,
				container, false);

		TextView tv_tenSV = (TextView) v
				.findViewById(R.id.tv_ChiTietMonthi_KetQuaHocTap_student);
		TextView tv_maSV = (TextView) v
				.findViewById(R.id.tv_maLopDocLap_KetQuaHocTap_student);
		TextView tv_diem1 = (TextView) v.findViewById(R.id.tv_diem1_student);
		TextView tv_diem2 = (TextView) v.findViewById(R.id.tv_diem2_student);
		TextView tv_diem3 = (TextView) v.findViewById(R.id.tv_diem3_student);
		TextView tv_diemGiuaHocPhan = (TextView) v
				.findViewById(R.id.tv_diemGiuaHocPhan_student);
		TextView tv_diemChuyenCan = (TextView) v
				.findViewById(R.id.tv_diemChuyenCan_student);
		TextView tv_diemTrungBinh = (TextView) v
				.findViewById(R.id.tv_diemTrungBinh_student);
		TextView tv_soTietNghi = (TextView) v
				.findViewById(R.id.tv_soTietNghi_student);
		TextView tv_dieuKienThi = (TextView) v
				.findViewById(R.id.tv_dieuKienThi_student); // TODO
															// Auto-generated
		tv_tenSV.setText(st.getTen());
		tv_maSV.setText(st.getMaSV());
		tv_diem1.setText(st.getDiem1());
		tv_diem2.setText(st.getDiem2());
		tv_diem3.setText(st.getDiem3());

		tv_diemGiuaHocPhan.setText(st.getDiemGiuaHocPhan());
		tv_diemChuyenCan.setText(st.getDiemChuyenCan());
		tv_diemTrungBinh.setText(st.getDiemTB());
		tv_soTietNghi.setText(st.getSoTietNghi());
		tv_dieuKienThi.setText(st.getDieuKienThi()); // method
		// stub

		container.addView(v);
		return v;
	}

}
