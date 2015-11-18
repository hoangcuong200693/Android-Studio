package qlcl.search.haui.dialog;

import qlcl.search.haui.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public abstract class Dialog_confirm_Exit extends Dialog implements
		android.view.View.OnClickListener {

	Button btOK, btCancel;
	String st;
	TextView tv_dialog;

	public Dialog_confirm_Exit(Activity act, String title) {
		super(act);
		st = title;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		setContentView(R.layout.dialog_confirm_exit);
		btOK = (Button) findViewById(R.id.btn_ok_exit);
		btOK.setOnClickListener(this);
		btCancel = (Button) findViewById(R.id.btn_cancel_exit);
		btCancel.setOnClickListener(this);
		tv_dialog=(TextView) findViewById(R.id.tv_dialog);
		tv_dialog.setText(st);
	}

	public abstract void OKClick();
	

	@Override
	public void onClick(View v) {
		if (v.getId() == btOK.getId()) {
			OKClick();
		} else {
			dismiss();
		}

	}

}
