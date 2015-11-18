package qlcl.search.haui.process;

import com.google.gson.Gson;

import qlcl.search.haui.obj.Obj_TichLuy;
import qlcl.search.haui.variable.Variable;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class SaveTichLuy {

	Activity act;
	SharedPreferences share_TichLuy;

	public SaveTichLuy(Activity act) {
		super();
		this.act = act;
		share_TichLuy = act.getSharedPreferences(Variable.FILE_TICHLUY, 0);
	}

	public void saveToFile(Obj_TichLuy obj) {
		SharedPreferences.Editor editor = share_TichLuy.edit();
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		editor.putString(Variable.KEY_TICHLUY, json);
		editor.commit();
		Log.d("tichluy", "luu file thanh cong");

	}

	public Obj_TichLuy readFromFile() {
		Gson gson = new Gson();
		String json = share_TichLuy.getString(Variable.KEY_TICHLUY, "");
		Obj_TichLuy obj = gson.fromJson(json, Obj_TichLuy.class);
		Log.d("tichluy", "doc  file thanh cong");
		return obj;
		// Toast.makeText(act.getBaseContext(),
		// "Luu diem tich luy thanh cong",Toast.LENGTH_SHORT).show();
	

	}

}
