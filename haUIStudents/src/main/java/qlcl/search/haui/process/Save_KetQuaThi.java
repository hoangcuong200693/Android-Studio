package qlcl.search.haui.process;

import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.variable.Variable;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Save_KetQuaThi {

	SharedPreferences share_KetQuaThi;
	Context act;

	public Save_KetQuaThi(Context act) {
		this.act = act;
		share_KetQuaThi = act.getSharedPreferences(Variable.FILE_KETQUATHI, 0);

	}

	public void saveToFile(Obj_KetQuaThi obj) {
		SharedPreferences.Editor editor = share_KetQuaThi.edit();
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		editor.putString(Variable.KEY_KETQUATHI, json);
		editor.commit();

	}

	public Obj_KetQuaThi readFromFile() {
		Gson gson = new Gson();
		String json = share_KetQuaThi.getString(Variable.KEY_KETQUATHI, "");
		Obj_KetQuaThi objKQT = gson.fromJson(json, Obj_KetQuaThi.class);
		return objKQT;

	}
}
