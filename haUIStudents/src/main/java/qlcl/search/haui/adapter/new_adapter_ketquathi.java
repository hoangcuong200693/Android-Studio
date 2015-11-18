package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class new_adapter_ketquathi extends BaseAdapter {

	List<MonHoc> list;
	Context ct;

	public new_adapter_ketquathi(List<MonHoc> list, Context ct) {
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
	public Object getItem(int position) {
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
		// TODO Auto-generated method stub
		int vitri = vt + 1;
		String monHoc = list.get(vt).getTenMonHoc();
		String diemTBC = list.get(vt).getDiemTBC();
		String diem1 = list.get(vt).getDiemThiL1();
		String diem2 = list.get(vt).getDiemThiL2();
		String diemChuString = list.get(vt).getDiemTinChi();
		String soTinChi = list.get(vt).getSoTinChi();
		boolean coDiem = false;
		boolean dongLePhi = true;
		if (!diemTBC.trim().equals("")) {
			coDiem = true;
		}

		if (diemTBC.trim().equals("**")) {
			dongLePhi = false;
		}

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.new_adapter_ketquathi, null);

		}

		TextView tv_new_TenKqt = (TextView) view
				.findViewById(R.id.tv_new_tenketquathi);
		TextView tv_new_labelDiemThi = (TextView) view
				.findViewById(R.id.tv_new_labelDiemThi);
		TextView tv_new_diemThi = (TextView) view
				.findViewById(R.id.tv_new_diemThi);
		TextView tv_new_diemTBC = (TextView) view
				.findViewById(R.id.tv_new_diemTBC);
		TextView tv_new_diemChu = (TextView) view
				.findViewById(R.id.tv_new_diemChu);
		tv_new_TenKqt.setText(vitri+". "+monHoc);
		if (coDiem) {
			tv_new_TenKqt.setTextColor(ct.getResources().getColor(R.color.xanhdatroi));
			tv_new_labelDiemThi.setVisibility(View.VISIBLE);
			tv_new_diemTBC.setVisibility(View.VISIBLE);
			tv_new_diemThi.setVisibility(View.VISIBLE);
			tv_new_diemChu.setVisibility(View.VISIBLE);

		

			// Kiểm tra đóng lệ phí thi đổi màu chữ

			// kiểm tra điều kiện điểm F thay đổi màu chữ
			

			if (dongLePhi) {
				// kiểm tra điều kiện điểm F thay đổi màu chữ
				if (diemChuString.trim().equals("F")) {
					tv_new_diemChu.setTextColor(ct.getResources().getColor(
							R.color.diemF));
				} else if (diemChuString.trim().equals("D")) {
					tv_new_diemChu.setTextColor(ct.getResources().getColor(
							R.color.diemD));
				} else if (diemChuString.trim().equals("C")) {
					tv_new_diemChu.setTextColor(ct.getResources().getColor(
							R.color.diemC));
				} else if (diemChuString.trim().equals("B")) {
					tv_new_diemChu.setTextColor(ct.getResources().getColor(
							R.color.diemB));
				} else if (diemChuString.trim().equals("A")) {
					tv_new_diemChu.setTextColor(ct.getResources().getColor(
							R.color.diemA));
				}
			} else {
				tv_new_TenKqt.setTextColor(Color.BLACK);
				tv_new_diemChu.setTextColor(Color.BLACK);
			}

			tv_new_diemTBC.setText("Điểm TBC: "+diemTBC);
			if (diem2.trim().equals("")) {
				tv_new_labelDiemThi.setText("Điểm thi lần 1:  ");
				tv_new_diemThi.setText(diem1);
			} else {
				tv_new_labelDiemThi.setText("Điểm thi lần 2 : ");
				tv_new_diemThi.setText(diem2);
			}

			tv_new_diemChu.setText(diemChuString);

		} else {
			tv_new_TenKqt.setTextColor(ct.getResources().getColor(R.color.red));
			tv_new_labelDiemThi.setVisibility(View.GONE);
			tv_new_diemTBC.setVisibility(View.GONE);
			tv_new_diemThi.setVisibility(View.GONE);
			tv_new_diemChu.setVisibility(View.GONE);
		}
		return view;
	}

}
