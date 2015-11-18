package qlcl.search.haui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;

public class activity_xacnhanMK extends ActionBarActivity implements
		OnClickListener {
	EditText edt_xacnhan_mk;
	Button bt_ok_xacnhan, bt_Huy_xacnhan;
	SharedPreferences share_mk;// du lieu tu tao
	SharedPreferences share_setting;// du lieu tu setting

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setTitle("Nhập mật khẩu");
		setContentView(R.layout.activity_xacnhanmatkhau);
		edt_xacnhan_mk = (EditText) findViewById(R.id.edt_xacnhan_mk);
		bt_ok_xacnhan = (Button) findViewById(R.id.bt_ok_xacnhan);
		bt_Huy_xacnhan = (Button) findViewById(R.id.bt_Huy_xacnhan);
		bt_ok_xacnhan.setOnClickListener(this);
		bt_Huy_xacnhan.setOnClickListener(this);
		share_mk = getSharedPreferences(Variable.FILE_MK, 0);
		share_setting = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == bt_ok_xacnhan.getId()) {
			String mk_saved = share_mk.getString(Variable.KEY_MK, "");
			String mk_xacnhan = edt_xacnhan_mk.getText().toString();
			if (!mk_saved.trim().equals(mk_xacnhan)) {
				Dialog_No_Connect dl = new Dialog_No_Connect(this,
						"Mật khẩu bạn nhập không đúng !");
				dl.show();
			}else{
			Toast.makeText(getApplicationContext(), "Đã tắt bảo mật", Toast.LENGTH_SHORT).show();
				finish();
			}

		}
		if (v.getId() == bt_Huy_xacnhan.getId()) {

			SharedPreferences.Editor edt = share_setting.edit();
			edt.putBoolean("check_matKhau", true);
			edt.commit();
			finish();
		}

	}

	@Override
	public void onBackPressed() {
		SharedPreferences.Editor edt = share_setting.edit();
		edt.putBoolean("check_matKhau", true);
		edt.commit();
		finish();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			View decorView = getWindow().getDecorView();
			decorView.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			

		}
	
	}
	
	
}
