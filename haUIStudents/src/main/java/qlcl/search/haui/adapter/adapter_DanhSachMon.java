package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.activity.activity_TichLuy;
import qlcl.search.haui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class adapter_DanhSachMon extends BaseAdapter{


	List<String> list;
	LayoutInflater infalter;
	Context ct;

	public adapter_DanhSachMon(List<String> list,Context ct) {
		super();
		this.list = list;
		this.ct=ct;
		infalter = (LayoutInflater) ct
				.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
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
		if (convertView == null) {
			convertView = infalter.inflate(R.layout.textview, null);

		}
		TextView tv = (TextView) convertView.findViewById(R.id.tv);
		tv.setText(((int) position + 1) + ". " + list.get(position));
		return convertView;
	}



}
