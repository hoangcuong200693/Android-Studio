package qlcl.search.haui.dialog;

import qlcl.search.haui.activity.IsOnline;
import qlcl.search.haui.service.Service_Check_DiemThi;
import qlcl.search.haui.service.Service_Check_KQHT;
import qlcl.search.haui.service.Service_Check_LichThi;
import qlcl.search.haui.variable.Variable;
import qlcl.search.haui.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dialog_NhapMaSV extends Dialog implements
		android.view.View.OnClickListener {

	Button btn_save, btn_cancel;
	EditText edt_masv;
	Context ct;
	IsOnline isOnline;
	InputMethodManager input;

	SharedPreferences share;
	String maSV;
	Activity act;

	public Dialog_NhapMaSV(Context context) {
		super(context);
		ct = context;
		share = context.getSharedPreferences(Variable.FILENAME, 0);
		isOnline = new IsOnline(context);
		act = (Activity) context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setContentView(R.layout.dialog_nhapmasv);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_save.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		edt_masv = (EditText) findViewById(R.id.edt_masv);
		String ma = share.getString(Variable.KEY_SAVE, "");
		edt_masv.setText(ma);
		input = (InputMethodManager) ct
				.getSystemService(ct.INPUT_METHOD_SERVICE);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == btn_save.getId()) {

			maSV = edt_masv.getText().toString();
			input.hideSoftInputFromWindow(edt_masv.getApplicationWindowToken(),
					0);

			if (maSV.equalsIgnoreCase("")) {
				Dialog_No_Connect dl = new Dialog_No_Connect(act,
						"Hãy nhập mã sinh viên");
				dl.show();
			} else {

				if (maSV.length() != 10) {
					Dialog_No_Connect dl = new Dialog_No_Connect(act,
							"Mã sinh viên nhập không đúng !");
					dl.show();

				} else {

					SharedPreferences.Editor editor = share.edit();
					editor.putString(Variable.KEY_SAVE, edt_masv.getText()
							.toString());
					editor.commit();
					if (isOnline.checkOnline()) {

						Toast.makeText(
								ct,
								"Đang cập nhật lại dữ liệu, vui lòng chờ trong giây lát",
								Toast.LENGTH_LONG).show();

						Intent it1 = new Intent(ct, Service_Check_DiemThi.class);
						ct.startService(it1);

						Intent it2 = new Intent(ct, Service_Check_LichThi.class);
						ct.startService(it2);

						Intent it3 = new Intent(ct, Service_Check_KQHT.class);
						ct.startService(it3);
					}

					dismiss();
				}

			}
		}
		if (v.getId() == btn_cancel.getId()) {
			dismiss();
		}

	}

}
