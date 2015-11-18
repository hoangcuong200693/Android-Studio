package qlcl.search.haui.process;

import android.app.Activity;
import android.content.SharedPreferences;

public class Save_HuongDanTichLuy {
	
	SharedPreferences save_HuongDan;
	Activity act;
	public Save_HuongDanTichLuy(Activity act) {
		super();
		this.act = act;
		save_HuongDan=act.getSharedPreferences("file_huongdan", 0);
		
	}
	
	
	public boolean isHuongDan(){
		boolean hd=save_HuongDan.getBoolean("key_huongdan", false);
		return hd;
	}
	
	public void setHuongDan(){
		SharedPreferences.Editor editor=save_HuongDan.edit();
		editor.putBoolean("key_huongdan", true);
		editor.commit();
	}
	

}
