package qlcl.search.haui.adapter;

import java.util.ArrayList;
import java.util.List;


import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class lichThi_Adapter extends BaseAdapter{
	List<LichThi> list;
	Context ct;
	 private int mLastPosition = -1;
	

	public lichThi_Adapter(List<LichThi> list, Context ct) {
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
	public View getView(int vt, View view, ViewGroup arg2) {
		String monHoc=list.get(vt).getTenMonThi();
		

		if(view==null){
			LayoutInflater inflater=(LayoutInflater) ct.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
		view=inflater.inflate(R.layout.adapter_monhoc, null);
		
		}
		TextView tv=(TextView) view.findViewById(R.id.tvMonHoc);
		
		
		tv.setText(monHoc);
		
	/*	 TranslateAnimation animation = null;
	      if (mLastPosition<list.size()-1) {
	            animation = new TranslateAnimation(
	                Animation.RELATIVE_TO_SELF,
	                1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
	                Animation.RELATIVE_TO_SELF, 1.0f,
	                Animation.RELATIVE_TO_SELF, 0.0f);

	            animation.setDuration(150);
	            view.startAnimation(animation);
	            mLastPosition = vt;
	       }
		*/
		
		return view;
	}
	

}
