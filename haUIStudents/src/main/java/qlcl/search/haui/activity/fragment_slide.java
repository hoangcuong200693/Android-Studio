package qlcl.search.haui.activity;

import java.util.List;

import qlcl.search.haui.R;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class fragment_slide extends Fragment implements OnItemClickListener {

	static ListView mListView;
	List<String> list_MocHoc;
	String title;
	public static final int TYPE_LICHTHI = 1;
	public static final int TYPE_KETQUATHI = 2;
	public static final int TYPE_KETQUAHT = 3;
	int type;

	@SuppressLint("ValidFragment")
	public fragment_slide(List<String> list_MocHoc, String title, int type) {
		super();
		this.list_MocHoc = list_MocHoc;
		this.title = title;
		this.type = type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.slidemonhoc, container, false);
		switch (type) {
		case TYPE_LICHTHI:
			v.setBackgroundResource(R.drawable.border_tb_green);
			break;
		case TYPE_KETQUATHI:
			v.setBackgroundResource(R.drawable.border_tb_blue);
			break;
		case TYPE_KETQUAHT:
			v.setBackgroundResource(R.drawable.border_tb_red);
			break;
		}

		mListView = (ListView) v.findViewById(R.id.list_slide);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.textview, list_MocHoc);
		// mListView.setDivider(getActivity().getResources().getDrawable(R.drawable.bg_btn_search_green));
		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(this);

		return v;

	}

	public static void setPositon(int vt) {
		mListView.setSelection(vt);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		switch (type) {
		case TYPE_LICHTHI:
			activity_ChiTietLichThi.menuslide.toggle();
			activity_ChiTietLichThi.pager.setCurrentItem(arg2);
			break;

		case TYPE_KETQUATHI:
			activity_ChiTietKetQuaThi.menuslide.toggle();
			activity_ChiTietKetQuaThi.pager.setCurrentItem(arg2);
			break;

		case TYPE_KETQUAHT:
			activity_ChiTietKetQuaHocTap.menuslide.toggle();
			activity_ChiTietKetQuaHocTap.pager.setCurrentItem(arg2);
			break;

		}

	}

}
