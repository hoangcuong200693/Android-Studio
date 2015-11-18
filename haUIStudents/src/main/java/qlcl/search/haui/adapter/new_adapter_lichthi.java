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

public class new_adapter_lichthi extends BaseAdapter {
	List<LichThi> list;
	Context ct;
	private int mLastPosition = -1;

	public new_adapter_lichthi(List<LichThi> list, Context ct) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		LichThi lt = list.get(position);
		int vt=position+1;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.new_adapter_lichthi, null);
		}

		TextView tv_new_tenmonthi = (TextView) convertView
				.findViewById(R.id.tv_new_tenLichThi);
		TextView tv_new_ngayThi = (TextView) convertView
				.findViewById(R.id.tv_new_ngayThi);
		TextView tv_new_gioThi = (TextView) convertView
				.findViewById(R.id.tv_new_gioThi);

		tv_new_tenmonthi.setText(vt+". "+lt.getTenMonThi());
		tv_new_ngayThi.setText(lt.getNgayThi() + " - " + lt.getPhongThi());
		tv_new_gioThi.setText(lt.getCaThi());
		return convertView;
	}

}
