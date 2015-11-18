package qlcl.search.haui.process;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.variable.Variable;

public class Save_LichThi {

	SharedPreferences share_LichThi;
	Context act;

	public Save_LichThi(Context act) {
		this.act = act;
		share_LichThi = act.getSharedPreferences(Variable.FILE_LICHTHI, 0);

	}

	public void saveToFile(Obj_LichThi obj) {
		SharedPreferences.Editor editor = share_LichThi.edit();
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		editor.putString(Variable.KEY_LICHTHI, json);
		editor.commit();

	}

	public Obj_LichThi readFromFile() {
		Gson gson = new Gson();
		String json = share_LichThi.getString(Variable.KEY_LICHTHI, null);
		Obj_LichThi objLT = gson.fromJson(json, Obj_LichThi.class);
		return objLT;

	}
}
