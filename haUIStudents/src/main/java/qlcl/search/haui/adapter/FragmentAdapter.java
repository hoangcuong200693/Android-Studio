package qlcl.search.haui.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{
	
	List<Fragment> listFragment;
	List<String> listTitle;

	public FragmentAdapter(FragmentManager fm,List<Fragment> listFragment,List<String> listTitle) {
		super(fm);
		this.listFragment=listFragment;
		this.listTitle=listTitle;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return listTitle.get(position);
	}


	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return listFragment.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listTitle.size();
	}
	
	

}
