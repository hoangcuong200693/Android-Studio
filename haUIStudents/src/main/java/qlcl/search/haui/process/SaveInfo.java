package qlcl.search.haui.process;

import qlcl.search.haui.variable.Variable;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SaveInfo {

	Context act;
	SharedPreferences shareInfo;

	public SaveInfo(Context act) {
		super();
		this.act = act;
		shareInfo = act.getSharedPreferences(Variable.FILE_INFO, 0);
	}

	public void saveToFile(String st) {
		SharedPreferences.Editor editor = shareInfo.edit();

		editor.putString(Variable.KEY_INFOR, st);
		editor.commit();
		// Log.d("tichluy", "luu file thanh cong");

	}

	public String readFromFile() {

		return shareInfo.getString(Variable.KEY_INFOR,null);

	}

}
