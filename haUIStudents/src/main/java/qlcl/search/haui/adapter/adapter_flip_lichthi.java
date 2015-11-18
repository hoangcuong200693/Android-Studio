package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class adapter_flip_lichthi extends BaseAdapter {

	List<LichThi> listLichThi;
	Context ct;
	LayoutInflater inflater;

	public adapter_flip_lichthi(List<LichThi> listLichThi, Context ct) {
		super();
		this.listLichThi = listLichThi;
		this.ct = ct;
		this.inflater = (LayoutInflater) this.ct
				.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listLichThi.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		LichThi lichthi = listLichThi.get(position);

		View v = inflater.inflate(R.layout.adapter_detail_lichthi, null);

		if (convertView == null) {
			v = inflater.inflate(R.layout.adapter_detail_lichthi, null);
			TextView tv_monThi = (TextView) v
					.findViewById(R.id.tv_ChiTietMonThi);
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
		} else {
			v = convertView;
			TextView tv_monThi = (TextView) v
					.findViewById(R.id.tv_ChiTietMonThi);
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
		}

		return v;
	}

}
