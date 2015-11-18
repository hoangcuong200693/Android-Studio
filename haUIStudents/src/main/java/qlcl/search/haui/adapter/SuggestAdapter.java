package qlcl.search.haui.adapter;

import java.util.List;

import qlcl.search.haui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
/**
 * 
 * @author Eo Cuong
 *<h2>Class custom Suggest List trong AutoCompleteTextview</h2>
 */
public class SuggestAdapter extends ArrayAdapter<String> {
	
	List<String> list;
	Context ct;
	int layout;
	
	public SuggestAdapter(Context ct, int layout,
			List<String> list) {
		super(ct, layout, list);
		this.ct=ct;
		this.layout=layout;
		this.list=list;
		// TODO Auto-generated constructor stub
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			LayoutInflater inflater=(LayoutInflater) ct.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
		convertView=inflater.inflate(layout, null);
		
		}
		TextView tv=(TextView) convertView.findViewById(R.id.tv_suggest);
		tv.setText(list.get(position));
		
		return convertView;
	}
	
	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}


	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	
	
	



}
