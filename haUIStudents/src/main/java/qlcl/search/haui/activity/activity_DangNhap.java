package qlcl.search.haui.activity;


import qlcl.search.haui.dialog.Dialog_confirm_Exit;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_DangNhap extends ActionBarActivity implements
		android.view.View.OnClickListener {

	EditText edt_DangNhap;
	Button bt_OK_dangNhap, bt_Cancel_DangNhap;
	TextView tv_sai_MK;

	SharedPreferences share_mk;
	InputMethodManager input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Đăng nhập");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setContentView(R.layout.dialog_login);
		edt_DangNhap = (EditText) findViewById(R.id.edt_dangnhap);
		bt_OK_dangNhap = (Button) findViewById(R.id.btn_ok_dangnhap);
		bt_Cancel_DangNhap = (Button) findViewById(R.id.btn_cancel_dangnhap);
		bt_OK_dangNhap.setOnClickListener(this);
		bt_Cancel_DangNhap.setOnClickListener(this);
		input = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		edt_DangNhap.requestFocus();
		edt_DangNhap.setFocusableInTouchMode(true);
		input.showSoftInput(edt_DangNhap, InputMethodManager.SHOW_FORCED);

		tv_sai_MK = (TextView) findViewById(R.id.tv_mk_sai);
		tv_sai_MK.setVisibility(View.INVISIBLE);
		share_mk = getSharedPreferences(Variable.FILE_MK, 0);

		edt_DangNhap.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				tv_sai_MK.setVisibility(View.INVISIBLE);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == bt_OK_dangNhap.getId()) {
			String mk_saved = share_mk.getString(Variable.KEY_MK, "");
			String mk_nhap = edt_DangNhap.getText().toString();
			if (!mk_saved.equals(mk_nhap)) {
				tv_sai_MK.setVisibility(View.VISIBLE);
			} else {
				finish();
				Intent it = new Intent(activity_DangNhap.this, main.class);
				startActivity(it);
			}

		} else {
			Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(
					activity_DangNhap.this,
					"Bạn có muốn thoát ứng dụng hay không ?") {

				@Override
				public void OKClick() {
					finish();

				}
			};

			dialog.show();
		}

	}

	@Override
	public void onBackPressed() {

		Dialog_confirm_Exit dialog = new Dialog_confirm_Exit(
				activity_DangNhap.this,
				"Bạn có muốn thoát ứng dụng hay không ?") {

			@Override
			public void OKClick() {
				finish();

			}
		};

		dialog.show();

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
