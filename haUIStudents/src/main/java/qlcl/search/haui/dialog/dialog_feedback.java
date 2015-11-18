package qlcl.search.haui.dialog;

import qlcl.search.haui.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dialog_feedback extends Dialog{
	
	Button btn_feedback;
	EditText edt_chuDe,edt_noiDung;
	Context ct;

	public dialog_feedback(Context context) {
		super(context);
		this.ct=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		setContentView(R.layout.activity_feedback);
		edt_chuDe=(EditText) findViewById(R.id.edt_chuDe);
		edt_noiDung=(EditText) findViewById(R.id.edt_noiDung);
		btn_feedback=(Button) findViewById(R.id.btn_feedback);
		btn_feedback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(edt_noiDung.getText().toString().equalsIgnoreCase("")){
					Dialog_No_Connect dl=new Dialog_No_Connect((Activity) ct, "Hãy nhập nội dung phản hồi !");
					dl.show();
				}else{
				sentEmail();
				dismiss();
				}
			}
		});
	}
	
	void sentEmail(){
		String subject=edt_chuDe.getText().toString();
		String body=edt_noiDung.getText().toString();
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"smil30ns4d@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT   ,body);
		try {
		    ct.startActivity(Intent.createChooser(i, "Cảm ơn đã gửi phản hồi cho tôi !"));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(ct, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	

	

}
