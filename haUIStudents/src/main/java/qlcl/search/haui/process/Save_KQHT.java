package qlcl.search.haui.process;

import qlcl.search.haui.obj.Obj_KetQuaHocTap;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.variable.Variable;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Save_KQHT {


	SharedPreferences share_KetQuaHT;
	Context act;

	public Save_KQHT(Context act) {
		this.act = act;
		share_KetQuaHT = act.getSharedPreferences(Variable.FILE_KETQUAHOCTAP, 0);

	}

	public void saveToFile(Obj_KetQuaHocTap obj) {
		SharedPreferences.Editor editor = share_KetQuaHT.edit();
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		editor.putString(Variable.KEY_KETQUAHOCTAP, json);
		editor.commit();

	}

	public Obj_KetQuaHocTap readFromFile() {
		Gson gson = new Gson();
		String json = share_KetQuaHT.getString(Variable.KEY_KETQUAHOCTAP, "");
		Obj_KetQuaHocTap objKQHT = gson.fromJson(json, Obj_KetQuaHocTap.class);
		return objKQHT;

	}


}
