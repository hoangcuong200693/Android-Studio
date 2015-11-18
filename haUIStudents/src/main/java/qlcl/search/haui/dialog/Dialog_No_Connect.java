package qlcl.search.haui.dialog;

import qlcl.search.haui.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Dialog_No_Connect extends Dialog implements android.view.View.OnClickListener{
	Button bt;
	String st;
	TextView tv;

	public Dialog_No_Connect(Activity context,String st) {
		super(context);
		this.st=st;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		setContentView(R.layout.dialog_noconnect);
		bt=(Button) findViewById(R.id.btn_ok);
		tv=(TextView) findViewById(R.id.tv_dialog);
		tv.setText(st);
		bt.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		dismiss();
		
		
	}
	
	
	
	

}
