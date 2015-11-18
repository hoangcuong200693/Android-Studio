package qlcl.search.haui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
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

/**
 * 
 * @author Eo Class Allow enter new password to turn on security
 * 
 */
public class activity_nhapMatKhauMoi extends ActionBarActivity implements
		OnClickListener {

	EditText edt_mk, edt_xacnhan_mk;
	Button bt_OK_mk, btn_Cancel_mk;
	SharedPreferences share_mk;
	SharedPreferences share_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setTitle("Thêm mật khẩu bảo mật");
		setContentView(R.layout.activity_nhapmatkhaumoi);
		edt_mk = (EditText) findViewById(R.id.edt_mk);
		edt_xacnhan_mk = (EditText) findViewById(R.id.edt_xacnhan_mk);
		bt_OK_mk = (Button) findViewById(R.id.bt_OK_mk);
		btn_Cancel_mk = (Button) findViewById(R.id.btn_Cancel_mk);
		bt_OK_mk.setOnClickListener(this);
		btn_Cancel_mk.setOnClickListener(this);

		share_mk = getSharedPreferences(Variable.FILE_MK, 0);
		share_setting = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == bt_OK_mk.getId()) {
			String mk1 = edt_mk.getText().toString();
			String mk2 = edt_xacnhan_mk.getText().toString();
			if (mk1.trim().equals("") || mk2.trim().equals("")) {
				Dialog_No_Connect dl = new Dialog_No_Connect(this,
						"Không được bỏ trống trường nào !");
				dl.show();

			} else {
				if (!mk1.equals(mk2)) {
					Dialog_No_Connect dl = new Dialog_No_Connect(this,
							"Mật khẩu xác nhận không trùng khớp !");
					dl.show();
				} else {

					SharedPreferences.Editor editor = share_mk.edit();
					editor.putString(Variable.KEY_MK, mk1);
					editor.commit();
					Toast.makeText(getApplicationContext(), "Đã bật bảo mật",
							Toast.LENGTH_SHORT).show();
					finish();

				}
			}

		}

		if (v.getId() == btn_Cancel_mk.getId()) {
			/*
			 * Cancel turn on security
			 */
			SharedPreferences.Editor edt = share_setting.edit();
			edt.putBoolean("check_matKhau", false);
			edt.commit();

			finish();

		}

	}

	@Override
	public void onBackPressed() {
		/*
		 * Cancel turn on security
		 */
		SharedPreferences.Editor edt = share_setting.edit();
		edt.putBoolean("check_matKhau", false);
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
