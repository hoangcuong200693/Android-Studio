package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.R;
import qlcl.search.haui.obj.RoundedImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Option_Adapter extends BaseAdapter {

	List<Integer> listIcon;
	List<String> listTitle;
	Context ct;

	public Option_Adapter(List<Integer> listIcon, List<String> listTitle,
			Context ct) {
		super();
		this.listIcon = listIcon;
		this.listTitle = listTitle;
		this.ct = ct;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listIcon.size();
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

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) ct
					.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.adapter_option, null);

		}
		TextView tv = (TextView) v.findViewById(R.id.tv_option);
		RoundedImageView icon = (RoundedImageView) v.findViewById(R.id.icon_option);

		tv.setText(listTitle.get(vt));
		icon.setImageResource(listIcon.get(vt));

		return v;
	}

}
